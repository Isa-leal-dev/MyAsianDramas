package com.projetop2.myasiandramas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
    @GetMapping("/")//Avisa qual é a rota. Neste caso ("/") a página principal/landing page
    public String landingPage(){
        return "landing";
    }

}
