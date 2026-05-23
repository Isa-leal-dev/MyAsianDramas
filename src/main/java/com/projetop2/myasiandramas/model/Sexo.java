package com.projetop2.myasiandramas.model;

public enum Sexo {
    F("Feminino"),
    M("Masculino");

    private final String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }

}
