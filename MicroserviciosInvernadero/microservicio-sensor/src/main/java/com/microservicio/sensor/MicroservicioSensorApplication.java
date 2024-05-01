package com.microservicio.sensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicioSensorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioSensorApplication.class, args);
	}

}
