package com.facturacion.almacenamiento2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Almacenamiento2Application {

	public static void main(String[] args) {
		SpringApplication.run(Almacenamiento2Application.class, args);
	}

}
