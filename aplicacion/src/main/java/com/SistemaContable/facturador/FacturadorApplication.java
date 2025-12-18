package com.SistemaContable.facturador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FacturadorApplication {
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(FacturadorApplication.class, args);
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }
}