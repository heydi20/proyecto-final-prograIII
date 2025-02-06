package com.itsqmet.app_hotel.Controlador;

import com.itsqmet.app_hotel.Servicio.ProveedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.itsqmet.app_hotel.Entidad.Proveedor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProveedorControlador {
    @Autowired
    ProveedorServicio proveedorServicio;

    @GetMapping("/proveedores")
    public String mostrarProveedores(@RequestParam(name = "buscarProveedor", required = false, defaultValue = "") String buscarProveedor, Model model) {
        List<Proveedor> proveedores = proveedorServicio.buscarProveedorNombre(buscarProveedor);
        model.addAttribute("buscarProveedor", buscarProveedor);
        model.addAttribute("proveedores", proveedores);
        return "Proveedor/listaProveedor";
    }
    @GetMapping("/formularioProveedor")
    public String mostrarFormularioProveedor(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "Proveedor/formulario";
    }
    @PostMapping("/registrarProveedor")
    public String insertarProveedor(Proveedor proveedor) {
        proveedorServicio.guardarProveedor(proveedor);
        return "redirect:/proveedores";
    }
    @GetMapping("/actualizarProveedor/{id}")
    public String editarProveedor(@PathVariable Long id, Model model) {
        Optional<Proveedor> proveedor = proveedorServicio.buscarProveedor(id);
        model.addAttribute("proveedor", proveedor);
        return "Proveedor/formulario";
    }
    @GetMapping("/eliminarProveedor/{id}")
    public String eliminarProveedor(@PathVariable Long id) {
        proveedorServicio.eliminarProveedor(id);
        return "redirect:/proveedores";
    }


}