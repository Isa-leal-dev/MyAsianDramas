/*
    Rotas referentes a login e cadastro
*/

package com.projetop2.myasiandramas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projetop2.myasiandramas.model.Usuario;
import com.projetop2.myasiandramas.model.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadastro")
    public String cadastrarUsuario(Model model, 
                                   @RequestParam(defaultValue = "false") boolean erro){
        model.addAttribute("erro", erro);
        model.addAttribute("cadastro", new Usuario());//Form inicial sem dados
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(@ModelAttribute Usuario user, Model model){
        if(usuarioService.verificarSeEmailExiste(user.getEmail())){
            return "redirect:/cadastro?erro=true";//Se e-mail já existe, redireciona para cadastro com parâmetro de erro
        }
        usuarioService.cadastrarUsuario(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String logar(Model model, 
                        @RequestParam(defaultValue = "false") boolean erro){
        model.addAttribute("erro", erro);
        return "login";
    }

    @PostMapping("/login")
    public String loginConcluido(@RequestParam String email, @RequestParam String senha, HttpSession session){
        try{//Verifica se usuário existe com base no email e senha informados.
            Usuario user = usuarioService.buscarUsuarioPorEmailSenha(email, senha);
            session.setAttribute("usuarioLogado", user);//Alimenta a sessão com dados do usuário encontrado.
            return "redirect:/";
        }//Se não existe, redireciona para login com o parâmetro de erro para mostrar email ou senha incorretos
        catch (Exception e){
            return "redirect:/login?erro=true";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
