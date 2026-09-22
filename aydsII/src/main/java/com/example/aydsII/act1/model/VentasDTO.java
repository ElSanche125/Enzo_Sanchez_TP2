package com.example.aydsII.act1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representa una venta de un producto")
public class VentasDTO {

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Schema(description = "Nombre del producto", example = "Mouse inalambrico")
    private String producto;

    @Positive (message = "La cantidad debe ser mayor a 0")
    @Schema(description = "Cantidad de productos vendidos", example = "3")
    private int cantidad;

    @Positive (message = "La cantidad debe ser mayor a 0")
    @Schema(description = "Precio del producto", example = "4500")
    private double precioUnitario;
}

/* Datos para usar
[
  {
    "producto": "Mouse inalambrico",
    "cantidad": 3,
    "precioUnitario": 4500
  },
  {
    "producto": "Auricular inalambrico",
    "cantidad": 1,
    "precioUnitario": 13500
  },
  {
    "producto": "Teclado mecanico",
    "cantidad": 5,
    "precioUnitario": 12000
  },
  {
    "producto": "Teclado mecanico",
    "cantidad": 4,
    "precioUnitario": 9600
  },
  {
    "producto": "Monitor 24 pulgadas",
    "cantidad": 2,
    "precioUnitario": 85000
  },
  {
    "producto": "Tarjeta grafica Nvidia",
    "cantidad": 1,
    "precioUnitario": 170000
  },
  {
    "producto": "Auriculares",
    "cantidad": 8,
    "precioUnitario": 15000
  },
  {
    "producto": "Webcam HD",
    "cantidad": 3,
    "precioUnitario": 22000
  }
]
*/