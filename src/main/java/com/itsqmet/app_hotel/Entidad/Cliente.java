package com.itsqmet.app_hotel.Entidad;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class Cliente {

    @NotNull
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

    @NotNull
    @Size(min = 8, max = 15)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,15}$",
            message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial"
    )
    private String password;

    @NotNull
    @Size(min = 8, max = 15)
    private String confirmarPassword;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmarPassword() {
        return confirmarPassword;
    }

    public void setConfirmarPassword(String confirmarPassword) {
        this.confirmarPassword = confirmarPassword;
    }
}
