package com.itsqmet.app_hotel.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.itsqmet.app_hotel.Entidad.Proveedor;

@Controller
public class ProveedorControlador {

    @GetMapping("/registrarProveedor")
    public String mostrarFormulario(Model model) {
        model.addAttribute("proveedor", new Proveedor());  
        return "/";
    }
}