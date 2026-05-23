package com.projetop2.myasiandramas.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetop2.myasiandramas.model.Dorama;
import com.projetop2.myasiandramas.model.dao.DoramaDAO;

@Service
public class DoramaService {

    @Autowired
    DoramaDAO ddao;

    public int inserirDorama(Dorama d){
        return ddao.inserirDorama(d);
    }   

    public int contarDoramas() {
    return ddao.contarDoramas();//Verifica quantos doramas estão inseridos em tb_doramas
    }  

}
