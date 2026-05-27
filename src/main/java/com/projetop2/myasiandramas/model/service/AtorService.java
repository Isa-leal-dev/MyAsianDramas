package com.projetop2.myasiandramas.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetop2.myasiandramas.model.Ator;
import com.projetop2.myasiandramas.model.dao.AtorDAO;

@Service
public class AtorService {

    @Autowired
    AtorDAO adao;

    public boolean atorExistePorIdTmdb(int idTmdbAtor) {
    return adao.existePorIdTmdb(idTmdbAtor);
    }  

    public int buscarIdAtorPorIdTmdb(int idTmdbAtor) {
    return adao.buscarIdPorIdTmdb(idTmdbAtor);
    }

    //CREATE:

    public void inserirAtor(Ator a){
        adao.inserirAtor(a);
    }   

    //READ:

    public Ator buscarAtorPorId(int idAtor){
        return adao.buscarAtorPorId(idAtor);
    }

    public List<Ator> buscarAtoresPorDorama(int idDorama){
        return adao.buscarAtoresPorDorama(idDorama);
    }
    
}
