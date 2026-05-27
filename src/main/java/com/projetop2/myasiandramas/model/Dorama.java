package com.projetop2.myasiandramas.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Dorama {
    private int idDorama, numeroEpisodios;
    private String tituloPortugues, tituloNativo, tituloIngles, sinopse, emissoraOriginal, poster;
    private StatusDorama statusDorama;
    private PaisDorama pais;
    private LocalDate dataEstreia, dataFinal;

    //Início do formulário
    public Dorama(){

    }

    //Para insert (Sem id pois id é serial)
    public Dorama(LocalDate dataEstreia, LocalDate dataFinal, String emissoraOriginal, 
                  PaisDorama pais, String poster, String sinopse, StatusDorama statusDorama, 
                  String tituloIngles, String tituloNativo, String tituloPortugues, int numeroEpisodios) {
        this.dataEstreia = dataEstreia;
        this.dataFinal = dataFinal;
        this.emissoraOriginal = emissoraOriginal;
        this.pais = pais;
        this.poster = poster;
        this.sinopse = sinopse;
        this.statusDorama = statusDorama;
        this.tituloIngles = tituloIngles;
        this.tituloNativo = tituloNativo;
        this.tituloPortugues = tituloPortugues;
        this.numeroEpisodios = numeroEpisodios;
    }
    
    //Para select
    public Dorama(LocalDate dataEstreia, LocalDate dataFinal, String emissoraOriginal, 
                  int idDorama, PaisDorama pais, String poster, String sinopse, StatusDorama statusDorama, 
                  String tituloIngles, String tituloNativo, String tituloPortugues, int numeroEpisodios) {
        this.dataEstreia = dataEstreia;
        this.dataFinal = dataFinal;
        this.emissoraOriginal = emissoraOriginal;
        this.idDorama = idDorama;
        this.pais = pais;
        this.poster = poster;
        this.sinopse = sinopse;
        this.statusDorama = statusDorama;
        this.tituloIngles = tituloIngles;
        this.tituloNativo = tituloNativo;
        this.tituloPortugues = tituloPortugues;
        this.numeroEpisodios = numeroEpisodios;
    }

    public int getIdDorama() {
        return idDorama;
    }

    public void setIdDorama(int idDorama) {
        this.idDorama = idDorama;
    }

    public String getTituloPortugues() {
        return tituloPortugues;
    }

    public void setTituloPortugues(String tituloPortugues) {
        this.tituloPortugues = tituloPortugues;
    }

    public String getTituloNativo() {
        return tituloNativo;
    }

    public void setTituloNativo(String tituloNativo) {
        this.tituloNativo = tituloNativo;
    }

    public String getTituloIngles() {
        return tituloIngles;
    }

    public void setTituloIngles(String tituloIngles) {
        this.tituloIngles = tituloIngles;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getEmissoraOriginal() {
        return emissoraOriginal;
    }

    public void setEmissoraOriginal(String emissoraOriginal) {
        this.emissoraOriginal = emissoraOriginal;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public StatusDorama getStatusDorama() {
        return statusDorama;
    }

    public void setStatusDorama(StatusDorama statusDorama) {
        this.statusDorama = statusDorama;
    }

    public PaisDorama getPais() {
        return pais;
    }

    public void setPais(PaisDorama pais) {
        this.pais = pais;
    }

    public LocalDate getDataEstreia() {
        return dataEstreia;
    }

    public void setDataEstreia(LocalDate dataEstreia) {
        this.dataEstreia = dataEstreia;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getNumeroEpisodios() {
        return numeroEpisodios;
    }

    public void setNumeroEpisodios(int numeroEpisodios) {
        this.numeroEpisodios = numeroEpisodios;
    }

    public static Dorama converterRegistros(Map<String,Object> registros){
        LocalDate dataEstreia = registros.get("data_estreia") != null ? //data é retornada como java.sqlDate
                                ((java.sql.Date) registros.get("data_estreia")).toLocalDate() //Precisa converter para LocalDate
                                : null;//verificação de null para evitar NullPointerException
        LocalDate dataFinal = registros.get("data_final") != null ?
                                ((java.sql.Date) registros.get("data_final")).toLocalDate()
                                : null;
        String emissoraOriginal = (String) registros.get("emissora_original");
        int idDorama = (int) registros.get("id_dorama");
        PaisDorama pais = PaisDorama.valueOf((String) registros.get("pais")); //Cast de Object para String. valueOf espera String
        String poster = (String) registros.get("poster");
        String sinopse = (String) registros.get("sinopse");
        StatusDorama statusDorama = StatusDorama.valueOf((String) registros.get("status_dorama"));        
        String tituloIngles = (String) registros.get("titulo_ingles");
        String tituloNativo = (String) registros.get("titulo_nativo");
        String tituloPortugues = (String) registros.get("titulo_portugues");
        int numeroEpisodios = (int) registros.get("numero_episodios");

        return new Dorama(dataEstreia, dataFinal, emissoraOriginal, idDorama, pais, poster, sinopse, statusDorama, 
                        tituloIngles, tituloNativo, tituloPortugues, numeroEpisodios);
    }

    public static List<Dorama> converterLista(List<Map<String,Object>> listaRegistros){
        ArrayList<Dorama> auxiliar = new ArrayList<>();

        for(Map<String,Object> registro : listaRegistros){
            auxiliar.add(Dorama.converterRegistros((Map) registro));
        }
        return auxiliar;
    }

}