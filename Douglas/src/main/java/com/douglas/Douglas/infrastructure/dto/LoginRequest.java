package com.douglas.Douglas.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotNull(message = "El email es obligatorio")
    @Email(message = "El email ingresado no cumple con las condiciones, por favor revise")
    private String email;

    @NotNull(message = "La contraseña es obligatoria")
    @Size(max = 15, min = 8, message = "La longitud de la contraseña debe ser entre 8 y 15 caracteres")
    private String password;
}
