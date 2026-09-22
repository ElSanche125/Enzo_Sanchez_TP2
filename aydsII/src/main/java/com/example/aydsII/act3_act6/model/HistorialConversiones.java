package com.example.aydsII.act3_act6.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "historial_conversiones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialConversiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "moneda_origen")
    private String monedaOrigen;

    @Column(name = "moneda_destino")
    private String monedaDestino;

    private Double monto;

    @Column(name = "monto_convertido")
    private Double montoConvertido;

    private Double tasa;

    @Column(name = "fecha_consulta")
    private LocalDateTime fechaConsulta;
}