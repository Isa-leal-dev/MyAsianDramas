package com.projetop2.myasiandramas.model;

public class Genero {
    private int idGenero, idTmdbGenero;
    private String descricao;

    //Para insert
    public Genero(int idTmdbGenero, String descricao) {
        this.idTmdbGenero = idTmdbGenero;
        this.descricao = descricao;
    }

    //Para select
    public Genero(int idGenero, int idTmdbGenero, String descricao) {
        this.idGenero = idGenero;
        this.idTmdbGenero = idTmdbGenero;
        this.descricao = descricao;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdTmdbGenero() {
        return idTmdbGenero;
    }

    public void setIdTmdbGenero(int idTmdbGenero) {
        this.idTmdbGenero = idTmdbGenero;
    }



}
