package com.example.aydsII.act3_act6.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import com.example.aydsII.act3_act6.model.*;

@Service
public class ConversionService {

    private final RestClient restClient;
    private final HistorialConversionesRepository repository;

    public ConversionService(RestClient divisasRestClient, HistorialConversionesRepository repository) {
        this.restClient = divisasRestClient;
        this.repository = repository;
    }

    public HistorialConversiones convertir(double monto, String origen, String destino) {
        String monedaOrigen = origen.toUpperCase();
        String monedaDestino = destino.toUpperCase();

        try {

            Map<String, Object> respuesta = restClient.get()
                    .uri("/v2/rate/{origen}/{destino}",
                            monedaOrigen,
                            monedaDestino)
                    .retrieve()
                    .body(Map.class);

            if (respuesta == null) {
                throw new RuntimeException(
                        "Frankfurter no devolvió información"
                );
            }

            double tasaCambio = ((Number) respuesta.get("rate")).doubleValue();

            double montoConvertido = monto * tasaCambio;

            HistorialConversiones historial = new HistorialConversiones();

            historial.setMonedaOrigen(monedaOrigen);
            historial.setMonedaDestino(monedaDestino);
            historial.setMonto(monto);
            historial.setMontoConvertido(redondear(montoConvertido));
            historial.setTasa(redondear(tasaCambio));
            historial.setFechaConsulta(LocalDateTime.now());

            return repository.save(historial);

        } catch (RestClientResponseException ex) {

            if (ex.getStatusCode().value() == 404 ||
                ex.getStatusCode().value() == 422) {

                throw new IllegalArgumentException(
                        "La moneda de origen o destino no existe"
                );
            }

            throw new RuntimeException(
                    "Frankfurter respondió con un error HTTP: "
                    + ex.getStatusCode().value()
            );

        } catch (ResourceAccessException ex) {

            throw new RuntimeException(
                    "No se pudo conectar con Frankfurter o se produjo un timeout"
            );
        }
    }

    private double redondear(double valor) {

        return Math.round(valor * 100.0) / 100.0;
    }

    public List<Map<String, Object>> historial(String origen, String destino) {

        String monedaOrigen = origen.toUpperCase();
        String monedaDestino = destino.toUpperCase();

        return repository.historial(monedaOrigen,monedaDestino);
    }
}