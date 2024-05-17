package com.microservicio.alarma.negocio;

import com.google.gson.Gson;
import com.microservicio.alarma.dto.SensorDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ColaRabbitMQ {

    Gson gson;
    @RabbitListener(queues = "queue1")
    public void consumeQueue1(String message) {
        //Se verifica que la temperatura y humedad esten por debajo del rango limite
        gson = new Gson();
        SensorDTO sensor = gson.fromJson(message, SensorDTO.class);
//        System.out.println(sensor.toString());
        if(sensor.getHumedad() > sensor.getHumedadLimite()){
            System.out.println("HUMEDAD ALTAAA: "+sensor.toString());
        }
        if(sensor.getTemperatura() > sensor.getTemperaturaLimite()){
            System.out.println("TEMPERATURA ALTAAA: "+sensor.toString());
        }
        if(sensor.getTemperatura() > sensor.getTemperaturaLimite() && sensor.getHumedad() > sensor.getHumedadLimite()){
            System.out.println("TEMPERATURA Y HUMEDAD ALTAAASSS: "+sensor.toString());
        }

    }

}
