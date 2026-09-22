package com.example.aydsII.act1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aydsII.ApiResponse;
import com.example.aydsII.act1.model.VentasDTO;
import com.example.aydsII.act1.service.VentasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ventas")
@Tag(name = "Ventas", description = "Controlador de ventas")
public class VentasController {

    private final VentasService service;

    public VentasController(VentasService service) {
        this.service = service;
    }

    @PostMapping("/estadisticas")
    @Operation(
        summary = "Devuelve las estadísticas de una lista de ventas",
        description = "Devuelve total facturado, cantidad de ventas, ticket promedio, venta mayor y menor y producto más vendido"
    )
    public ResponseEntity<ApiResponse<Map<String, Object>>> estadisticas(
            @RequestBody @Valid List<VentasDTO> ventas) {

        if (ventas.isEmpty()) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>(400,"La lista de ventas no puede estar vacía",null)
            );
        }
        
        return ResponseEntity.ok(
            new ApiResponse<>(200,"Operación completada exitosamente",
            service.obtenerEstadisticas(ventas))
        );
    }

    @PostMapping("/aplicar-descuento")
    @Operation(
        summary = "Aplica el descuento a las ventas",
        description = "Aplica el descuento especificado a la lista de VentasDTO"
    )
    public ResponseEntity<ApiResponse<Map<String, Object>>> aplicarDescuento(
            @RequestBody @Valid List<VentasDTO> ventas,
            @RequestParam int descuento) {

        return ResponseEntity.ok(
            new ApiResponse<>(200,"Descuento aplicado exitosamente",
                service.aplicarDescuento(ventas, descuento)
            )
        );
    }
}