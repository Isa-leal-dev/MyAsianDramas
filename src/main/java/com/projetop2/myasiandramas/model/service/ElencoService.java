package com.projetop2.myasiandramas.model.service;

import java.util.List;

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

    public List<Elenco> verificarPersonagem(int idDorama){
        return edao.verificarPersonagem(idDorama);
    }

    public boolean elencoExiste(int idDorama, int idAtor){
        return edao.elencoExiste(idDorama, idAtor);
    }

    public void removerElenco(int idDorama, int idAtor){
        edao.removerElenco(idDorama, idAtor);
    }

}
