package com.projetop2.myasiandramas.model;

public class ListaDorama {
    private int idLista, idDorama;

    //Form inicial
    public ListaDorama() {

    }

    //Para insert e select
    public ListaDorama(int idLista, int idDorama) {
        this.idLista = idLista;
        this.idDorama = idDorama;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public int getIdDorama() {
        return idDorama;
    }

    public void setIdDorama(int idDorama) {
        this.idDorama = idDorama;
    }

}
