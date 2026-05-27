package com.projetop2.myasiandramas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public static Genero converterRegistros(Map<String,Object> registros){
        int idGenero = (int) registros.get("id_genero");
        int idTmdbGenero = (int) registros.get("id_tmdb_genero");
        String descricao = (String) registros.get("descricao");

        return new Genero(idGenero, idTmdbGenero, descricao);
    }

    public static List<Genero> converterLista(List<Map<String,Object>> listaRegistros){
        ArrayList<Genero> auxiliar = new ArrayList<>();

        for(Map<String,Object> registro : listaRegistros){
            auxiliar.add(Genero.converterRegistros((Map) registro));
        }
        return auxiliar;
    }



}
