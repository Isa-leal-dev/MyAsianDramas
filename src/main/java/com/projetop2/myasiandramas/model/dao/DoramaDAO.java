package com.projetop2.myasiandramas.model.dao;

import java.time.LocalDate;

import org.springframework.stereotype.Repository;

import com.projetop2.myasiandramas.model.Dorama;

@Repository
public class DoramaDAO extends BaseDAO{

    public int contarDoramas() {
    Integer countDoramas = jdbc.queryForObject("SELECT COUNT(*) FROM tb_doramas", Integer.class);
    return countDoramas;
    //Precisa ser boxed, para converter resultado do postgre em classe Integer
    }

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
    
}