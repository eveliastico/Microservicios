package com.microservicio.sensor.servicio;

import com.microservicio.sensor.entidades.Sensor;
import com.microservicio.sensor.entidades.SensorRegistro;

import java.util.List;

public interface ISensorServicio {

    List<Sensor> findAll();

    Sensor findById(Long id);

    List<SensorRegistro> findAllSensorRegistro();

    SensorRegistro findByIdSensorRegistro(Long id);

    void save(Sensor sensor);

    void saveNew(SensorRegistro sensorRegistro);

    List<Sensor> findByIdInvernadero(Long idInvernadero);
}
