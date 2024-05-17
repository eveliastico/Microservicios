package com.invernadero.gateway;

import com.google.gson.Gson;
import com.invernadero.entidades.Sensor;
import com.invernadero.negocio.ConversorSensor;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class Gateway {

    private static List<String> listaDatos = new ArrayList<>();
    private ConversorSensor conversor = new ConversorSensor();
    private int puerto;

    private static final String EXCHANGE_NAME = "logs";
    private static final String QUEUE_NAME_1 = "queue1";
    private static final String QUEUE_NAME_2 = "queue2";
    private static final String QUEUE_NAME_3 = "queue3";
    private static final String ROUTING_KEY = "";
    ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    public Gateway(int puerto) {
        this.puerto = puerto;
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

//            //Cola 1
//            channel.queueDeclare(QUEUE_NAME_1, false, false, false, null);
//            channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME, ROUTING_KEY);

//            //Cola2
//            channel.queueDeclare(QUEUE_NAME_2, false, false, false, null);
//            channel.queueBind(QUEUE_NAME_2, EXCHANGE_NAME, ROUTING_KEY);
//

            System.out.println("Colas RabbitMQ creadas exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al crear las colas RabbitMQ: " + e.getMessage());
        } catch (TimeoutException ex) {
            Logger.getLogger(Gateway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() throws Exception {
        ServerSocket serverSocket = null;
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            serverSocket = new ServerSocket(puerto);
            System.out.println("Gateway iniciado. Esperando conexiones en el puerto " + puerto);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("CICLO WHILE");
                procesarDatos(socket);

            }
        } catch (IOException e) {
            System.err.println("Error de E/S al iniciar el Gateway - " + e.getMessage());
        }
    }

    private void procesarDatos(Socket socket) throws IOException, Exception {
        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder dataBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            dataBuilder.append(line);
        }

        String sensorData = dataBuilder.toString();

        Sensor sensor = conversor.convertStringToSensor(sensorData);
        System.out.println("Dato recibido del sensor: " + sensorData);

        Gson gson = new Gson();
        String message = gson.toJson(sensor);

//        try{
//            channel.basicPublish(EXCHANGE_NAME, null, null, message.getBytes("UTF-8"));
//            System.out.println("Mensaje enviado: " + message);
//        }catch(IOException e) {
//            System.err.println("Error al enviar mensaje a la cola: " + e.getMessage());
//        }
        
        try {
            enviarMensajeCola(QUEUE_NAME_1, message);
//            enviarMensajeCola(QUEUE_NAME_2, message);
//            enviarMensajeCola(QUEUE_NAME_3, message);
        } catch (IOException e) {
            System.err.println("Error al enviar mensaje a la cola: " + e.getMessage());
        }

    }

    private void enviarMensajeCola(String queueName, String message) throws IOException {
        channel.basicPublish(EXCHANGE_NAME, queueName, null, message.getBytes("UTF-8"));
        System.out.println("Mensaje enviado a la cola " + queueName + ": " + message);
    }

    public static void main(String[] args) {
        int gatewayPort = 5555;
        Gateway gateway = new Gateway(gatewayPort);
        try {
            gateway.start();
        } catch (Exception e) {
            System.out.println("Error asdasd" + e.getMessage());
        }

    }
}
