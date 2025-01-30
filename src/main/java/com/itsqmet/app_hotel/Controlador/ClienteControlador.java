package com.itsqmet.app_hotel.Controlador;

import com.itsqmet.app_hotel.Entidad.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteControlador {
    @GetMapping("/registrocli")
    public String mostrarregistro(Model model){
        model.addAttribute("registerRequest", new Cliente());
        return "register";
    }
}
