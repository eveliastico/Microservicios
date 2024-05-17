package com.invernadero.socketsensores.negocio;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import dominio.Sensor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
/**
 *
 * @author Jorge
 */
public class Gateway {
    
    private final static String QUEUE_NAME = "ColaDatos";
    private final static String NEW_QUEUE_NAME = "servicio";
    private int puerto;

    public Gateway(int puerto) {
        this.puerto = puerto;
    }

    public void start() {
        ServerSocket serverSocket = null;
        try {
            // Crear el socket del servidor en el puerto especificado
            serverSocket = new ServerSocket(puerto);
            System.out.println("Gateway iniciado. Esperando conexiones en el puerto " + puerto);
            
            while (true) {
                // Esperar a que llegue una conexión desde un sensor
                Socket socket = serverSocket.accept();
                
                // Procesar los datos recibidos del sensor
                processSensorData(socket);
                
                // Cerrar el socket del sensor
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error de E/S al iniciar el Gateway - " + e.getMessage());
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el socket del servidor - " + e.getMessage());
                }
            }
        }
    }

    private void processSensorData(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        
        StringBuilder dataBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            dataBuilder.append(line);
        }
        
        String sensorData = dataBuilder.toString();
        System.out.println("Datos recibidos del sensor: " + sensorData);
        // Aquí puedes realizar acciones adicionales con los datos recibidos, como almacenarlos en una base de datos o procesarlos de alguna otra manera.
    }
    
    

    public static void main(String[] args) throws Exception{
        // Ejemplo de uso
        int gatewayPort = 5555; // Puerto en el que el Gateway escucha las conexiones
        
        Gateway gateway = new Gateway(gatewayPort);
        gateway.start();
       
    }
}
