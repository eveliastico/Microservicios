package com.microservicio.sensor.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sensores_registrados")
public class SensorRegistro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "invernadero_id")
    private Long invernaderoId;
    @Column(name = "fecha_Hora")
    private Timestamp fechaHora;
    private String marca;
}
