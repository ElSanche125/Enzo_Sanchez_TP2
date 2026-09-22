package com.example.aydsII.act4.respuesta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRespuesta {

    private Integer id;

    private String nombre;

    private String apellido;

    private String email;
    
    private String telefono;
}