package com.projetop2.myasiandramas.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetop2.myasiandramas.model.Usuario;
import com.projetop2.myasiandramas.model.dao.UsuarioDAO;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDAO udao;

    public void cadastrarUsuario(Usuario u){
        udao.cadastrarUsuario(u);
    }

    public Usuario buscarUsuarioPorEmailSenha(String email, String senha){
        return udao.buscarUsuarioPorEmailSenha(email, senha);
    }

    public boolean verificarSeEmailExiste(String email){
        return udao.verificarSeEmailExiste(email);
    }

}
