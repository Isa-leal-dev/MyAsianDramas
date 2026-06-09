
package com.projetop2.myasiandramas.model.dao;

import org.springframework.stereotype.Repository;

import com.projetop2.myasiandramas.model.DoramaGenero;

@Repository
public class DoramaGeneroDAO extends BaseDAO {

    public void inserirDoramaGenero(DoramaGenero dg){
        String sql = "INSERT INTO tb_doramas_generos (id_dorama, id_genero) VALUES(?,?)";
        Object[] obj = new Object[2];
        obj[0] = (int) dg.getIdDorama(); 
        obj[1] = (int) dg.getIdGenero();
        jdbc.update(sql,obj);
    }

    public boolean generoJaExisteNoDorama(int idDorama, int idGenero){
        String sql = "SELECT COUNT(*) FROM tb_doramas_generos WHERE id_dorama = ? AND id_genero = ?";
        Integer count = jdbc.queryForObject(sql,Integer.class, idDorama, idGenero);
        return count > 0;

    }

}
