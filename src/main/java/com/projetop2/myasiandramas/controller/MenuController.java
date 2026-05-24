package com.projetop2.myasiandramas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.projetop2.myasiandramas.model.Lista;
import com.projetop2.myasiandramas.model.service.ListaService;

@Controller
public class MenuController {

    @Autowired
    ApplicationContext context;

    @GetMapping("/")
    public String landingPage(){
        return "landing";
    }

    @GetMapping("/criar-lista")
    public String formCriarLista(Model model){
        model.addAttribute("criarLista",new Lista());
        return "criar-lista";
    }

    @PostMapping("/criar-lista")
    public String formCriarLista(@ModelAttribute Lista li, Model model){
        ListaService ls = context.getBean(ListaService.class);//pega o bean de ListaService
        li.setIdUsuario(1); //Usuário 1 fixo por enquanto
		ls.inserirLista(li);
		return "redirect:/minhas-listas";
    }

    @GetMapping("/minhas-listas") //Apenas para mostrar (não implementado totalmente ainda)
    public String mostraLista(Model model){
        //model.addAttribute("mostraLista",);
        return "minhas-listas";
    }

}
