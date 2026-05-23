package com.projetop2.myasiandramas.model.dao;

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

}
