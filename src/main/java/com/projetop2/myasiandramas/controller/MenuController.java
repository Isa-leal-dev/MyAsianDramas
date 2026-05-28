package com.projetop2.myasiandramas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projetop2.myasiandramas.model.Ator;
import com.projetop2.myasiandramas.model.Dorama;
import com.projetop2.myasiandramas.model.Genero;
import com.projetop2.myasiandramas.model.Lista;
import com.projetop2.myasiandramas.model.ListaDorama;
import com.projetop2.myasiandramas.model.PaisDorama;
import com.projetop2.myasiandramas.model.service.AtorService;
import com.projetop2.myasiandramas.model.service.DoramaService;
import com.projetop2.myasiandramas.model.service.GeneroService;
import com.projetop2.myasiandramas.model.service.ListaDoramaService;
import com.projetop2.myasiandramas.model.service.ListaService;

@Controller
public class MenuController {

    @Autowired
    ApplicationContext context;
    @Autowired
    private DoramaService doramaService; //DoramaService ds = context.getBean(DoramaService.class);
    @Autowired
    private AtorService atorService; 
    @Autowired
    private GeneroService generoService;
    @Autowired
    private ListaService listaService;
    @Autowired
    private ListaDoramaService listaDoramaService;

    @GetMapping("/")
    public String landingPage(Model model){
        //14 Doramas em Andamento:
        List<Dorama> listaAndamento = doramaService.buscarDoramasPorStatusLimitado(14);
        model.addAttribute("doramas",listaAndamento);

        //Gêneros:
        List<Genero> opcoesGeneros = generoService.buscarTodosGeneros();
        model.addAttribute("generos", opcoesGeneros);

        //Lançamentos do Ano:


        return "landing";
    }

    //CREATE - LISTAS:

    @GetMapping("/listas/criar")
    public String formCriarLista(Model model){
        model.addAttribute("criarLista",new Lista());
        return "lista-criar";
    }

    @PostMapping("/listas/criar")
    public String formCriarLista(@ModelAttribute Lista li, Model model){
        li.setIdUsuario(1); //Usuário 1 fixo por enquanto
		listaService.criarLista(li);
		return "redirect:/listas/minhas/1"; //Fixo por enquanto
    }

    //Insere Dorama em Lista

    @GetMapping("/listas/adicionar-dorama/{idDorama}")
    public String adicionarDoramaLista(Model model, @PathVariable int idDorama){
        Dorama d = doramaService.buscarDoramaPorId(idDorama);
        model.addAttribute("dorama",d);
        //Listas para escolher quando for adicionar dorama em lista:
        model.addAttribute("minhasListas", listaService.buscarListaPorUsuario(1));
        return "lista-adicionar-dorama";
    }

    @PostMapping("listas/adicionar-dorama")
    public String adicionarDorama(@ModelAttribute ListaDorama ld){
        listaDoramaService.inserirDoramaEmLista(ld);
        return "redirect:/dorama/" + ld.getIdDorama();
    }

    //READ - LISTAS

    @GetMapping("/listas/minhas/{idUsuario}")
    public String mostrarLista(Model model, @PathVariable int idUsuario){
        List<Lista> listas = listaService.buscarListaPorUsuario(idUsuario);
        model.addAttribute("listas",listas);
        return "listas-minhas";
    }

    //Página da lista:
    @GetMapping("/listas/{idLista}")
    public String mostrarPaginaLista(Model model, @PathVariable int idLista){
        Lista li = listaService.buscarListaPorId(idLista);
        model.addAttribute("lista",li);
        //Doramas da lista:
        List<Dorama> listaDoramas = doramaService.buscarDoramasPorLista(idLista);
        model.addAttribute("doramas",listaDoramas);
        
        return "pagina-lista";
    }

    //READ - DORAMAS
    //Busca de navbar:
     @GetMapping("/resultados-busca")
    public String mostrarDoramasBuscados(Model model, @RequestParam String titulo){
        List<Dorama> lista = doramaService.buscarDoramasPorTitulo(titulo);
        model.addAttribute("doramas",lista);
        return "resultados-busca";
    }
    
    //Página do dorama:
    @GetMapping("/dorama/{id}")
    public String mostrarPaginaDorama(Model model, @PathVariable int id){
        Dorama d = doramaService.buscarDoramaPorId(id);
        model.addAttribute("dorama",d);
        //Elenco:
        List<Ator> elenco = atorService.buscarAtoresPorDorama(id);
        model.addAttribute("elenco",elenco);
        //Listas para escolher quando for adicionar dorama em lista:
        model.addAttribute("minhasListas", listaService.buscarListaPorUsuario(1));
        return "pagina-dorama";
    }
    
    //Ver Mais em "Doramas em andamento"
    @GetMapping("/lista/dorama/em-andamento")
    public String mostrarDoramasEmAndamento(Model model){
        List<Dorama> lista = doramaService.buscarEmAndamento();
        model.addAttribute("doramas",lista);
        return "doramas-andamento";
    }

    //Página Doramas por país:
    @GetMapping("/doramas/{pais}")
    public String mostrarDoramaPorPais(Model model, @PathVariable PaisDorama pais){
        model.addAttribute("pais",pais);
        List<Dorama> lista = doramaService.buscarDoramasPorPais(pais);
        model.addAttribute("doramas",lista);
        return "dorama-pais";
    }

    //READ - ATORES

    //Página do Ator:
    @GetMapping("/ator/{idAtor}")
    public String mostrarPaginaAtor(Model model, @PathVariable int idAtor){
        Ator a = atorService.buscarAtorPorId(idAtor);
        model.addAttribute("ator",a);
        //Doramas onde ator atuou:
        List<Dorama> listaDoramas = doramaService.buscarDoramasPorAtor(idAtor);
        model.addAttribute("doramas",listaDoramas);
        return "pagina-ator";
    }

    //UPDATE - LISTA:
    //Atualizar lista
    @GetMapping("/listas/{id}/atualizar") //Apenas mostra a página para atualizar
    public String editarLista(Model model, @PathVariable int id){
        model.addAttribute("id",id); //ir para a página certa, th:action
        Lista listaAnterior = listaService.buscarListaPorId(id);
        model.addAttribute("lista",listaAnterior);
        return "lista-editar";
    }

    @PostMapping("/listas/{id}/atualizar") //@ModelAttribute = é o que digitou para atualizar
    public String atualizarLista(Model model, @PathVariable int id, @ModelAttribute Lista lista){
        listaService.atualizarLista(id, lista);
		return "redirect:/listas/minhas/1"; //Fixo por enquanto
    }

    
}