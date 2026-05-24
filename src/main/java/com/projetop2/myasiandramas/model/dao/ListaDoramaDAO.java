package com.projetop2.myasiandramas.model.dao;

import org.springframework.stereotype.Repository;

import com.projetop2.myasiandramas.model.ListaDorama;

@Repository
public class ListaDoramaDAO extends BaseDAO{

    public void inserirListaDorama(ListaDorama ld){
        String sql = "INSERT INTO tb_lista_doramas(id_lista, id_dorama, status_visualizacao) VALUES(?,?,?)";
        Object[] obj = new Object[3];
        obj[0] = (int) ld.getIdLista(); 
        obj[1] = (int) ld.getIdDorama();
        obj[2] = ld.getStatus().name();
        jdbc.update(sql,obj);
    }

}
