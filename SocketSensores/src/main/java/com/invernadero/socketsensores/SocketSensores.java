package com.invernadero.socketsensores;

import com.invernadero.socketsensores.negocio.Gateway;
import com.invernadero.socketsensores.negocio.SocketEnvioDatos;

/**
 *
 * @author Jorge
 */
public class SocketSensores {

    public static void main(String[] args) {

        String gatewayAddress = "127.0.0.1"; // Dirección IP del Gateway
        int gatewayPort = 5555; // Puerto del Gateway
        
        SocketEnvioDatos sender = new SocketEnvioDatos(gatewayAddress, gatewayPort);
        String dataToSend = "Temperatura: 26°C, Humedad: 60%";
        String dataToSend2 = "Temperatura: 30°C, Humedad: 60%";
        sender.sendData(dataToSend);
        sender.sendData(dataToSend2);
    }
}
