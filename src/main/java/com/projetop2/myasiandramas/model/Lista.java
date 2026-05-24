package com.projetop2.myasiandramas.model;

import java.time.LocalDate;

public class Lista {

    private int idLista, idUsuario;
    private String nomeLista, descricao;
    private LocalDate criadaEm;

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
    public Lista(int idLista, int idUsuario, String nomeLista, String descricao, LocalDate criadaEm) {
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

    public LocalDate getCriadaEm() {
        return criadaEm;
    }

    public void setCriadaEm(LocalDate criadaEm) {
        this.criadaEm = criadaEm;
    }

}