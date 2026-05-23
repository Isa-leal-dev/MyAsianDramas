package com.projetop2.myasiandramas.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetop2.myasiandramas.model.Genero;
import com.projetop2.myasiandramas.model.dao.GeneroDAO;

@Service
public class GeneroService {

    @Autowired
    GeneroDAO gdao;

    public void inserirGenero(Genero g){
        gdao.inserirGenero(g);
    }   

    public boolean generoExistePorIdTmdb(int idTmdbGenero) {
    return gdao.existePorIdTmdb(idTmdbGenero); 
    }

    public int buscarIdGeneroPorIdTmdb(int idTmdbGenero) {
    return gdao.buscarIdPorIdTmdb(idTmdbGenero);
    }

}
