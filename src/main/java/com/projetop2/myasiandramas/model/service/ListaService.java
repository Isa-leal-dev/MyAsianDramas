package com.projetop2.myasiandramas.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetop2.myasiandramas.model.Lista;
import com.projetop2.myasiandramas.model.dao.ListaDAO;

@Service
public class ListaService {

    @Autowired
    ListaDAO ldao;
    
    public void inserirLista(Lista l){
        ldao.inserirLista(l);
    } 

}
