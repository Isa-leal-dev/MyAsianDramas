package com.projetop2.myasiandramas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Elenco {
    private int idDorama;
    private int idAtor;
    private String personagem;

    public Elenco(){
        
    }

    //Para insert e select
    public Elenco(int idDorama, int idAtor, String personagem) {
        this.idDorama = idDorama;
        this.idAtor = idAtor;
        this.personagem = personagem;
    }

    public int getIdDorama() {
        return idDorama;
    }

    public void setIdDorama(int idDorama) {
        this.idDorama = idDorama;
    }

    public int getIdAtor() {
        return idAtor;
    }

    public void setIdAtor(int idAtor) {
        this.idAtor = idAtor;
    }

    public String getPersonagem() {
        return personagem;
    }

    public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }

    public static Elenco converterRegistros(Map<String,Object> registros){

        int idDorama = (int) registros.get("id_dorama");
        int idAtor = (int) registros.get("id_ator");
        String personagem = (String) registros.get("personagem");

        return new Elenco(idDorama, idAtor, personagem);
    }

    public static List<Elenco> converterLista(List<Map<String,Object>> listaRegistros){
        ArrayList<Elenco> auxiliar = new ArrayList<>();

        for(Map<String,Object> registro : listaRegistros){
            auxiliar.add(Elenco.converterRegistros((Map) registro));
        }
        return auxiliar;
    }

}
