package com.projetop2.myasiandramas.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.projetop2.myasiandramas.model.Elenco;

@Repository
public class ElencoDAO extends BaseDAO{

    public void inserirElenco(Elenco e){
        String sql = "INSERT INTO tb_elenco(id_dorama, id_ator, personagem) VALUES(?,?,?)";
        Object[] obj = new Object[3];
        obj[0] = (int) e.getIdDorama(); 
        obj[1] = (int) e.getIdAtor();
        obj[2] = (String) e.getPersonagem();
        jdbc.update(sql,obj);
    }

    public List<Elenco> verificarPersonagem(int idDorama){
        String sql = "SELECT * FROM tb_elenco WHERE id_dorama = ?";
        List<Map<String,Object>> listaRegistros = jdbc.queryForList(sql, idDorama);
        return Elenco.converterLista(listaRegistros);
    }

}
