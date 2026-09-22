package com.example.aydsII.act2.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.example.aydsII.act2.model.Producto;

@Service
public class ProductoService {

    List<Producto> productos = new ArrayList<>();

    public ProductoService() {

        productos.add(new Producto(1, "Mouse inalambrico", "Accesorios", 12000, 25));
        productos.add(new Producto(2, "Teclado mecanico", "Accesorios", 35000, 15));
        productos.add(new Producto(3, "Monitor 24 pulgadas", "Pantallas", 180000, 8));
        productos.add(new Producto(4, "Auriculares Bluetooth", "Audio", 45000, 20));
        productos.add(new Producto(5, "Webcam Full HD", "Camaras", 55000, 10));
        productos.add(new Producto(6, "Disco SSD 1TB", "Almacenamiento", 95000, 12));
        productos.add(new Producto(7, "Memoria RAM 16GB", "Componentes", 70000, 18));
        productos.add(new Producto(8, "Parlante Bluetooth", "Audio", 60000, 14));
    }

    public List<Producto> listarTodos() {
        return productos;
    }

    public List<Producto> filtrar(String categoria, Double precioMin, Double precioMax) {
        // Stream sobre el catálogo, encadenando un filter() por cada filtro opcional.
        Stream<Producto> respuesta = productos.stream()
                .filter(producto -> categoria == null || contiene(producto.getCategoria(), categoria))
                .filter(producto -> precioMin == null || (producto.getPrecio() >= precioMin))
                .filter(producto -> precioMax == null || (producto.getPrecio() <= precioMax));

        return respuesta.toList();
    }

    private boolean contiene(String valor, String filtro) {
        return valor != null && valor.toLowerCase().contains(filtro.toLowerCase());
    }

    public List<Producto> ordenar(String criterio, String orden) {

        Stream<Producto> respuesta = productos.stream();

        if (orden == null || orden.equalsIgnoreCase("asc")) {

            if (criterio.equalsIgnoreCase("nombre")) {
                respuesta = respuesta.sorted(Comparator.comparing(Producto::getNombre));
            } else if (criterio.equalsIgnoreCase("precio")) {
                respuesta = respuesta.sorted(Comparator.comparing(Producto::getPrecio));
            }
        } else if (orden.equalsIgnoreCase("desc")) {

            if (criterio.equalsIgnoreCase("nombre")) {
                respuesta = respuesta.sorted(Comparator.comparing(Producto::getNombre).reversed());
            } else if (criterio.equalsIgnoreCase("precio")) {
                respuesta = respuesta.sorted(Comparator.comparing(Producto::getPrecio).reversed());
            }
        }

        return respuesta.toList();
    }

    public void agregarProducto(Producto p) {
        int id = productos.stream().mapToInt(Producto::getId).max().orElse(0);

        p.setId(id);

        productos.add(p);
    }

    public Producto modificarStock(int id, int cantidad) {

        Producto producto = productos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);

        if (producto == null) {
            return null;
        }

        int nuevoStock = producto.getStock() + cantidad;

        if (nuevoStock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        producto.setStock(nuevoStock);

        return producto;
    }

    public Producto elimnarProducto(int id){
        Producto producto = productos.stream().filter(p ->p.getId() == id).findFirst().orElse(null);

        if (producto == null){
            return null;
        }

        productos.remove(producto);

        return producto;
    }
}