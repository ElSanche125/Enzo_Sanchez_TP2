package com.example.aydsII.act4.service;

import com.example.aydsII.act4.model.*;
import com.example.aydsII.act4.respuesta.ClienteRespuesta;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteRespuesta crearCliente(ClienteDTO dto) {

        // Crear entidad
        Cliente cliente = new Cliente();

        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setFecha_registro(LocalDateTime.now());

        // Guardar en la BD
        Cliente clienteGuardado = clienteRepository.save(cliente);

        // Convertir Entidad en DTO de respuesta
        ClienteRespuesta respuesta = new ClienteRespuesta();

        respuesta.setId(clienteGuardado.getId());
        respuesta.setNombre(clienteGuardado.getNombre());
        respuesta.setApellido(clienteGuardado.getApellido());
        respuesta.setEmail(clienteGuardado.getEmail());
        respuesta.setTelefono(clienteGuardado.getTelefono());

        return respuesta;
    }
}