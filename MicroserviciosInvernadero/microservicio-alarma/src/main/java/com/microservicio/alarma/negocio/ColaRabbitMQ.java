package com.microservicio.alarma.negocio;

import com.google.gson.Gson;
import com.microservicio.alarma.dto.SensorDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ColaRabbitMQ {

    @Autowired
    MailManager mailManager;

    Gson gson;
    @RabbitListener(queues = "queue1")
    public void consumeQueue1(String message) {
        //Se verifica que la temperatura y humedad esten por debajo del rango limite
        gson = new Gson();
        SensorDTO sensor = gson.fromJson(message, SensorDTO.class);
        if(sensor.getTemperatura() > sensor.getTemperaturaLimite() || sensor.getHumedad() > sensor.getHumedadLimite()){
            mailManager.enviarEmail("jorge.estrada204169@potros.itson.edu.mx", "ALARMA: Anomalias en los datos", sensor.toString());
            System.out.println("TEMPERATURA Y HUMEDAD ALTAAASSS: "+sensor.toString());
        }

    }

}
