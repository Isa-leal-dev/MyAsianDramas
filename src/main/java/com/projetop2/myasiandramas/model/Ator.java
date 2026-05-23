package com.projetop2.myasiandramas.model;

import java.time.LocalDate;

public class Ator {
    private int idAtor, idTmdbAtor;
    private String nomeAtor, nomeOriginal, fotoPerfil;
    private LocalDate dataNascimento;
    private Sexo sexo;

    public Ator(){

    }

    //Para insert
    public Ator(int idTmdbAtor, String nomeAtor, String nomeOriginal, String fotoPerfil, Sexo sexo, LocalDate dataNascimento) {
        this.idTmdbAtor = idTmdbAtor;
        this.nomeAtor = nomeAtor;
        this.nomeOriginal = nomeOriginal;
        this.fotoPerfil = fotoPerfil;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    //Para select
    public Ator(int idAtor, int idTmdbAtor, String nomeAtor, String nomeOriginal, String fotoPerfil, Sexo sexo, LocalDate dataNascimento) {
        this.idAtor = idAtor;
        this.idTmdbAtor = idTmdbAtor;
        this.nomeAtor = nomeAtor;
        this.nomeOriginal = nomeOriginal;
        this.fotoPerfil = fotoPerfil;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public int getIdAtor() {
        return idAtor;
    }

    public void setIdAtor(int idAtor) {
        this.idAtor = idAtor;
    }

    public String getNomeAtor() {
        return nomeAtor;
    }

    public void setNomeAtor(String nomeAtor) {
        this.nomeAtor = nomeAtor;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public int getIdTmdbAtor() {
        return idTmdbAtor;
    }

    public void setIdTmdbAtor(int idTmdbAtor) {
        this.idTmdbAtor = idTmdbAtor;
    }

    public String getNomeOriginal() {
        return nomeOriginal;
    }

    public void setNomeOriginal(String nomeOriginal) {
        this.nomeOriginal = nomeOriginal;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

}
