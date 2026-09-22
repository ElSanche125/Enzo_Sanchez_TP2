package com.example.aydsII.act5.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("""
    SELECT DISTINCT p
    FROM Pedido p
    JOIN p.cliente c
    JOIN p.detalles d
    JOIN d.producto pr
    JOIN pr.categoria cat
    WHERE (:clienteId IS NULL OR c.id = :clienteId)
    AND (:categoria IS NULL OR cat.nombre = :categoria)
    AND (:fechaDesde IS NULL OR p.fecha >= :fechaDesde)
    AND (:fechaHasta IS NULL OR p.fecha <= :fechaHasta)
    AND (:estado IS NULL OR p.estado = :estado)
    """)
    List<Pedido> buscarPedidos(
                                @Param("clienteId") Integer clienteId,
                                @Param("categoria") String categoria,
                                @Param("fechaDesde") LocalDate fechaDesde,
                                @Param("fechaHasta") LocalDate fechaHasta,
                                @Param("estado") String estado);
                                
}