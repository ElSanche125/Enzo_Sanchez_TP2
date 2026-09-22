package com.example.aydsII.act2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Schema(description = "Representa un producto")
public class Producto {

    @Schema(description = "Identificador unicod el producto", example = "5")
    private Integer id;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Schema(description = "Nombre del producto", example = "Mouse inalambrico")
    private String nombre;

    @NotBlank(message = "La categoria del producto es obligatorio")
    @Schema(description = "Categoria del producto", example = "Accesorios")
    private String categoria;

    @Positive (message = "La cantidad debe ser mayor a 0")
    @Schema(description = "Precio del producto", example = "12000")
    private double precio;

    @Min(value = 0, message = "La cantidad disponible no puede ser negativa")
    @Schema(description = "Cantidad disponible del producto", example = "12")
    private  int stock;
}

