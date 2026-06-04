package com.projetop2.myasiandramas.model.dao;

import org.springframework.stereotype.Repository;

import com.projetop2.myasiandramas.model.ListaDorama;

@Repository
public class ListaDoramaDAO extends BaseDAO{

    //CREATE:
    public void inserirDoramaEmLista(ListaDorama ld){
        String sql = "INSERT INTO tb_lista_doramas(id_lista, id_dorama) VALUES(?,?)";
        Object[] obj = new Object[2];
        obj[0] = (int) ld.getIdLista(); 
        obj[1] = (int) ld.getIdDorama();
        jdbc.update(sql,obj);
    }

    //DELETE:
    public void removerDoramaDaLista(ListaDorama ld){
        String sql = "DELETE FROM tb_lista_doramas WHERE id_lista = ? AND id_dorama = ?";
        jdbc.update(sql,ld.getIdLista(),ld.getIdDorama());
    }

}
