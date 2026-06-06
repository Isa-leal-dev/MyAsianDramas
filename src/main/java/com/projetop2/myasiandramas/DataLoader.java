package com.projetop2.myasiandramas;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projetop2.myasiandramas.model.Ator;
import com.projetop2.myasiandramas.model.Dorama;
import com.projetop2.myasiandramas.model.DoramaGenero;
import com.projetop2.myasiandramas.model.Elenco;
import com.projetop2.myasiandramas.model.Genero;
import com.projetop2.myasiandramas.model.PaisDorama;
import com.projetop2.myasiandramas.model.Sexo;
import com.projetop2.myasiandramas.model.StatusDorama;
import com.projetop2.myasiandramas.model.service.AtorService;
import com.projetop2.myasiandramas.model.service.DoramaGeneroService;
import com.projetop2.myasiandramas.model.service.DoramaService;
import com.projetop2.myasiandramas.model.service.ElencoService;
import com.projetop2.myasiandramas.model.service.GeneroService;

/*
    Para seed inicial na integração com API de The Movie DataBase (TMDB)
    Documentação TMDB:
    * Lista de doramas: https://developer.themoviedb.org/reference/discover-tv
    * Detalhes: https://developer.themoviedb.org/reference/tv-series-details
    * Créditos/Elenco: https://developer.themoviedb.org/reference/tv-series-credits
    * Imagens: https://developer.themoviedb.org/docs/image-basics

*/
@Component
public class DataLoader implements CommandLineRunner{
//Usando CommandLineRunner para garantir que run de DataLoader vai executar após inicialização dos outros componentes  
    @Autowired
    private DoramaService doramaService;
    @Autowired
    private AtorService atorService;
    @Autowired
    private ElencoService elencoService;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private DoramaGeneroService doramaGeneroService;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String urlBase = "https://api.themoviedb.org/3";
    @Value("${tmdb.api-key}")
    private String chaveApi;

