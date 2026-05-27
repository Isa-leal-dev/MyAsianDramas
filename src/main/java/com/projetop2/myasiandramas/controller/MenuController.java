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
import com.projetop2.myasiandramas.model.PaisDorama;
import com.projetop2.myasiandramas.model.service.AtorService;
import com.projetop2.myasiandramas.model.service.DoramaService;
import com.projetop2.myasiandramas.model.service.GeneroService;
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

    @GetMapping("/")
    public String landingPage(Model model){
        //14 Doramas em Andamento:
        List<Dorama> listaAndamento = doramaService.buscarDoramasPorStatusLimitado(14);
        model.addAttribute("doramas",listaAndamento);

        //Gêneros:
        List<Genero> opcoesGeneros = generoService.buscarTodosGeneros();
        model.addAttribute("generos", opcoesGeneros);


        return "landing";
    }

    //CREATE - LISTAS:

    @GetMapping("/criar-lista")
    public String formCriarLista(Model model){
        model.addAttribute("criarLista",new Lista());
        return "criar-lista";
    }

    @PostMapping("/criar-lista")
    public String formCriarLista(@ModelAttribute Lista li, Model model){
        ListaService ls = context.getBean(ListaService.class);//pega o bean de ListaService
        li.setIdUsuario(1); //Usuário 1 fixo por enquanto
		ls.inserirLista(li);
		return "redirect:/minhas-listas";
    }

    //READ - LISTAS
    @GetMapping("/minhas-listas") //Apenas para mostrar (não implementado totalmente ainda)
    public String mostrarLista(Model model){
        //model.addAttribute("mostraLista",);
        return "minhas-listas";
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

}