
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

}
