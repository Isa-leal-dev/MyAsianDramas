package com.projetop2.myasiandramas.model.dao;

import org.springframework.stereotype.Repository;

import com.projetop2.myasiandramas.model.Lista;

@Repository
public class ListaDAO extends BaseDAO {

    public void inserirLista(Lista l){
        String sql = "INSERT INTO tb_listas (id_usuario, nome_lista, descricao) VALUES(?,?,?)";
        Object[] obj = new Object[3];
        obj[0] = (int) l.getIdUsuario();
        obj[1] = (String) l.getNomeLista(); 
        obj[2] = (String) l.getDescricao();
        jdbc.update(sql,obj);
    }

}
