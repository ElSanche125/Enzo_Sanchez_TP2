package com.example.aydsII.act5.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.aydsII.act5.model.*;
import com.example.aydsII.act5.respuesta.PedidoRespuesta;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<PedidoRespuesta> buscarPedidos(
            Integer clienteId,
            String categoria,
            LocalDate fechaDesde,
            LocalDate fechaHasta,
            String estado) {

        List<Pedido> pedidos = pedidoRepository.buscarPedidos(
                clienteId, categoria, fechaDesde, fechaHasta, estado);

        List<PedidoRespuesta> respuesta = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            PedidoRespuesta dto = convertirAPedidoDTO(pedido);
            respuesta.add(dto);
        }

        return respuesta;
    }

    private PedidoRespuesta convertirAPedidoDTO(Pedido pedido) {

        PedidoRespuesta dto = new PedidoRespuesta();

        dto.setPedidoId(pedido.getId());

        dto.setFecha(pedido.getFecha());

        dto.setEstado(pedido.getEstado());

        String nombreCompleto = pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellido();

        dto.setCliente(nombreCompleto);

        List<ProductoPedidoDTO> productos = new ArrayList<>();

        double total = 0;

        for (DetallePedido detalle : pedido.getDetalles()) {

            ProductoPedidoDTO productoDTO = new ProductoPedidoDTO();

            productoDTO.setNombre(detalle.getProducto().getNombre());

            productoDTO.setCategoria(
                    detalle.getProducto()
                            .getCategoria()
                            .getNombre());

            productoDTO.setCantidad(detalle.getCantidad());

            double subtotal = detalle.getCantidad() * detalle.getPrecioUnitario();

            productoDTO.setSubtotal(subtotal);

            total += subtotal;

            productos.add(productoDTO);
        }

        dto.setProductos(productos);
        dto.setTotalPedido(total);

        return dto;
    }
}
