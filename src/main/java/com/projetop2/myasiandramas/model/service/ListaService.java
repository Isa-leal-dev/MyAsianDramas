package com.projetop2.myasiandramas.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetop2.myasiandramas.model.Lista;
import com.projetop2.myasiandramas.model.dao.ListaDAO;

@Service
public class ListaService {

    @Autowired
    ListaDAO ldao;

    //CREATE:
    
    public void criarLista(Lista l){
        ldao.criarLista(l);
    } 

    //READ:

    public Lista buscarListaPorId(int idLista){
        return ldao.buscarListaPorId(idLista);
    } 

    public List<Lista> buscarListaPorUsuario(int idUsuario){
        return ldao.buscarListaPorUsuario(idUsuario);
    }

    public int contarDoramasEmLista(int idLista){
        return ldao.contarDoramasEmLista(idLista);
    }

    //UPDATE:
    public void atualizarLista(int id, Lista atualizada){
        ldao.atualizarLista(id, atualizada);
    }

    //DELETE:
    public void deletarLista(int idLista){
        ldao.deletarLista(idLista);
    }

    public boolean podeDeletar(int idLista){
        int count = ldao.contarDoramasEmLista(idLista);
        return count == 0;
    }

}
