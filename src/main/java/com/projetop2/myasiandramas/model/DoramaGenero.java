package com.projetop2.myasiandramas.model;

public class DoramaGenero {
    private int idDorama;
    private int idGenero;

    //Para insert e select
    public DoramaGenero(int idDorama, int idGenero) {
        this.idDorama = idDorama;
        this.idGenero = idGenero;
    }

    public int getIdDorama() {
        return idDorama;
    }

    public void setIdDorama(int idDorama) {
        this.idDorama = idDorama;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }
    
    

}


