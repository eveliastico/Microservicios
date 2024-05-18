package com.invernadero.gateway;

import com.google.gson.Gson;
import com.invernadero.entidades.Sensor;
import com.invernadero.negocio.ConversorSensor;
import com.rabbitmq.client.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gateway {

    private static final String EXCHANGE_NAME = "logs";
    private static final String QUEUE_NAME_1 = "queue1";
    private static final String ROUTING_KEY = "";
    private int puerto;
    private ConnectionFactory factory;
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

            // Declarar la cola como no duradera (durable = false)
            channel.queueDeclare(QUEUE_NAME_1, false, false, false, null);
            channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME, ROUTING_KEY);

            System.out.println("Colas RabbitMQ creadas exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al crear las colas RabbitMQ: " + e.getMessage());
        } catch (TimeoutException ex) {
            Logger.getLogger(Gateway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
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

    private void procesarDatos(Socket socket) throws IOException {
        try (InputStream inputStream = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            StringBuilder dataBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                dataBuilder.append(line);
            }

            String sensorData = dataBuilder.toString();
            Sensor sensor = new ConversorSensor().convertStringToSensor(sensorData);
            System.out.println("Dato recibido del sensor: " + sensorData);

            Gson gson = new Gson();
            String message = gson.toJson(sensor);

            enviarMensajeCola(QUEUE_NAME_1, message);
        }
    }

    private void enviarMensajeCola(String queueName, String message) throws IOException {
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
        System.out.println("Mensaje enviado a la cola " + queueName + ": " + message);
    }

    public static void main(String[] args) {
        int gatewayPort = 5555;
        Gateway gateway = new Gateway(gatewayPort);
        try {
            gateway.start();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}