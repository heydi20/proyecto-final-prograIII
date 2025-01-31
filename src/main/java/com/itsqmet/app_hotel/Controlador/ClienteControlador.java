package com.itsqmet.app_hotel.Controlador;

import com.itsqmet.app_hotel.Entidad.Cliente;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteControlador {
    @GetMapping("/registrocli")
    public String mostrarregistro(Model model){
        model.addAttribute("registerRequest", new Cliente());
        return "pages/register";
    }

    @PostMapping("/enviar")
    public String enviarFormulario(@Valid @ModelAttribute("registerRequest") Cliente cliente, BindingResult bindingResult, Model model) {
        System.out.println("Formulario enviado: " + cliente);
        if (bindingResult.hasErrors()) {
            return "pages/register"; // Volver a la misma página si hay errores
        }
        return "index"; // Redirigir a la página de inicio si no hay errores
    }
}
