package com.itsqmet.app_hotel.Entidad;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Proveedor {

    private Long id;
    
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
        private String nombre;
    
        @NotBlank(message = "La especialidad es obligatoria")
        @Size(min = 2, max = 100, message = "La especialidad debe tener entre 2 y 100 caracteres")
        private String especialidad;
    
        @NotBlank(message = "La experiencia es obligatoria")
        @Size(min = 2, max = 100, message = "La experiencia debe tener entre 2 y 100 caracteres")
        private String experiencia;
    
        @NotBlank(message = "Las tarifas son obligatorias")
        private String tarifas;
    
        @NotBlank(message = "El correo es obligatorio")
        @Size(max = 100, message = "El correo no puede tener más de 100 caracteres")
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "El correo no tiene un formato válido")
        private String correo;
    
        @NotBlank(message = "El teléfono es obligatorio")
        @Size(min = 10, max = 13, message = "El teléfono debe tener entre 10 y 13 caracteres")
        private String telefono;
    
    
        public String getNombre() {
            return nombre;
        }
    
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    
        public String getEspecialidad() {
            return especialidad;
        }
    
        public void setEspecialidad(String especialidad) {
            this.especialidad = especialidad;
        }
    
        public String getExperiencia() {
            return experiencia;
        }
    
        public void setExperiencia(String experiencia) {
            this.experiencia = experiencia;
        }
    
        public String getTarifas() {
            return tarifas;
        }
    
        public void setTarifas(String tarifas) {
            this.tarifas = tarifas;
        }
    
        public String getCorreo() {
            return correo;
        }
    
        public void setCorreo(String correo) {
            this.correo = correo;
        }
    
        public String getTelefono() {
            return telefono;
        }
    
        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }
    
        public Long getId() {
            return id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
}