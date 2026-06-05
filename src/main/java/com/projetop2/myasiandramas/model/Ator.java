package com.projetop2.myasiandramas.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public String getDataNascimentoFormatada(){
        return ConversorData.converterData(dataNascimento);
    }

    public static Ator converterRegistros(Map<String,Object> registros){

        int idAtor = (int) registros.get("id_ator");
        int idTmdbAtor = (int) registros.get("id_tmdb_ator");
        String nomeAtor = (String) registros.get("nome_ator");
        String nomeOriginal = (String) registros.get("nome_original");
        String fotoPerfil = (String) registros.get("foto_perfil");
        Sexo sexo = Sexo.valueOf((String) registros.get("sexo"));
        LocalDate dataNascimento = registros.get("data_nascimento") != null ? //data é retornada como java.sqlDate
                                ((java.sql.Date) registros.get("data_nascimento")).toLocalDate() //Precisa converter para LocalDate
                                : null;//verificação de null para evitar NullPointerException

        return new Ator(idAtor, idTmdbAtor, nomeAtor, nomeOriginal, fotoPerfil, sexo, dataNascimento);
    }

    public static List<Ator> converterLista(List<Map<String,Object>> listaRegistros){
        ArrayList<Ator> auxiliar = new ArrayList<>();

        for(Map<String,Object> registro : listaRegistros){
            auxiliar.add(Ator.converterRegistros((Map) registro));
        }
        return auxiliar;
    }

}
