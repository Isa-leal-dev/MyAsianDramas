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

    //READ:

    public boolean doramaJaExisteNaLista(int idLista, int idDorama){
        String sql = "SELECT COUNT(*) FROM tb_lista_doramas WHERE id_lista = ? AND id_dorama = ?";
        Integer count = jdbc.queryForObject(sql,Integer.class, idLista, idDorama);
        return count > 0;
    }

    //DELETE:
    public void removerDoramaDaLista(ListaDorama ld){
        String sql = "DELETE FROM tb_lista_doramas WHERE id_lista = ? AND id_dorama = ?";
        jdbc.update(sql,ld.getIdLista(),ld.getIdDorama());
    }

}
