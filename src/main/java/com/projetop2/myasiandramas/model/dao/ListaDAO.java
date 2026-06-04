package com.projetop2.myasiandramas.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.projetop2.myasiandramas.model.Lista;

@Repository
public class ListaDAO extends BaseDAO {

    //CREATE:
    public void criarLista(Lista l){
        String sql = "INSERT INTO tb_listas (id_usuario, nome_lista, descricao) VALUES(?,?,?)";
        Object[] obj = new Object[3];
        obj[0] = (int) l.getIdUsuario();
        obj[1] = (String) l.getNomeLista(); 
        obj[2] = (String) l.getDescricao();
        jdbc.update(sql,obj);
    }

    //READ:
    public Lista buscarListaPorId(int idLista){
        String sql = "SELECT * FROM tb_listas WHERE id_lista = ?";
        Map<String,Object> registros = (Map<String,Object>) jdbc.queryForMap(sql,idLista);        
        return Lista.converterRegistros(registros);
    }

    public List<Lista> buscarListaPorUsuario(int idUsuario){
        String sql = "SELECT * FROM tb_listas WHERE id_usuario = ?";
        List<Map<String,Object>> listaRegistros = jdbc.queryForList(sql, idUsuario);
        return Lista.converterLista(listaRegistros);
    }

    public int contarDoramasEmLista(int idLista) {
        String sql = "SELECT COUNT(*) FROM tb_lista_doramas WHERE id_lista = ?";
        Integer total = jdbc.queryForObject(sql,Integer.class,idLista);
        return total;
    }

    //UPDATE:
    public void atualizarLista(int idLista, Lista atualizada){
        String sql = "UPDATE tb_listas SET nome_lista = ? ,descricao = ? where id_lista = ?";
        Object[] obj = new Object[3];
        obj[0] = atualizada.getNomeLista(); 
        obj[1] = atualizada.getDescricao(); 
        obj[2] = idLista;
        jdbc.update(sql,obj);
    }

    //DELETE:
    public void deletarLista(int idLista){
        String sql = "DELETE FROM tb_listas WHERE id_lista = ?";
        jdbc.update(sql,idLista);
    }

}
