package com.projetop2.myasiandramas.model;

public enum PaisDorama {
    KR("Coréia do Sul"),
    JP("Japão"),
    CN("China"),
    TH("Tailândia"),
    TW("Taiwan");

    private final String descricao;

    PaisDorama(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
