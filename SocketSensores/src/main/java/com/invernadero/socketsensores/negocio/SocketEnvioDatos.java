/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.invernadero.socketsensores.negocio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Jorge
 */
public class SocketEnvioDatos {
    
    private String direccionGateway;
    private int puerto;
    
    public SocketEnvioDatos(String direccionGateway, int puerto) {
        this.direccionGateway = direccionGateway;
        this.puerto = puerto;
    }

    public void sendData(String data) {
        Socket socket = null;
        try {
            // Conectar al Gateway
            socket = new Socket(direccionGateway, puerto);
            
            // Obtener el flujo de salida del socket
            OutputStream outputStream = socket.getOutputStream();
            
            // Enviar datos al Gateway
            outputStream.write(data.getBytes());
            
            // Cerrar el flujo de salida y el socket
            outputStream.close();
            socket.close();
            
            System.out.println("Datos enviados correctamente al Gateway.");
        } catch (UnknownHostException e) {
            System.err.println("Error: Host desconocido - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de E/S al enviar datos - " + e.getMessage());
        } finally {
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el socket - " + e.getMessage());
                }
            }
        }
    }
}
