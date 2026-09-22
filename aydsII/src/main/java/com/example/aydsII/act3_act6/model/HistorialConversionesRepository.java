package com.example.aydsII.act3_act6.model;

import java.util.Map;
import java.util.List;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HistorialConversionesRepository extends JpaRepository<HistorialConversiones, Integer> {

        @Query(value = """
        SELECT fecha_consulta AS fecha,
               tasa AS tasaCambio
        FROM historial_conversiones
        WHERE moneda_origen = :origen
          AND moneda_destino = :destino
        ORDER BY fecha_consulta DESC
        """, nativeQuery = true)
        List<Map<String, Object>> historial(@Param("origen") String origen,@Param("destino") String destino);
}