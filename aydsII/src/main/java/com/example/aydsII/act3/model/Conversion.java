package com.example.aydsII.act3.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Schema(description = "Representa un producto")
public class Conversion {

    @Schema(description = "Monto que se pidió convertir", example = "100") 
    double montoOriginal;

    @Schema(description = "Código de la moneda de origen", example = "USD") 
    String monedaOrigen;

    @Schema(description = "Código de la moneda de destino", example = "ARS") 
    String monedaDestino;

    @Schema(description = "Tasa de cambio aplicada", example = "1234.56")
    double tasaCambio;

    @Schema(description = "Monto ya convertido", example = "123456.0")
    double montoConvertido;

    @Schema(description = "Fecha de la tasa de cambio", example = "2026-09-02")
    LocalDate fecha;
}
