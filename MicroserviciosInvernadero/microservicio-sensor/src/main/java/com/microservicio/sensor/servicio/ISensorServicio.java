package com.microservicio.sensor.servicio;

import com.microservicio.sensor.entidades.Sensor;

import java.util.List;

public interface ISensorServicio {

    List<Sensor> findAll();

    Sensor findById(Long id);

    void save(Sensor sensor);

    List<Sensor> findByIdInvernadero(Long idInvernadero);
}
