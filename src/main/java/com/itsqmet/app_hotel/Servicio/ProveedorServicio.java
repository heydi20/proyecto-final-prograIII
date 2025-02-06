package com.itsqmet.app_hotel.Servicio;

import com.itsqmet.app_hotel.Entidad.Proveedor;
import com.itsqmet.app_hotel.Repositorio.ProveedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServicio {
    @Autowired
    ProveedorRepositorio proveedorRepositorio;

    public List<Proveedor> mostrarProveedores() {
        return proveedorRepositorio.findAll();
    }

    public List<Proveedor> buscarProveedorNombre(String buscarProveedor) {

        if (buscarProveedor != null || buscarProveedor.isEmpty()) {
            return proveedorRepositorio.findAll();
        } else {
            return proveedorRepositorio.findByNombreContainingIgnoreCase(buscarProveedor);
        }
    }
    public void guardarProveedor(Proveedor proveedor) {proveedorRepositorio.save(proveedor);}

    public void eliminarProveedor(Long id) {proveedorRepositorio.deleteById(id);}

    public Optional<Proveedor> buscarProveedor(Long id) {return proveedorRepositorio.findById(id);}


}