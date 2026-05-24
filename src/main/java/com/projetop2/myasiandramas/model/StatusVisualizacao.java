package com.projetop2.myasiandramas.model;

public enum StatusVisualizacao {

    QUERO_ASSISTIR("Quero assistir"),
    ASSISTINDO("Assistindo"),
    ASSISTIDO("Assistido");

    private final String descricao;

    StatusVisualizacao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }

}
