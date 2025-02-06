package com.itsqmet.app_hotel.Servicio;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itsqmet.app_hotel.Entidad.Cliente;
import com.itsqmet.app_hotel.Repositorio.ClienteRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    public List<Cliente> mostrarClientes() {return clienteRepositorio.findAll();}

    public List<Cliente> buscarClienteNombre(String buscarCliente){
        if(buscarCliente != null || buscarCliente.isEmpty()){
            return clienteRepositorio.findAll();
        }else{
            return clienteRepositorio.findByNombreContainingIgnoreCase(buscarCliente);
        }
    }

    public void guardarCliente(Cliente cliente) {clienteRepositorio.save(cliente);}
    public void eliminarCliente(Long id) {clienteRepositorio.deleteById(id);}
    public Optional<Cliente> buscarClienteId(Long id) {return clienteRepositorio.findById(id);}
    @Transactional
    public Cliente obtenerClienteId(Long id){
        Cliente cliente=clienteRepositorio.findById(id).orElseThrow();
        System.out.println(("cliente: "+cliente.getNombre().length()));
        return cliente;
    }

    public byte[] generarPdf() throws DocumentException, IOException {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();
        document.add(new Paragraph("Lista de Clientes", FontFactory.getFont("Arial", 14, Font.BOLD)));
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.addCell(new PdfPCell(new Phrase("ID", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("Nombre", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("Email", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("Direccion", FontFactory.getFont("Arial", 12))));
        for (Cliente cliente : clientes) {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(cliente.getId()), FontFactory.getFont("Arial", 11))));
            table.addCell(new PdfPCell(new Phrase(cliente.getNombre(), FontFactory.getFont("Arial", 11))));
            table.addCell(new PdfPCell(new Phrase(cliente.getEmail(), FontFactory.getFont("Arial", 11))));
            table.addCell(new PdfPCell(new Phrase(cliente.getDireccion(), FontFactory.getFont("Arial", 11))));
        }
        document.add(table);
        document.close();
        return baos.toByteArray();
    }
}

