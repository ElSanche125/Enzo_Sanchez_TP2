package com.example.aydsII.act4.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(min=2)
    @Schema(description = "Nombre del cliente", example = "Marquitos")
    private String nombre;

    @NotBlank(message = "El apellido del cliente es obligatorio")
    @Size(min=2)
    @Schema(description = "Apellido del cliente", example = "De Luque")
    private String apellido;

    @Email 
    @NotBlank(message = "El email del cliente es obligatorio")
    @Schema(description = "email del cliente", example = "MdeLuque@gmail.com")
    private String email;
    
    @NotBlank(message = "El telefono del cliente es obligatorio")
    @Pattern(
    regexp = "\\d+",
    message = "solo debe contener dígitos")
    @Schema(description = "telefono del cliente", example = "121212")
    private String telefono;
}