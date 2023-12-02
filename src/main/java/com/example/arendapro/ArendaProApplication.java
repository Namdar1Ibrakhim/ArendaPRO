package com.example.arendapro;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Library APIS", version = "1.0", description = "Library Management Apis"))
@EnableCaching
public class ArendaProApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArendaProApplication.class, args);
    }

}
