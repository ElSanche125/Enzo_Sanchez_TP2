package com.example.aydsII.act1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.aydsII.act1.model.VentasDTO;

@Service
public class VentasService {

    public double totalFacturado(List<VentasDTO> ventas) {

        double total = 0;

        for (VentasDTO venta : ventas) {
            total += venta.getCantidad() * venta.getPrecioUnitario();
        }

        return total;
    }

    public List<VentasDTO> ventaMayor(List<VentasDTO> ventas) {
        List<VentasDTO> ventaMayor = new ArrayList<>();
        double importe;
        double mayor = ventas.get(0).getPrecioUnitario() * ventas.get(0).getCantidad();

        for (VentasDTO venta : ventas) {
            importe = venta.getPrecioUnitario() * venta.getCantidad();

            if (importe > mayor) {

                mayor = importe;

                ventaMayor.clear();

                ventaMayor.add(venta);

            } else if (importe == mayor) {

                ventaMayor.add(venta);
            }
        }

        return ventaMayor;
    }

    public List<VentasDTO> ventaMenor(List<VentasDTO> ventas) {
        List<VentasDTO> ventaMenor = new ArrayList<>();
        double importe;
        double menor = ventas.get(0).getPrecioUnitario() * ventas.get(0).getCantidad();

        for (VentasDTO venta : ventas) {
            importe = venta.getPrecioUnitario() * venta.getCantidad();

            if (importe < menor) {

                menor = importe;

                ventaMenor.clear();

                ventaMenor.add(venta);

            } else if (importe == menor) {

                ventaMenor.add(venta);
            }
        }

        return ventaMenor;
    }

    public String productoMasVendido(List<VentasDTO> ventas) {

        Map<String, Integer> productos = new HashMap<>();

        for (VentasDTO venta : ventas) {

            String producto = venta.getProducto();
            int cantidad = venta.getCantidad();

            productos.put(
                    producto,
                    productos.getOrDefault(producto, 0) + cantidad);
        }

        String productoMasVendido = null;
        int mayorCantidad = 0;

        for (Map.Entry<String, Integer> entrada : productos.entrySet()) {

            if (entrada.getValue() > mayorCantidad) {

                mayorCantidad = entrada.getValue();
                productoMasVendido = entrada.getKey();
            }
        }

        return productoMasVendido;
    }

    public Map<String, Object> aplicarDescuento(List<VentasDTO> ventas, int descuento) {
        List<Map<String, Object>> datos = new ArrayList<>();
        double totalConDescuento=0;

        for(VentasDTO venta : ventas){
            double montoDescuento = venta.getPrecioUnitario() * (descuento/100.0);
            Map<String, Object> v = new  HashMap<>();
            
            v.put("producto", venta.getProducto());
            v.put("cantidad", venta.getCantidad());
            v.put("precioUnitario", venta.getPrecioUnitario());
            v.put("montoConDescuento", montoDescuento);

            totalConDescuento += montoDescuento;

            datos.add(v);
        }

        Map<String, Object> respuesta = new HashMap<>();

        respuesta.put("ventas", datos);
        respuesta.put("totalConDescuento", totalConDescuento);

        return respuesta;
    }

}