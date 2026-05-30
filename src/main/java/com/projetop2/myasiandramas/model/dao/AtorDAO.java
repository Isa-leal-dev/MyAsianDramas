package com.projetop2.myasiandramas.model.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.projetop2.myasiandramas.model.Ator;

@Repository
public class AtorDAO extends BaseDAO{

    public boolean existePorIdTmdb(int idTmdbAtor){
        return existePorIdTmdb("tb_atores", "id_tmdb_ator", idTmdbAtor);//De classe abstrata
    }

    public int buscarIdPorIdTmdb(int idTmdbAtor){
        return buscarIdPorIdTmdb("id_ator", "tb_atores","id_tmdb_ator", idTmdbAtor);//De classe abstrata
    }

    //CREATE:

    public void inserirAtor(Ator a){
        String sql = "INSERT INTO tb_atores(id_tmdb_ator, nome_ator, nome_original, data_nascimento, sexo, foto_perfil) VALUES(?,?,?,?,?,?)";
        Object[] obj = new Object[6];
        obj[0] = (int) a.getIdTmdbAtor();
        obj[1] = (String) a.getNomeAtor(); 
        obj[2] = (String) a.getNomeOriginal();
        obj[3] = (LocalDate) a.getDataNascimento();
        obj[4] = a.getSexo() != null ? a.getSexo().name() : null;
        obj[5] = (String) a.getFotoPerfil();
        jdbc.update(sql,obj);
    }

    //READ:
    public Ator buscarAtorPorId(int idAtor){
        String sql = "SELECT * FROM tb_atores WHERE id_ator = ?";
        Map<String,Object> registros = (Map<String,Object>) jdbc.queryForMap(sql,idAtor);        
        return Ator.converterRegistros(registros);
    }

    public List<Ator> buscarAtoresPorDorama(int idDorama){
        String sql = "SELECT a.* FROM tb_atores a " +
                    "JOIN tb_elenco e ON a.id_ator = e.id_ator " +
                    "WHERE e.id_dorama = ?";
        List<Map<String,Object>> listaRegistros = jdbc.queryForList(sql, idDorama);
        return Ator.converterLista(listaRegistros);
    }

    public List<Ator> buscarAtoresPorNome(String nome){
        String sql = "SELECT * FROM tb_atores WHERE nome_ator ILIKE ?";
        String nomeBuscado = "%"+nome+"%";
        List<Map<String,Object>> listaRegistros = jdbc.queryForList(sql, nomeBuscado);
        return Ator.converterLista(listaRegistros);       
    }

}