    @Override
    public void run(String... args) throws Exception {
        if(doramaService.contarDoramas()>0) //Se já tiver doramas em tb_doramas, não continua a execução.
            return;

        // Cada página contém 20 doramas | 40 para KR, 20 para JP, CN, TH e TW
        String[] paises = {"KR", "JP", "CN", "TH", "TW"};
        int[] paginas = { 2, 1, 1, 1, 1}; 

        //For para cada país
        for (int i = 0; i < paises.length; i++) {
            String pais = paises[i];
            for (int pagina = 1; pagina <= paginas[i]; pagina++) {//For para cada página daquele país
                String url = urlBase 
                           + "/discover/tv?api_key=" + chaveApi 
                           + "&with_origin_country=" + pais 
                           + "&with_genres=18"
                           + "&without_genres=16"
                           + "&without_keywords=5970|209125"
                           + "&sort_by=popularity.desc&page=" + pagina;

                            /*URL de exemplo testada em navegador:
                            https://api.themoviedb.org/3
                            /discover/tv?api_key=
                            chaveAPI (placeholder por questões de segurança)
                            &with_origin_country=
                            KR
                            &with_genres=18
                            &without_genres=16
                            &without_keywords=wrestling|gourmet
                            &sort_by=popularity.desc&page=
                            1
                            */
                //pega o body(getForObject) do retorno e converte o resultado em Map<String,Object>
                Map resultado = restTemplate.getForObject(url, Map.class);
                //results = detalhes dos doramas. Dentro de results há outra lista de Map<String,Object>. Cast realizado
                List<Map> results = (List<Map>) resultado.get("results");

                for (Map item : results) {
                    System.out.println("Processando dorama: " + item.get("name") + " (País: " + pais + ", Página: " + pagina + ")");

                    //id do dorama em TMDB
                    int idTmdbDorama = (int) item.get("id");

                    // Monta URL para detalhes dos doramas
                    String urlDetalhes = urlBase + "/tv/" + idTmdbDorama + "?api_key=" + chaveApi;
                    Map detalhes = restTemplate.getForObject(urlDetalhes, Map.class);

                    // Extração dos dados dorama. Realizado cast após confirmar documentação TMDB
                    String tituloIngles = (String) item.get("name");
                    String tituloNativo = (String) item.get("original_name");
                    String posterPath = (String) item.get("poster_path");
                    String poster = posterPath != null ?
                        "https://image.tmdb.org/t/p/w500" + posterPath 
                        : null;

                    String dataEstreiaTexto = (String) item.get("first_air_date");
                    LocalDate dataEstreia = (dataEstreiaTexto != null && !dataEstreiaTexto.isBlank())
                        ? LocalDate.parse(dataEstreiaTexto)
                        : null;

                    String dataFinalTexto = (String) detalhes.get("last_air_date");
                    LocalDate dataFinal = (dataFinalTexto != null && !dataFinalTexto.isBlank())
                        ? LocalDate.parse(dataFinalTexto)
                        : null;

                    Integer episodios = (Integer) detalhes.get("number_of_episodes");
                    int numeroEpisodios = episodios != null ? episodios : 0;
                    String emissoraOriginal = null; //iniciando como null
                    //networks é array de objetos. Quero apenas a original/primeira (0)
                    List<Map> networks = (List<Map>) detalhes.get("networks");
                    if (networks != null && !networks.isEmpty()) {
                        emissoraOriginal = (String) networks.get(0).get("name");
                    }

                    String statusTmdb = (String) detalhes.get("status");
                    StatusDorama status = converterStatus(statusTmdb);

                    PaisDorama paisEnum = PaisDorama.valueOf(pais);//KR, JP, CN...

                    // Monta URL para detalhes dos doramas em português
                    String urlDetalhesPortugues = urlDetalhes + "&language=pt-BR";
                    Map detalhesPortugues = restTemplate.getForObject(urlDetalhesPortugues, Map.class);

                    String sinopse = (String) detalhesPortugues.get("overview");
                    String tituloPortugues = (String) detalhesPortugues.get("name");

                    Dorama dorama = new Dorama(
                        dataEstreia, dataFinal, emissoraOriginal, paisEnum, poster, sinopse, status,
                        tituloIngles, tituloNativo, tituloPortugues, numeroEpisodios);
                    int idDorama = doramaService.inserirDorama(dorama);
                    System.out.println("idDorama retornado: " + idDorama);

                    //para tb_generos e tb_doramas_generos (genres é um array): 
                    List<Map> generos = (List<Map>) detalhesPortugues.get("genres");
                    if (generos != null) {

                        for(Map g : generos){
                            int idTmdbGenero = (int) g.get("id");
                            String descricaoGenero = (String) g.get("name");

                            if (!generoService.generoExistePorIdTmdb(idTmdbGenero)) {//Se não existe, insere em tb_generos
                                Genero genero = new Genero(idTmdbGenero, descricaoGenero);
                                generoService.inserirGenero(genero);
                            }
                            // Insere em tb_doramas_generos
                            int idGenero = generoService.buscarIdGeneroPorIdTmdb(idTmdbGenero);
                            DoramaGenero doramaGenero = new DoramaGenero(idDorama, idGenero);
                            doramaGeneroService.inserirDoramaGenero(doramaGenero);
                        }//Fim verificação cada genero no array
                    }

                    //Para tb_atores e tb_elenco:
                    String urlCreditos = urlBase + "/tv/" + idTmdbDorama + "/credits?api_key=" + chaveApi;

                    Map creditos = restTemplate.getForObject(urlCreditos, Map.class);
                    //credits = detalhes do elenco
                    List<Map> credits = (List<Map>) creditos.get("cast");

                    //Extração dos dados ator/elenco
                    for (Map integrante : credits){
                        int idTmdbAtor = (int) integrante.get("id");
                        String nomeAtor = (String) integrante.get("name");
                        String nomeOriginal = (String) integrante.get("original_name");
                        String fotoPath = (String) integrante.get("profile_path");
                        String fotoPerfil = fotoPath != null ?
                        "https://image.tmdb.org/t/p/w500" + fotoPath 
                        : null;
                        int sexoTmdb = (int) integrante.get("gender");
                        Sexo sexo = sexoTmdb == 1 ? Sexo.F : sexoTmdb == 2 ? Sexo.M : null;
                        
                        String personagem = (String) integrante.get("character");

                        //Se o ator não existe em tb_atores, insere
                        if(!atorService.atorExistePorIdTmdb(idTmdbAtor)){
                            Ator ator = new Ator(idTmdbAtor, nomeAtor, nomeOriginal, fotoPerfil, sexo, null);
                            atorService.inserirAtor(ator);
                        }
                        //Insere em tb_elenco
                        int idAtor = atorService.buscarIdAtorPorIdTmdb(idTmdbAtor);
                        
                        // Verifica se o par idDorama+idAtor já existe em tb_elenco
                        if(!elencoService.elencoExiste(idDorama, idAtor)){
                            Elenco elenco = new Elenco(idDorama, idAtor, personagem);
                            elencoService.inserirElenco(elenco);
                        }
                    }//fim cada integrante/ator de elenco/creditos 

                }//Fim de cada item/dorama
            }//Fim for de cada página
        }//Fim for de cada país
    }

    private StatusDorama converterStatus(String statusTmdb) {
        if (statusTmdb == null) 
            return StatusDorama.DESCONHECIDO;

        switch(statusTmdb){
            case "Returning Series":
                return StatusDorama.EM_ANDAMENTO;
            case "Ended":
                return StatusDorama.CONCLUIDO;
            case "In Production":
            case "Planned":
                return StatusDorama.EM_BREVE;
            default:
                return StatusDorama.DESCONHECIDO;
        }
    }
}