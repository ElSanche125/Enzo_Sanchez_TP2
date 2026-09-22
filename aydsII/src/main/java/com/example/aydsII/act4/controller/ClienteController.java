package com.example.aydsII.act4.controller;

import com.example.aydsII.act4.model.ClienteDTO;
import com.example.aydsII.act4.respuesta.ClienteRespuesta;
import com.example.aydsII.act4.service.ClienteService;
import com.example.aydsII.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @Operation(
        summary = "Crea un cliente",
        description = "Crea y agrega un cliente a la tabla clientes en la BD MYSQL")
    public ResponseEntity<ApiResponse<ClienteRespuesta>> crearCliente(@RequestBody ClienteDTO dto) {

        ClienteRespuesta respuesta = clienteService.crearCliente(dto);

        return ResponseEntity.status(201).body(
            new ApiResponse<>(201,"Cliente creado exitosamente", respuesta)
        );
    }

    @PostMapping("/validado")
    public ResponseEntity<ApiResponse<ClienteRespuesta>> validado(@Valid @RequestBody ClienteDTO dto) {

        ClienteRespuesta respuesta = clienteService.crearCliente(dto);

        return ResponseEntity.status(201).body(
            new ApiResponse<>(201,"Cliente creado exitosamente", respuesta)
        );
    }
    
}