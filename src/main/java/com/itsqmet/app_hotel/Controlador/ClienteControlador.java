package com.itsqmet.app_hotel.Controlador;

import com.itextpdf.text.DocumentException;
import com.itsqmet.app_hotel.Entidad.Cliente;
import com.itsqmet.app_hotel.Servicio.ClienteServicio;
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


    @GetMapping("/clientes")
    public String listarClientes(@RequestParam(name = "buscarCliente", required = false, defaultValue = "") String buscarCliente, Model model) {
        List<Cliente>clientes = clienteServicio.buscarClienteNombre(buscarCliente);
        model.addAttribute("buscarCliente", buscarCliente);
        model.addAttribute("clientes", clientes);
        return "Cliente/listaCliente";
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
        Optional<Cliente> cliente = clienteServicio.buscarClienteId(id);
        model.addAttribute("cliente", cliente);
        return "Cliente/formulario";
    }


    //// Obtener libros por autor
    //    @GetMapping("/autor/{id}")
    //    public String obtenerLibrosPorAutor(@PathVariable Long id, Model model) {
    //        Optional<Libro> libros = libroServicio.buscarLibroId(id);
    //        Autor autor = autorServicio.buscarAutorId(id)
    //                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
    //
    //        model.addAttribute("autor", autor);
    //        model.addAttribute("libros", libros);
    //
    //        return "Autor/listaAutorLibros";
    //    }
    // Eliminar
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