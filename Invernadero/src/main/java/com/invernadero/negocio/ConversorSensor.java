package com.invernadero.negocio;

import com.invernadero.entidades.Sensor;
import java.sql.Timestamp;

/**
 *
 * @author Jorge
 */
public class ConversorSensor {
    
    public Sensor convertStringToSensor(String data) {
        // Dividir el String en partes utilizando ":" como separador
        String[] parts = data.split(";");
        
        // Verificar si se obtuvieron todas las partes necesarias
        if (parts.length != 7) {
            throw new IllegalArgumentException("El String de datos no tiene el formato esperado.");
        }
        
        // Convertir cada parte a su tipo correspondiente
        Long id = Long.parseLong(parts[0].trim());
        Long invernaderoId = Long.parseLong(parts[1].trim());
        float humedad = Float.parseFloat(parts[2].trim());
        float humedadLimite = Float.parseFloat(parts[3].trim());
        float temperatura = Float.parseFloat(parts[4].trim());
        float temperaturaLimite = Float.parseFloat(parts[5].trim());
        Timestamp fechaHora = Timestamp.valueOf(parts[6].trim());
        
        // Crear y retornar un objeto Sensor con los datos convertidos
        return new Sensor(id, invernaderoId, humedad, humedadLimite, temperatura, temperaturaLimite, fechaHora);
        // String dato = "1;1001;60.5;70.0;25.0;30.0;2024-05-16 12:00:00";
    }
    
}
