/*
    Rotas para a funcionalidade de lista
*/

package com.projetop2.myasiandramas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projetop2.myasiandramas.model.Dorama;
import com.projetop2.myasiandramas.model.Lista;
import com.projetop2.myasiandramas.model.ListaDorama;
import com.projetop2.myasiandramas.model.Usuario;
import com.projetop2.myasiandramas.model.service.DoramaService;
import com.projetop2.myasiandramas.model.service.ListaDoramaService;
import com.projetop2.myasiandramas.model.service.ListaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ListaController {

    @Autowired
    private ListaService listaService;
    @Autowired
    private DoramaService doramaService;
    @Autowired
    private ListaDoramaService listaDoramaService;

    //CREATE:

    @GetMapping("/listas/criar")
    public String formCriarLista(Model model,
                                 HttpSession session){

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) //Se não há nenhum usuário logado, redireciona para fazer o login
            return "redirect:/login";

        model.addAttribute("criarLista",new Lista());
        return "lista-criar";
    }

    @PostMapping("/listas/criar")
    public String formCriarLista(@ModelAttribute Lista li, 
                                 Model model, 
                                 HttpSession session){
        //Verifica dados do usuário logado
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        li.setIdUsuario(usuarioLogado.getIdUsuario());

		listaService.criarLista(li);
		return "redirect:/listas/minhas";
    }

    //Insere Dorama em Lista

    @GetMapping("/listas/adicionar-dorama/{idDorama}")
    public String adicionarDoramaLista(Model model, 
                                       @PathVariable int idDorama,
                                       HttpSession session){

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) 
            return "redirect:/login";

        Dorama d = doramaService.buscarDoramaPorId(idDorama);
        model.addAttribute("dorama",d);

        //Listas para escolher quando for adicionar dorama em lista:
        model.addAttribute("minhasListas", listaService.buscarListaPorUsuario(usuarioLogado.getIdUsuario()));
        return "lista-adicionar-dorama";
    }

    @PostMapping("/listas/adicionar-dorama")
    public String adicionarDorama(@ModelAttribute ListaDorama ld){
        listaDoramaService.inserirDoramaEmLista(ld);
        return "redirect:/dorama/" + ld.getIdDorama();
    }

    //READ

    @GetMapping("/listas/minhas")
    public String mostrarLista(Model model, 
                               HttpSession session,
                               @RequestParam(defaultValue = "false") boolean erro){

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null)
            return "redirect:/login";

        List<Lista> listas = listaService.buscarListaPorUsuario(usuarioLogado.getIdUsuario());
        model.addAttribute("listas",listas);
        model.addAttribute("temListas",!listas.isEmpty());

        model.addAttribute("erro", erro);

        return "listas-minhas";
    }

    //Página da lista:
    @GetMapping("/listas/{idLista}")
    public String mostrarPaginaLista(Model model, 
                                    @PathVariable int idLista,
                                    HttpSession session){

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null)
            return "redirect:/login";

        Lista li = listaService.buscarListaPorId(idLista);
        model.addAttribute("lista",li);

        //Doramas da lista:
        List<Dorama> listaDoramas = doramaService.buscarDoramasPorLista(idLista);
        model.addAttribute("doramas",listaDoramas);

        int totalDoramas = listaService.contarDoramasEmLista(idLista);
        model.addAttribute("totalDoramas",totalDoramas);
        
        return "pagina-lista";
    }

    //UPDATE:
    
    @GetMapping("/listas/{idLista}/atualizar") 
    public String editarLista(Model model, 
                             @PathVariable int idLista,
                             HttpSession session){

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null)
            return "redirect:/login";

        model.addAttribute("id",idLista); 
        Lista listaAnterior = listaService.buscarListaPorId(idLista);
        model.addAttribute("lista",listaAnterior);
        return "lista-editar";
    }

    @PostMapping("/listas/{idLista}/atualizar")
    public String atualizarLista(Model model, 
                                @PathVariable int idLista, 
                                @ModelAttribute Lista lista,
                                HttpSession session){

        //Verifica dados do usuário logado
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        lista.setIdUsuario(usuarioLogado.getIdUsuario());

        listaService.atualizarLista(idLista, lista);
		return "redirect:/listas/minhas";
    }

    //DELETE:
    @PostMapping("/listas/{idLista}/remover-dorama")
    public String removerDorama(@PathVariable int idLista,
                                @RequestParam int idDorama,
                                HttpSession session){
        
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null)
            return "redirect:/login";

        ListaDorama ld = new ListaDorama(idLista, idDorama);
        listaDoramaService.removerDoramaDaLista(ld);
        return "redirect:/listas/" + idLista;
    }

    @PostMapping("/listas/{idLista}/deletar")
    public String deletarLista(@PathVariable int idLista,
                               HttpSession session){

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null)
            return "redirect:/login";

        //Se há doramas na lista, retorna erro ao tentar excluir:
        if(listaService.contarDoramasEmLista(idLista)>0)
            return "redirect:/listas/minhas?erro=true";

        listaService.deletarLista(idLista);
		return "redirect:/listas/minhas";
    }

}
