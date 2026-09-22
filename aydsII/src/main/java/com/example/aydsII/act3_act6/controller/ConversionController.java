package com.example.aydsII.act3_act6.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

import com.example.aydsII.ApiResponse;
import com.example.aydsII.act3_act6.model.HistorialConversiones;
import com.example.aydsII.act3_act6.service.ConversionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/divisas")
@Tag(name = "Conversor Divisas", description = "Realiza conversiones de divisas")
public class ConversionController {

    private final ConversionService conversionService;

    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @PostMapping("/convertir")
    @Operation(summary = "Convierte un monto entre dos monedas",
            description = "Valida los parámetros, consulta Frankfurter y devuelve los datos relevantes de la conversión")
    public ResponseEntity<ApiResponse<HistorialConversiones>> convertir(@RequestParam @Positive(message = "El monto debe ser mayor que 0") double monto,
                                                            @RequestParam
                                                            @Pattern(
                                                                    regexp = "[A-Za-z]{3}",
                                                                    message = "El código de origen debe tener 3 letras")
                                                            String origen,

                                                            @RequestParam
                                                            @Pattern(
                                                                    regexp = "[A-Za-z]{3}",
                                                                    message = "El código de destino debe tener 3 letras")
                                                            String destino) {

        try {

            HistorialConversiones resultado =
                    conversionService.convertir(monto, origen, destino);

            return ResponseEntity.ok(
                    new ApiResponse<>(200,"Conversión realizada exitosamente",resultado)
            );

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(400,e.getMessage(),null)
            );

        } catch (RuntimeException e) {

            return ResponseEntity.status(502).body(
                    new ApiResponse<>(502,e.getMessage(),null)
            );
        }
    }

    @GetMapping("/historial")
    public ResponseEntity<ApiResponse<List<Map<String,Object>>>> historial(@RequestParam 
                                                                           @Pattern(
                                                                           regexp = "[A-Za-z]{3}",
                                                                           message = "El código de origen debe tener 3 letras")
                                                                           String origen,

                                                                           @RequestParam
                                                                           @Pattern(
                                                                           regexp = "[A-Za-z]{3}",
                                                                           message = "El código de destino debe tener 3 letras")
                                                                           String destino) {
        return ResponseEntity.status(200).body(new ApiResponse<>(
                200, "historial de conversiones", conversionService.historial(origen, destino)
        ));
    }
    
}