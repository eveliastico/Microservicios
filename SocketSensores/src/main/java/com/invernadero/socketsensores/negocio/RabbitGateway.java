package com.invernadero.socketsensores.negocio;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import dominio.Sensor;
import java.nio.charset.StandardCharsets;


/**
 *
 * @author Jorge
 */
public class RabbitGateway {
    
    private final static String QUEUE_NAME = "ColaDatos";
    private final static String NEW_QUEUE_NAME = "servicio";

    public static void main(String[] args) throws Exception{

        Gson gson = new Gson();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Channel newChannel = connection.createChannel();
        newChannel.queueDeclare(NEW_QUEUE_NAME, false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            Sensor sensor = gson.fromJson(message, Sensor.class);

            System.out.println(sensor.toString());

            newChannel.basicPublish("", NEW_QUEUE_NAME, null, message.getBytes());
            
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }
}
