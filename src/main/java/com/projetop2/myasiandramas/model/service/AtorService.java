package com.projetop2.myasiandramas.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetop2.myasiandramas.model.Ator;
import com.projetop2.myasiandramas.model.dao.AtorDAO;

@Service
public class AtorService {

    @Autowired
    AtorDAO adao;

    public void inserirAtor(Ator a){
        adao.inserirAtor(a);
    }   

    public boolean atorExistePorIdTmdb(int idTmdbAtor) {
    return adao.existePorIdTmdb(idTmdbAtor);
    }  

    public int buscarIdAtorPorIdTmdb(int idTmdbAtor) {
    return adao.buscarIdPorIdTmdb(idTmdbAtor);
    }
    
}
