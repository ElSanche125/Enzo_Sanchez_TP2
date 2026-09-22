package com.example.aydsII.act3_act6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConversionAplication {

    public static void main(String[] args) {

        System.setProperty("spring.profiles.active", "act3_act6");

        SpringApplication.run(ConversionAplication.class, args);
    }
}