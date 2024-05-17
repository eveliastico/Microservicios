/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.sql.Timestamp;

/**
 *
 * @author Jorge
 */
public class Sensor {
    
    private Long id;
    private Long invernaderoId;
    private float humedad;
    private float humedadLimite;
    private float temperatura;
    private float temperaturaLimite;
    private Timestamp fechaHora;
    
    public Sensor() {
    }

    public Sensor(Long invernaderoId, float humedad, float humedadLimite, float temperatura, float temperaturaLimite, Timestamp fechaHora) {
        this.invernaderoId = invernaderoId;
        this.humedad = humedad;
        this.humedadLimite = humedadLimite;
        this.temperatura = temperatura;
        this.temperaturaLimite = temperaturaLimite;
        this.fechaHora = fechaHora;
    }
    
    public Sensor(Long id, Long invernaderoId, float humedad, float humedadLimite, float temperatura, float temperaturaLimite, Timestamp fechaHora) {
        this.id = id;
        this.invernaderoId = invernaderoId;
        this.humedad = humedad;
        this.humedadLimite = humedadLimite;
        this.temperatura = temperatura;
        this.temperaturaLimite = temperaturaLimite;
        this.fechaHora = fechaHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvernaderoId() {
        return invernaderoId;
    }

    public void setInvernaderoId(Long invernaderoId) {
        this.invernaderoId = invernaderoId;
    }

    public float getHumedad() {
        return humedad;
    }

    public void setHumedad(float humedad) {
        this.humedad = humedad;
    }

    public float getHumedadLimite() {
        return humedadLimite;
    }

    public void setHumedadLimite(float humedadLimite) {
        this.humedadLimite = humedadLimite;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getTemperaturaLimite() {
        return temperaturaLimite;
    }

    public void setTemperaturaLimite(float temperaturaLimite) {
        this.temperaturaLimite = temperaturaLimite;
    }

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    
    
}
