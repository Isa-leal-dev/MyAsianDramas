package com.projetop2.myasiandramas.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetop2.myasiandramas.model.ListaDorama;
import com.projetop2.myasiandramas.model.dao.ListaDoramaDAO;

@Service
public class ListaDoramaService {

    @Autowired
    ListaDoramaDAO lddao;

    public void inserirDoramaEmLista(ListaDorama ld){
        lddao.inserirDoramaEmLista(ld);
    } 

}
