package com.projetop2.myasiandramas.model.dao;

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

    public void inserirGenero(Genero g){
        String sql = "INSERT INTO tb_generos(id_tmdb_genero, descricao) VALUES(?,?)";
        Object[] obj = new Object[2];
        obj[0] = (int) g.getIdTmdbGenero();
        obj[1] = (String) g.getDescricao();
        
        jdbc.update(sql,obj);
    }

}
