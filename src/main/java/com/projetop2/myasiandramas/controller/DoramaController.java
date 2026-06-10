/*
    Rotas para a funcionalidade de gerenciar doramas (Criar/atualizar)
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
import com.projetop2.myasiandramas.model.DoramaGenero;
import com.projetop2.myasiandramas.model.PaisDorama;
import com.projetop2.myasiandramas.model.StatusDorama;
import com.projetop2.myasiandramas.model.Usuario;
import com.projetop2.myasiandramas.model.service.DoramaGeneroService;
import com.projetop2.myasiandramas.model.service.DoramaService;
import com.projetop2.myasiandramas.model.service.GeneroService;

import jakarta.servlet.http.HttpSession;


@Controller
public class DoramaController {

    @Autowired
    private DoramaService doramaService;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private DoramaGeneroService doramaGeneroService;

    //CREATE:

    @GetMapping("/dorama/criar")
    public String formCriarDorama(Model model,
                                 HttpSession session){

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) //Se não há nenhum usuário logado, redireciona para fazer o login
            return "redirect:/login";

        model.addAttribute("criarDorama",new Dorama());
        //Opções de países:
        model.addAttribute("paises", PaisDorama.values());
        //Opções de status:
        model.addAttribute("status", StatusDorama.values());

        return "dorama-criar";
    }

    @PostMapping("/dorama/criar")
    public String formCriarDorama(@ModelAttribute Dorama dorama, 
                                 HttpSession session){

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado"); //Caso a sessão expire entre GET e POST
        if (usuarioLogado == null) 
            return "redirect:/login";
        
		int idDorama = doramaService.inserirDorama(dorama);
		return "redirect:/dorama/" + idDorama;
    }

    //UPDATE:

    @GetMapping("/dorama/{idDorama}/editar") 
    public String editarDorama(Model model, 
                             @PathVariable int idDorama,
                             HttpSession session){

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null)
            return "redirect:/login";

        model.addAttribute("id",idDorama); 
        Dorama doramaAnterior = doramaService.buscarDoramaPorId(idDorama);
        model.addAttribute("dorama",doramaAnterior);

        //Opções de países:
        model.addAttribute("paises", PaisDorama.values());
        //Opções de status:
        model.addAttribute("status", StatusDorama.values());
        return "dorama-editar";
    }

    @PostMapping("/dorama/{idDorama}/editar")
    public String atualizarDorama(@PathVariable int idDorama, 
                                @ModelAttribute Dorama dorama,
                                HttpSession session){

        //Verifica dados do usuário logado
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null)
            return "redirect:/login";

        doramaService.atualizarDorama(idDorama, dorama);
		return "redirect:/dorama/" + idDorama;
    }

    //Insere Gênero em Dorama
    
    @GetMapping("/dorama/{idDorama}/adicionar-genero")
    public String adicionarGeneroEmDorama(Model model, 
                                       @PathVariable int idDorama,
                                       HttpSession session){

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) 
            return "redirect:/login";

        Dorama d = doramaService.buscarDoramaPorId(idDorama);
        model.addAttribute("dorama",d);

        //Opções de gêneros
        model.addAttribute("generos", generoService.buscarTodosGeneros());

        return "dorama-adicionar-genero";
    }

    @PostMapping("/dorama/adicionar-genero")
    public String adicionarGeneroEmDorama(@RequestParam List<Integer> idGenero,
                                          @RequestParam int idDorama,
                                          HttpSession session){

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) 
            return "redirect:/login";

        for(int idGen : idGenero){
            if(!doramaGeneroService.generoJaExisteNoDorama(idDorama, idGen)){
                DoramaGenero dg = new DoramaGenero(idDorama,idGen);
                doramaGeneroService.inserirDoramaGenero(dg);
            }
            
        }
        return "redirect:/dorama/" + idDorama;
    }

    //Remove Gênero do Dorama
    
    @GetMapping("/dorama/{idDorama}/remover-genero")
    public String removerGeneroDoDorama(Model model, 
                                       @PathVariable int idDorama,
                                       HttpSession session){

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) 
            return "redirect:/login";

        Dorama d = doramaService.buscarDoramaPorId(idDorama);
        model.addAttribute("dorama",d);

        //Gêneros incluídos
        model.addAttribute("generos", generoService.buscarGenerosPorDorama(idDorama));

        return "dorama-remover-genero";
    }

    @PostMapping("/dorama/remover-genero")
    public String removerGeneroDoDorama( @RequestParam List<Integer> idGenero,
                                         @RequestParam int idDorama,
                                          HttpSession session){

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) 
            return "redirect:/login";

        for(int idGen : idGenero){
            if(doramaGeneroService.generoJaExisteNoDorama(idDorama, idGen)){
                DoramaGenero dg = new DoramaGenero(idDorama,idGen);
                doramaGeneroService.removerGeneroDoDorama(dg);
            }    
        }
        return "redirect:/dorama/" + idDorama;
    }

}
