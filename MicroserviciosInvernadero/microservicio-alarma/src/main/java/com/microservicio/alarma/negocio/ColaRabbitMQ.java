package com.microservicio.alarma.negocio;

import com.google.gson.Gson;
import com.microservicio.alarma.dto.SensorDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ColaRabbitMQ {

    @Autowired
    MailManager mailManager;

    @Autowired
    private RestTemplate restTemplate;

    private final Gson gson = new Gson();

    @RabbitListener(queues = "queue1")
    public void consumeQueue1(byte[] message) {
        try {
            String messageStr = new String(message);
            SensorDTO sensor = gson.fromJson(messageStr, SensorDTO.class);
            System.out.println("DATO: " + sensor.getId());

            if (sensor.getTemperatura() > sensor.getTemperaturaLimite() || sensor.getHumedad() > sensor.getHumedadLimite()) {
                mailManager.enviarEmail("jorge.estrada204169@potros.itson.edu.mx", "ALARMA: Anomalias en los datos", sensor.toString());
                System.out.println("TEMPERATURA Y HUMEDAD ALTAS: " + sensor.toString());
            }

//            String sensorServiceUrl = "http://localhost:8080/api/sensor/create"; // Ajusta el puerto y URL según sea necesario
//            restTemplate.postForObject(sensorServiceUrl, sensor, Void.class);

        } catch (Exception ex) {
            System.err.println("Error al procesar el mensaje: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
