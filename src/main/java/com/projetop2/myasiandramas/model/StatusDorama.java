package com.projetop2.myasiandramas.model;

public enum StatusDorama {
    EM_ANDAMENTO("Em andamento"),
    CONCLUIDO("Concluído"),
    EM_BREVE("Em breve"),
    DESCONHECIDO("Desconhecido");
    
    private final String descricao;


    StatusDorama(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }

}
