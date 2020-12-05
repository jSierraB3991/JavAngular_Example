package com.douglas.Douglas.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationRest extends BaseRest {

    @NotNull(message = "El email es obligatorio")
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email ingresado no cumple con las condiciones, por favor revise")
    private String email;

    @NotNull(message = "La contraseña es obligatoria")
    @NotBlank(message = "La contraseña es obligatoria")
    @Max(value = 15, message = "La longitud maxima de la contraseña es de 15 caracteres")
    @Min(value = 8, message = "La longitud minima permitida para la contraseña es de 8 caracteres")
    private String password;

    @NotNull(message = "El primer nombre es obligatorio")
    @NotBlank(message = "El primer nombre es obligatorio")
    @Max(value = 50, message = "El(los) nombre(s) tiene(n) màs de 50 caracteres, por favor revise")
    private String firstName;

    @NotNull(message = "El o los apellidos son obligatorios")
    @NotBlank(message = "El o los apellidos son obligatorios")
    @Max(value = 100, message = "Los apellidos tienen mas de 100 caracteres, por favor revise")
    private String lastName;

    @Max(value = 200, message = "La direcciòn tiene màs de 200 caracteres, por favor revise")
    private String address;

    @Max(value = 10, message = "El celular tiene màs de 10 caracteres, por favor revise")
    private String cellPhone;
}
