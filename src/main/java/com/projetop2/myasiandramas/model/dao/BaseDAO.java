package com.projetop2.myasiandramas.model.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.annotation.PostConstruct;

public abstract class BaseDAO {
    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    // Verificar existência na tabela por id_tmdb
    protected boolean existePorIdTmdb(String tabela, String campo, int idTmdb) {
        Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM " + tabela + " WHERE " + campo + " = ?", Integer.class, idTmdb);
        return count > 0;
    }

    //Buscar id interno por id_tmdb
    protected int buscarIdPorIdTmdb(String idInterno, String tabela, String campo, int idTmdb) {
        return jdbc.queryForObject("SELECT " + idInterno + " FROM " + tabela + " WHERE " + campo + " = ?",Integer.class, idTmdb);
    }

}
