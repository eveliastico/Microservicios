package com.microservicio.invernadero.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "sensores")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "humedad")
    private float humedad;
    @Column(name = "temperatura")
    private float temperatura;
    @Column(name = "invernadero_id")
    private Long invernaderoId;


}
