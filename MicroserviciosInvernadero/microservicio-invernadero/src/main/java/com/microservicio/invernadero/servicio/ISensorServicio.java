package com.microservicio.invernadero.servicio;

import com.microservicio.invernadero.entidades.Sensor;

import java.util.List;

public interface ISensorServicio {

    List<Sensor> findAll();

    Sensor findById(Long id);

    void save(Sensor sensor);

    List<Sensor> findByIdInvernadero(Long idInvernadero);
}
