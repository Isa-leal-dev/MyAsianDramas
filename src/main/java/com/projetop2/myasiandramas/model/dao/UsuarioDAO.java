package com.projetop2.myasiandramas.model.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.projetop2.myasiandramas.model.Usuario;

@Repository
public class UsuarioDAO extends BaseDAO {

    //CREATE:
    public void cadastrarUsuario(Usuario u){
        String sql = "INSERT INTO tb_usuarios (nome, email, senha) VALUES (?,?,?)";
        Object[] obj = new Object[3];
        obj[0] = (String) u.getNome();
        obj[1] = (String) u.getEmail();
        obj[2] = (String) u.getSenha();
        jdbc.update(sql,obj);
    }

    //READ
    //Login
    public Usuario buscarUsuarioPorEmailSenha(String email, String senha){
        String sql = "SELECT * FROM tb_usuarios WHERE email = ? AND senha = ?";
        Map<String,Object> registros = (Map<String,Object>) jdbc.queryForMap(sql,email,senha);        
        return Usuario.converterRegistros(registros);
        //Se não retorna nenhum usuário, lança EmptyResultDataAccessException
    }

    //Validação e-mail duplicado
    public boolean verificarSeEmailExiste(String email){
        String sql = "SELECT COUNT(*) FROM tb_usuarios WHERE email = ?";
        return ( jdbc.queryForObject(sql, Integer.class, email) > 0);
    }

}
