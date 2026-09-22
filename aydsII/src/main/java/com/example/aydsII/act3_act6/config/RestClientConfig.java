package com.example.aydsII.act3_act6.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    private static final String BASE_URL = "https://api.frankfurter.dev";

    @Bean
    public RestClient divisasRestClient() {

        SimpleClientHttpRequestFactory factory =
                new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(Duration.ofSeconds(3));
        factory.setReadTimeout(Duration.ofSeconds(3));

        return RestClient.builder()
                .baseUrl(BASE_URL)
                .requestFactory(factory)
                .build();
    }
}