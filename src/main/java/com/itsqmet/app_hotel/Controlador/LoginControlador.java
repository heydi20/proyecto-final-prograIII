package com.itsqmet.app_hotel.Controlador;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginControlador {

    @GetMapping("/login")
    public String showLoginPage() {
        return "/pages/loginP"; 
    }

    @GetMapping("/iniciar-sesion")
    public String login() {
        return "redirect:/index"; 
    }
}

