package com.itsqmet.app_hotel.Controlador;

import com.itextpdf.text.DocumentException;
import com.itsqmet.app_hotel.Entidad.Cliente;
import com.itsqmet.app_hotel.Entidad.Contrato;
import com.itsqmet.app_hotel.Entidad.Prestaciones;
import com.itsqmet.app_hotel.Entidad.Proveedor;
import com.itsqmet.app_hotel.Servicio.ClienteServicio;
import com.itsqmet.app_hotel.Servicio.ContratoServicio;
import com.itsqmet.app_hotel.Servicio.PrestacionesServicios;
import com.itsqmet.app_hotel.Servicio.ProveedorServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class ClienteControlador {
    @Autowired
    ClienteServicio clienteServicio;
    @Autowired
    ProveedorServicio proveedorServicio;
    @Autowired
    PrestacionesServicios prestacionesServicios;
    @Autowired
    ContratoServicio contratoServicio;


    @GetMapping("/clientes")
    public String listarClientes(@RequestParam(name = "buscarCliente", required = false, defaultValue = "") String buscarCliente, Model model) {
        List<Cliente>clientes = clienteServicio.buscarClienteNombre(buscarCliente);
        model.addAttribute("buscarCliente", buscarCliente);
        model.addAttribute("clientes", clientes);
        return "Cliente/listaCliente";
    }
    @GetMapping("/vistaCliente")
    public String vistaCliente(Model model) {
        List<Cliente> clientes = clienteServicio.buscarClienteNombre(""); // obtener lista de clientes
        List<Proveedor> proveedores = proveedorServicio.mostrarProveedores(); // obtener lista de proveedores
        List<Prestaciones> prestaciones = prestacionesServicios.mostrarPrestaciones(); // obtener lista de prestaciones>

        model.addAttribute("clientes", clientes); // pasamos la lista de clientes
        model.addAttribute("proveedores", proveedores); // pasamos la lista de proveedores
        model.addAttribute("prestaciones",prestaciones );
        model.addAttribute("cliente", new Cliente()); // para el formulario de cliente
        model.addAttribute("contrato", new Contrato()); // <-- aquí agregamos contrato

        return "Cliente/Vistacliente"; // retornamos la vista
    }

    // Mostrar formulario
    @GetMapping("/formularioCliente")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "Cliente/formulario";
    }
    // Insertar
    @GetMapping("/registrarCliente")
    public String insertarCliente(Cliente cliente) {
        clienteServicio.guardarCliente(cliente);
        return "redirect:/clientes";
    }
    // Actualizar
    @GetMapping("/actualizarCliente/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Optional<Cliente> clienteOpt = clienteServicio.buscarClienteId(id);

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            model.addAttribute("cliente", cliente);
            return "Cliente/formulario2"; // Asegúrate de que esta vista exista.
        }

        return "redirect:/clientes"; // Redirige a la lista de clientes si no se encuentra el cliente.
    }

    @GetMapping("/eliminarCliente/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteServicio.eliminarCliente(id);
        return "redirect:/clientes";
    }
    // PDF
    @GetMapping("/clientes/pdf")
    public ResponseEntity<byte[]> descargarPdf() throws ExceptionInInitializerError, DocumentException, IOException {
        byte[] pdf = clienteServicio.generarPdf();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "clientes.pdf");
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }

}