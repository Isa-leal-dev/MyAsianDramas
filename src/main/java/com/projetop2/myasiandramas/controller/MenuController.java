/*
    Rotas para landing page, busca e navegação geral
*/
package com.projetop2.myasiandramas.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.projetop2.myasiandramas.model.Ator;
import com.projetop2.myasiandramas.model.Dorama;
import com.projetop2.myasiandramas.model.Elenco;
import com.projetop2.myasiandramas.model.Genero;
import com.projetop2.myasiandramas.model.PaisDorama;
import com.projetop2.myasiandramas.model.service.AtorService;
import com.projetop2.myasiandramas.model.service.DoramaService;
import com.projetop2.myasiandramas.model.service.ElencoService;
import com.projetop2.myasiandramas.model.service.GeneroService;

@Controller
public class MenuController {

    @Autowired
    private DoramaService doramaService; //DoramaService ds = context.getBean(DoramaService.class);
    @Autowired
    private AtorService atorService; 
    @Autowired
    private GeneroService generoService;
    @Autowired
    private ElencoService elencoService;

    @GetMapping("/")
    public String landingPage(Model model, 
                              @RequestParam(required = false) Integer idGenero,
                              @RequestParam(required = false) Integer mes){
        
        //Carrega o primeiro gênero por padrão quando ainda não foi selecionado nenhum:
        if (idGenero == null)
            idGenero = 1;
        //Carrega o mês atual por padrão:
        if (mes == null)
            mes = LocalDate.now().getMonthValue();
        
        //Doramas em Andamento (14):
        List<Dorama> listaAndamento = doramaService.buscarDoramasPorStatusLimitado(14);
        model.addAttribute("doramas",listaAndamento);

        //Doramas por gênero:
        List<Dorama> listaPorGenero = doramaService.buscarDoramasPorGenero(idGenero,21);
        model.addAttribute("doramasGenero",listaPorGenero);
        model.addAttribute("generoSelecionado", idGenero);
            //Botões:
            List<Genero> opcoesGeneros = generoService.buscarTodosGeneros();
            model.addAttribute("generos", opcoesGeneros);
            
        //Lançamentos do Ano:
        List<Dorama> listaAno = doramaService.buscarDoramasPorMes(mes);
        model.addAttribute("mesSelecionado", mes); //Para adicionar classe de ativo no botão selecionado
            //Agrupamento de doramas por data
            Map<LocalDate, List<Dorama>> lancamentos = new LinkedHashMap<>();//LinkedHashMap mantém a ordem do que foi inserido
                for(Dorama d : listaAno){
                    LocalDate data = d.getDataEstreia();
                    if(!lancamentos.containsKey(data)){//Se a data não está mapeada
                        lancamentos.put(data, new ArrayList<>());//coloca a data no Map<LocalDate, e cria ArrayList, por enquanto vazio
                    }
                    lancamentos.get(data).add(d);//Sempre insere o dorama
                }
                model.addAttribute("lancamentos", lancamentos);

        return "landing";
    }

    //READ - DORAMAS
    //Busca de navbar:
     @GetMapping("/resultados-busca")
    public String mostrarDoramasBuscados(Model model, @RequestParam String titulo){
        List<Dorama> listaDoramas = doramaService.buscarDoramasPorTitulo(titulo);
        model.addAttribute("doramas",listaDoramas);
        model.addAttribute("temDoramas",!listaDoramas.isEmpty());

        List<Ator> listaAtores = atorService.buscarAtoresPorNome(titulo);
        model.addAttribute("atores",listaAtores);
        model.addAttribute("temAtores",!listaAtores.isEmpty());

        model.addAttribute("termoBuscado", titulo);

        return "resultados-busca";
    }
    
    //Página do dorama:
    @GetMapping("/dorama/{id}")
    public String mostrarPaginaDorama(Model model, @PathVariable int id){
        Dorama d = doramaService.buscarDoramaPorId(id);
        model.addAttribute("dorama",d);
        
        //Gêneros:
        List<Genero> generos = generoService.buscarGenerosPorDorama(id);
        model.addAttribute("generos",generos);

        //Elenco:
        List<Ator> atores = atorService.buscarAtoresPorDorama(id);
        model.addAttribute("atores",atores);
            //Personagem:
            List<Elenco> personagens = elencoService.verificarPersonagem(id);
            model.addAttribute("personagens",personagens);
            
        return "pagina-dorama";
    }
    
    //Ver Mais em "Doramas em andamento"
    @GetMapping("/lista/dorama/em-andamento")
    public String mostrarDoramasEmAndamento(Model model){
        List<Dorama> lista = doramaService.buscarEmAndamento();
        model.addAttribute("doramas",lista);

        model.addAttribute("tituloPagina", "Doramas em Andamento");
        model.addAttribute("mostrarBandeira", true);

        return "doramas-resultados";
    }

    //Página Doramas por país:
    @GetMapping("/doramas/{pais}")
    public String mostrarDoramaPorPais(Model model, @PathVariable PaisDorama pais){
        model.addAttribute("pais",pais);
        List<Dorama> lista = doramaService.buscarDoramasPorPais(pais);
        model.addAttribute("doramas",lista);

        model.addAttribute("tituloPagina", pais.getDescricao());
        model.addAttribute("mostrarBandeira", false);

        return "doramas-resultados";
    }

    //Todos os Doramas:
    @GetMapping("/doramas/todos")
    public String mostrarTodosDoramas(Model model){
        List<Dorama> todosDoramas = doramaService.buscarTodosDoramas();
        model.addAttribute("doramas",todosDoramas);

        model.addAttribute("tituloPagina", "Todos os Doramas");
        model.addAttribute("mostrarBandeira", true);

        return "doramas-resultados";
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