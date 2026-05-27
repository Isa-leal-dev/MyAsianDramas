package com.projetop2.myasiandramas.model.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.projetop2.myasiandramas.model.Dorama;
import com.projetop2.myasiandramas.model.PaisDorama;
import com.projetop2.myasiandramas.model.StatusDorama;

@Repository
public class DoramaDAO extends BaseDAO{

    public int contarDoramas() {
    Integer countDoramas = jdbc.queryForObject("SELECT COUNT(*) FROM tb_doramas", Integer.class);
    return countDoramas;
    //Precisa ser boxed, para converter resultado do postgre em classe Integer
    }

    //CREATE:

    public int inserirDorama(Dorama d){
        String sql = "INSERT INTO tb_doramas(data_estreia, data_final, emissora_original, pais, poster, sinopse, status_dorama, titulo_ingles, titulo_nativo, titulo_portugues,numero_episodios) VALUES(?,?,?,?,?,?,?,?,?,?,?) RETURNING id_dorama";
        Object[] obj = new Object[11];
        obj[0] = (LocalDate) d.getDataEstreia(); //downcast
        obj[1] = (LocalDate) d.getDataFinal();
        obj[2] = (String) d.getEmissoraOriginal();
        obj[3] = d.getPais().name();
        obj[4] = (String) d.getPoster();
        obj[5] = (String) d.getSinopse();
        obj[6] = d.getStatusDorama().name();
        obj[7] = (String) d.getTituloIngles();
        obj[8] = (String) d.getTituloNativo();
        obj[9] = (String) d.getTituloPortugues();
        obj[10] = (int) d.getNumeroEpisodios();

        //Retorna o último id inserido em tb_doramas para usar como FK em outras tabelas:
        return jdbc.queryForObject(sql, Integer.class, obj); 
    }

    //READ:
    public Dorama buscarDoramaPorId(int idDorama){
        String sql = "SELECT * FROM tb_doramas WHERE id_dorama = ?";
        Map<String,Object> registros = (Map<String,Object>) jdbc.queryForMap(sql,idDorama);        
        return Dorama.converterRegistros(registros);
    }   

    public List<Dorama> buscarDoramasPorTitulo(String titulo){
        String sql = "SELECT * FROM tb_doramas WHERE titulo_portugues ILIKE ? OR titulo_nativo ILIKE ? OR titulo_ingles ILIKE ?";
        String tituloBuscado = "%"+titulo+"%";
        List<Map<String,Object>> listaRegistros = jdbc.queryForList(sql, tituloBuscado, tituloBuscado, tituloBuscado);
        return Dorama.converterLista(listaRegistros);       
    }

    public List<Dorama> buscarDoramasPorStatus(StatusDorama sd){
        String sql = "SELECT * FROM tb_doramas WHERE status_dorama = ?";
        List<Map<String,Object>> listaRegistros = jdbc.queryForList(sql, sd.name());
        return Dorama.converterLista(listaRegistros);
    } 

    public List<Dorama> buscarDoramasPorStatusLimitado(StatusDorama sd, int limite){ //Para landing.html
    String sql = "SELECT * FROM tb_doramas WHERE status_dorama = ? ORDER BY data_final DESC LIMIT ?";
    List<Map<String,Object>> listaRegistros = jdbc.queryForList(sql, sd.name(), limite);
    return Dorama.converterLista(listaRegistros);
    }

    public List<Dorama> buscarDoramasPorPais(PaisDorama pd){
        String sql = "SELECT * FROM tb_doramas WHERE pais = ?";
        List<Map<String,Object>> listaRegistros = jdbc.queryForList(sql, pd.name());
        return Dorama.converterLista(listaRegistros); 
    } 

    public List<Dorama> buscarTodosDoramas(){
        String sql = "SELECT * FROM tb_doramas";
        List<Map<String,Object>> listaRegistros = jdbc.queryForList(sql);
        return Dorama.converterLista(listaRegistros);
    }

    public List<Dorama> buscarDoramasPorMes(int mes){
        String sql = "SELECT * FROM tb_doramas WHERE EXTRACT(YEAR FROM data_estreia) = EXTRACT(YEAR FROM CURRENT_DATE) AND EXTRACT(MONTH FROM data_estreia) = ? ORDER BY data_estreia ASC";
        List<Map<String,Object>> listaRegistros = jdbc.queryForList(sql,mes);
        return Dorama.converterLista(listaRegistros);
    }

    public List<Dorama> buscarDoramasPorAtor(int idAtor){
        String sql = "SELECT d. * FROM tb_doramas d " +
                    "JOIN tb_elenco e ON d.id_dorama = e.id_dorama " +
                    "WHERE e.id_ator = ?";
        List<Map<String,Object>> listaRegistros = jdbc.queryForList(sql, idAtor);
        return Dorama.converterLista(listaRegistros);
    }

    public List<Dorama> buscarDoramasPorGenero(int idGenero){
        String sql = "SELECT d.* FROM tb_doramas d " +
                    "JOIN tb_doramas_generos dg ON d.id_dorama = dg.id_dorama " +
                    "WHERE dg.id_genero = ?";
        List<Map<String,Object>> listaRegistros = jdbc.queryForList(sql, idGenero);
        return Dorama.converterLista(listaRegistros);
    }

   
}