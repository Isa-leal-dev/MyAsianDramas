package com.projetop2.myasiandramas.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetop2.myasiandramas.model.Dorama;
import com.projetop2.myasiandramas.model.PaisDorama;
import com.projetop2.myasiandramas.model.StatusDorama;
import com.projetop2.myasiandramas.model.dao.DoramaDAO;

@Service
public class DoramaService {

    @Autowired
    DoramaDAO ddao;

    public int contarDoramas() {
    return ddao.contarDoramas();//Verifica quantos doramas estão inseridos em tb_doramas
    }  

    //CREATE:

    public int inserirDorama(Dorama d){
        return ddao.inserirDorama(d);
    }   

    //READ:

    public Dorama buscarDoramaPorId(int idDorama){
        return ddao.buscarDoramaPorId(idDorama);
    }   

    public List<Dorama> buscarDoramasPorTitulo(String titulo){
        return ddao.buscarDoramasPorTitulo(titulo);       
    }

    public List<Dorama> buscarEmAndamento(){
        return ddao.buscarDoramasPorStatus(StatusDorama.EM_ANDAMENTO);
    }

    public List<Dorama> buscarDoramasPorStatusLimitado(int limite){
        return ddao.buscarDoramasPorStatusLimitado(StatusDorama.EM_ANDAMENTO, limite);
    }

    public List<Dorama> buscarDoramasPorPais(PaisDorama pd){
       return ddao.buscarDoramasPorPais(pd); 
    } 

    public List<Dorama> buscarTodosDoramas(){//Sem rota ainda
        return ddao.buscarTodosDoramas();
    }

    public List<Dorama> buscarDoramasPorMes(int mes){//Sem rota ainda
        return ddao.buscarDoramasPorMes(mes);
    }

    public List<Dorama> buscarDoramasPorAtor(int idAtor){
        return ddao.buscarDoramasPorAtor(idAtor);
    }

    public List<Dorama> buscarDoramasPorGenero(int idGenero){//Sem uso ainda
        return ddao.buscarDoramasPorGenero(idGenero);
    }

    public List<Dorama> buscarDoramasPorLista(int idLista){
        return ddao.buscarDoramasPorLista(idLista);
    }

}
