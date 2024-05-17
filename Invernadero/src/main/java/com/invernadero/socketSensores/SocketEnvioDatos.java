package com.invernadero.socketSensores;

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
            socket = new Socket(direccionGateway, puerto);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(data.getBytes());
            outputStream.flush();
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
    
    public static void main(String[] args) {
        
        String gatewayAddress = "127.0.0.1";
        int gatewayPort = 5555;
        
        SocketEnvioDatos sender = new SocketEnvioDatos(gatewayAddress, gatewayPort);
        String dataToSend = "2;10;60.5;50.0;25.0;30.0;2024-05-16 12:00:00";
        String dataToSend2 = "2;10;30.5;50.0;35.0;30.0;2024-05-16 12:00:00";
//        String dataToSend2 = "2;1001;60.5;70.0;25.0;30.0;2024-05-16 12:00:00";
        sender.sendData(dataToSend);
//        sender.sendData(dataToSend2);
//        sender.sendData(dataToSend2);
        
    }
    
}
