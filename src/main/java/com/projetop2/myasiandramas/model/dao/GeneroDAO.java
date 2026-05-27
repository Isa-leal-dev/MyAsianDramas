package com.projetop2.myasiandramas.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.projetop2.myasiandramas.model.Genero;

@Repository
public class GeneroDAO extends BaseDAO{

    public boolean existePorIdTmdb(int idTmdbGenero){
        return existePorIdTmdb("tb_generos", "id_tmdb_genero", idTmdbGenero);//De classe abstrata
    }

    public int buscarIdPorIdTmdb(int idTmdbGenero){
        return buscarIdPorIdTmdb("id_genero", "tb_generos","id_tmdb_genero", idTmdbGenero);//De classe abstrata
    }

    //CREATE:

    public void inserirGenero(Genero g){
        String sql = "INSERT INTO tb_generos(id_tmdb_genero, descricao) VALUES(?,?)";
        Object[] obj = new Object[2];
        obj[0] = (int) g.getIdTmdbGenero();
        obj[1] = (String) g.getDescricao();
        
        jdbc.update(sql,obj);
    }

    //READ:

    public List<Genero> buscarTodosGeneros(){
        String sql = "SELECT * FROM tb_generos";
        List<Map<String,Object>> listaRegistros = jdbc.queryForList(sql);
        return Genero.converterLista(listaRegistros);
    }

}
