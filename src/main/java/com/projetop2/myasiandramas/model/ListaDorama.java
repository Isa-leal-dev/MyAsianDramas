package com.projetop2.myasiandramas.model;

public class ListaDorama {
    private int idLista, idDorama;
    private StatusVisualizacao status;

    //Para insert e select
    public ListaDorama(int idLista, int idDorama, StatusVisualizacao status) {
        this.idLista = idLista;
        this.idDorama = idDorama;
        this.status = status;
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

    public StatusVisualizacao getStatus() {
        return status;
    }

    public void setStatus(StatusVisualizacao status) {
        this.status = status;
    }



}
