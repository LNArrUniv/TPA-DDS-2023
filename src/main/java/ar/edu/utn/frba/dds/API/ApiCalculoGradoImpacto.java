package ar.edu.utn.frba.dds.API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ar.edu.utn.frba.dds")
public class ApiCalculoGradoImpacto {

    public static void main(String[] args) {
        SpringApplication.run(ApiCalculoGradoImpacto.class, args);
    }
}