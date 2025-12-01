package com.SIstemaContable.facturador;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FacturadorApplication {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = new SpringApplicationBuilder(FacturadorApplication.class)
                .headless(false)
                .run(args);
        Application.launch(JavaFxApp.class, args);
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }
}
