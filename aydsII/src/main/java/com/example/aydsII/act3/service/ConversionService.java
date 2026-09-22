package com.example.aydsII.act3.service;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import com.example.aydsII.act3.model.Conversion;

@Service
public class ConversionService {

    private final RestClient restClient;

    public ConversionService(RestClient divisasRestClient) {
        this.restClient = divisasRestClient;
    }

    public Conversion convertir(double monto, String origen, String destino) {

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

            double tasaCambio =
                    ((Number) respuesta.get("rate")).doubleValue();

            double montoConvertido = monto * tasaCambio;

            String fechaTexto = (String) respuesta.get("date");

            LocalDate fecha = LocalDate.parse(fechaTexto);

            return new Conversion(
                    monto,
                    monedaOrigen,
                    monedaDestino,
                    redondear(tasaCambio),
                    redondear(montoConvertido),
                    fecha
            );

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
}