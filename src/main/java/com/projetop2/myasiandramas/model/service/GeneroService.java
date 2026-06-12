package com.projetop2.myasiandramas.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetop2.myasiandramas.model.Genero;
import com.projetop2.myasiandramas.model.dao.GeneroDAO;

@Service
public class GeneroService {

    @Autowired
    GeneroDAO gdao;

    public boolean generoExistePorIdTmdb(int idTmdbGenero) {
    return gdao.existePorIdTmdb(idTmdbGenero); 
    }

    public int buscarIdGeneroPorIdTmdb(int idTmdbGenero) {
    return gdao.buscarIdPorIdTmdb(idTmdbGenero);
    }

    //CREATE:

    public void inserirGenero(Genero g){
        gdao.inserirGenero(g);
    }   

    //READ:
 
    public List<Genero> buscarTodosGeneros(){
        return gdao.buscarTodosGeneros();
    }

    public List<Genero> buscarGenerosPorDorama(int idDorama){
        return gdao.buscarGenerosPorDorama(idDorama);
    }

}
