package com.example.aydsII.act5.respuesta;

import com.example.aydsII.act5.model.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class PedidoRespuesta {

    private int pedidoId;

    private String cliente;

    private LocalDate fecha;

    private String estado;

    private Double totalPedido;

    private List<ProductoPedidoDTO> productos;
}
