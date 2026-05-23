package com.projetop2.myasiandramas.model;

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

}
