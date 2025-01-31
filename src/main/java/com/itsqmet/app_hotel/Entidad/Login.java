package com.itsqmet.app_hotel.Entidad;

import org.springframework.stereotype.Controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Controller
public class Login {

    @NotBlank(message = "El correo es obligatorio")
    @Size(max = 100, message = "El correo no puede tener más de 100 caracteres")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "El correo no tiene un formato válido")
    private String correo;

    @NotNull
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,15}$", message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial")
    private String password;

}
