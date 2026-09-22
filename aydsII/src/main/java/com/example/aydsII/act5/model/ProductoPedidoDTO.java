package com.example.aydsII.act5.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class ProductoPedidoDTO {

    private String nombre;

    private String categoria;

    private Integer cantidad;

    private Double subtotal;
}
