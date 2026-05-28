package com.projetop2.myasiandramas.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lista {

    private int idLista, idUsuario;
    private String nomeLista, descricao;
    private LocalDateTime criadaEm;

    //Para form
    public Lista(){

    }

    //Para insert
    public Lista(int idUsuario, String nomeLista, String descricao) {
        this.idUsuario = idUsuario;
        this.nomeLista = nomeLista;
        this.descricao = descricao;
    }

    //Para select
    public Lista(int idLista, int idUsuario, String nomeLista, String descricao, LocalDateTime criadaEm) {
        this.idLista = idLista;
        this.idUsuario = idUsuario;
        this.nomeLista = nomeLista;
        this.descricao = descricao;
        this.criadaEm = criadaEm;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int id_usuario) {
        this.idUsuario = id_usuario;
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getCriadaEm() {
        return criadaEm;
    }

    public void setCriadaEm(LocalDateTime criadaEm) {
        this.criadaEm = criadaEm;
    }

    public static Lista converterRegistros(Map<String,Object> registros){

        int idLista = (int) registros.get("id_lista");
        int idUsuario = (int) registros.get("id_usuario");
        String nomeLista = (String) registros.get("nome_lista");
        String descricao = (String) registros.get("descricao");
        LocalDateTime criadaEm = registros.get("criada_em") != null ?
                                ((java.sql.Timestamp) registros.get("criada_em")).toLocalDateTime() 
                                : null;
        //criada_em é TIMESTAMP, converter para LocalDateTime
        return new Lista(idLista, idUsuario, nomeLista, descricao, criadaEm);
    }

    public static List<Lista> converterLista(List<Map<String,Object>> listaRegistros){
        ArrayList<Lista> auxiliar = new ArrayList<>();

        for(Map<String,Object> registro : listaRegistros){
            auxiliar.add(Lista.converterRegistros((Map) registro));
        }
        return auxiliar;
    }

}