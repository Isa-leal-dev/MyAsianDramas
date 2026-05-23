package com.projetop2.myasiandramas.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetop2.myasiandramas.model.DoramaGenero;
import com.projetop2.myasiandramas.model.dao.DoramaGeneroDAO;

@Service
public class DoramaGeneroService {

    @Autowired
    DoramaGeneroDAO dgdao;

    public void inserirDoramaGenero(DoramaGenero dg){
        dgdao.inserirDoramaGenero(dg);
    }  
}
