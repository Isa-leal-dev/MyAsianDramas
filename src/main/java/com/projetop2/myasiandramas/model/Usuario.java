package com.projetop2.myasiandramas.model;

import java.time.LocalDateTime;
import java.util.Map;

public class Usuario {

    private int idUsuario;
    private String nome, email, senha;
    private LocalDateTime dataCriacao;

    public Usuario(){

    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(int idUsuario, String nome, String email, String senha, LocalDateTime dataCriacao) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public static Usuario converterRegistros(Map<String,Object> registros){

        int idUsuario = (int) registros.get("id_usuario");
        String nome = (String) registros.get("nome");
        String email = (String) registros.get("email");
        String senha = (String) registros.get("senha");
        LocalDateTime dataCriacao = registros.get("data_criacao") != null ?
                                ((java.sql.Timestamp) registros.get("data_criacao")).toLocalDateTime() 
                                : null;
        return new Usuario(idUsuario, nome, email, senha, dataCriacao);
    }
   
}
