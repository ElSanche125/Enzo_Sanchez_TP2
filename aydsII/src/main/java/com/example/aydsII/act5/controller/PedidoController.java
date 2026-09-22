package com.example.aydsII.act5.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aydsII.act5.respuesta.PedidoRespuesta;
import com.example.aydsII.act5.service.PedidoService;
import com.example.aydsII.ApiResponse;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<PedidoRespuesta>>> buscarPedidos(
            @RequestParam(required = false) Integer clienteId,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta,
            @RequestParam(required = false) String estado) {

        List<PedidoRespuesta> respuesta =
                pedidoService.buscarPedidos(clienteId, categoria, fechaDesde, fechaHasta, estado);

        return ResponseEntity.ok(new ApiResponse<>(
            200, "Pedidos filtrados correctamente", respuesta
        ));
    }
}