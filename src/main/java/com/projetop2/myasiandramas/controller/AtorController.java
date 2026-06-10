/*
    Rotas para a funcionalidade de gerenciar atores
*/

package com.projetop2.myasiandramas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.projetop2.myasiandramas.model.Ator;
import com.projetop2.myasiandramas.model.Sexo;
import com.projetop2.myasiandramas.model.Usuario;
import com.projetop2.myasiandramas.model.service.AtorService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AtorController {

    @Autowired
    AtorService atorService;

    //CREATE:

    @GetMapping("/ator/criar")
    public String formCriarAtor(Model model,
                                 HttpSession session){

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) //Se não há nenhum usuário logado, redireciona para fazer o login
            return "redirect:/login";

        model.addAttribute("criarAtor",new Ator());
        //Opções de sexo:
        model.addAttribute("sexo", Sexo.values());

        return "ator-criar";
    }

    @PostMapping("/ator/criar")
    public String formCriarAtor(@ModelAttribute Ator ator, 
                                 HttpSession session){

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado"); //Caso a sessão expire entre GET e POST
        if (usuarioLogado == null) 
            return "redirect:/login";
        
		int idAtor = atorService.inserirAtor(ator);

		return "redirect:/ator/" + idAtor;
    }

    //UPDATE:

    @GetMapping("/ator/{idAtor}/editar") 
    public String editarAtor(Model model, 
                             @PathVariable int idAtor,
                             HttpSession session){

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null)
            return "redirect:/login";

        model.addAttribute("id",idAtor); 
        Ator atorAnterior = atorService.buscarAtorPorId(idAtor);
        model.addAttribute("ator",atorAnterior);

        //Opções de sexo:
        model.addAttribute("sexo", Sexo.values());
        return "ator-editar";
    }

    @PostMapping("/ator/{idAtor}/editar")
    public String atualizarAtor(@PathVariable int idAtor, 
                                @ModelAttribute Ator ator,
                                HttpSession session){

        //Verifica dados do usuário logado
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null)
            return "redirect:/login";

        atorService.atualizarAtor(idAtor, ator);
		return "redirect:/ator/" + idAtor;
    }

}
