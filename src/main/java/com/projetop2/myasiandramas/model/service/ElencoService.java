package com.projetop2.myasiandramas.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetop2.myasiandramas.model.Elenco;
import com.projetop2.myasiandramas.model.dao.ElencoDAO;

@Service
public class ElencoService {

    @Autowired
    ElencoDAO edao;
    
    public void inserirElenco(Elenco e){
        edao.inserirElenco(e);
    } 

}
