package com.microservicio.alarma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicioAlarmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioAlarmaApplication.class, args);
	}

}
