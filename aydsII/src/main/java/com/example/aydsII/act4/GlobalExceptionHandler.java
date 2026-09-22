package com.example.aydsII.act4;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.aydsII.ApiResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> manejarValidaciones(
            MethodArgumentNotValidException ex) {

        Map<String, String> errores = new HashMap<>();

        for (var error : ex.getBindingResult().getFieldErrors()) {
            errores.put(
                error.getField(),
                error.getDefaultMessage()
            );

        }

        return ResponseEntity.badRequest().body(new ApiResponse<>(
            400, "Datos invalidos", errores
        ));
    }
}