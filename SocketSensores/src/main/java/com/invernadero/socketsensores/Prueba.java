/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.invernadero.socketsensores;

import com.invernadero.socketsensores.negocio.SocketEnvioDatos;

/**
 *
 * @author Jorge
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String gatewayAddress = "127.0.0.1"; // Dirección IP del Gateway
        int gatewayPort = 5555; // Puerto del Gateway
        
        SocketEnvioDatos sender = new SocketEnvioDatos(gatewayAddress, gatewayPort);
        String dataToSend = "SOPOTAMADRE";
        String dataToSend2 = "Temperatura: 30°C, Humedad: 60%";
        sender.sendData(dataToSend);
        sender.sendData(dataToSend2);
    }
    
}
