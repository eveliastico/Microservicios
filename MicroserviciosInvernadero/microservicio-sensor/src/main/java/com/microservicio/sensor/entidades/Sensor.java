package com.microservicio.sensor.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sensores")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "invernadero_id")
    private Long invernaderoId;
    private float humedad;
    @Column(name = "humedad_lim")
    private float humedadLimite;
    private float temperatura;
    @Column(name = "temperatura_lim")
    private float temperaturaLimite;
    @Column(name = "fecha_Hora")
    private Timestamp fechaHora;

}
