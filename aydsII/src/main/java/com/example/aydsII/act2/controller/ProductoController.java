package com.example.aydsII.act2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aydsII.ApiResponse;
import com.example.aydsII.act2.model.Producto;
import com.example.aydsII.act2.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/catalogo")
@Tag(name = "Productos", description = "Controlador de Productos")
public class ProductoController {

    ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lista productos",
        description = "Lista los productos cargados en memoria")
    public ResponseEntity<ApiResponse<List<Producto>>> listarTodos() {

        return ResponseEntity.ok(
            new ApiResponse<>( 200,"Lista de productos",service.listarTodos())
        );
    }

    @GetMapping("/buscar")
    @Operation(summary = "Busca un producto",
        description = "Filtra los productos de la lista segun filtros opcionales")
    public ResponseEntity<ApiResponse<List<Producto>>> filtrar(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Double precioMin,
            @RequestParam(required = false) Double precioMax) {

        return ResponseEntity.ok(
            new ApiResponse<>(200, "Filtros aplicados",service.filtrar(categoria, precioMin, precioMax))
        );
    }

    @GetMapping("/ordenar")
    @Operation(summary = "Ordena la lista de productos",
        description = "Ordena la lista de Productos segun un criterio y orden")
    public ResponseEntity<ApiResponse<List<Producto>>> ordenar(
            @RequestParam String criterio,
            @RequestParam(required = false) String orden) {

        return ResponseEntity.ok(
            new ApiResponse<>(200,"Productos ordenados",service.ordenar(criterio, orden))
        );
    }

    @PostMapping
    @Operation(summary = "Agrega un producto",
        description = "Agrega un producto al catalogo actual")
    public ResponseEntity<ApiResponse<Producto>> agregarProducto(
            @RequestBody @Valid Producto producto) {

        service.agregarProducto(producto);

        return ResponseEntity.status(201).body(
            new ApiResponse<>( 201,"Producto creado exitosamente",producto)
        );
    }

    @PutMapping("/{id}/stock")
    @Operation(summary = "Modifica el stock",
        description = "Modifica el stock de un producto de un determinado id")
    public ResponseEntity<ApiResponse<Producto>> modificarStock(
            @PathVariable int id,
            @RequestParam int cantidad) {

        try {

            Producto producto = service.modificarStock(id, cantidad);

            if (producto == null) {

                return ResponseEntity.status(404).body(
                    new ApiResponse<>(404,"Producto no encontrado",null)
                );
            }

            return ResponseEntity.ok(
                new ApiResponse<>(200,"Stock modificado exitosamente",producto)
            );

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(
                new ApiResponse<>(400,"La modificación provoca un stock negativo",null)
            );
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un producto",
        description = "Elimina un producto de un determinado id")
    public ResponseEntity<ApiResponse<Producto>> elimnarProducto(@PathVariable int id){

        Producto producto = service.elimnarProducto(id);

        if (producto==null){
            return ResponseEntity.status(404).body(
                new ApiResponse<>(404, "Producto no encontrado", null)
            );
        }

        return ResponseEntity.status(200).body(
            new ApiResponse<>(200, "Producto elimnado con exito", producto)
        );
    }
}