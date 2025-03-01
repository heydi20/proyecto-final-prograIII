package com.itsqmet.app_hotel.Entidad;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 50)
    private String nombre;

    @Size(min = 3, max = 50)
    @NotBlank
    private String direccion;

    @NotBlank
    @Size(min = 9, max = 10, message = "El teléfono debe tener 9 o 10 dígitos")
    private String telefono;

    @NotBlank
    @Email(message = "Ingrese un correo válido")
    private String email;

    @OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY)
    private List<Contrato> contratos = new ArrayList<>();
    @OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY)
    private List<Resenas> reseñasCliente = new ArrayList<>();


    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public List<Resenas> getReseñasCliente() {
        return reseñasCliente;
    }

    public void setReseñasCliente(List<Resenas> reseñasCliente) {
        this.reseñasCliente = reseñasCliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
