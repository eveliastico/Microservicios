package com.microservicio.sensor.servicio;

import com.microservicio.sensor.entidades.Sensor;
import com.microservicio.sensor.entidades.SensorRegistro;
import com.microservicio.sensor.persistencia.SensorNuevoRepositorio;
import com.microservicio.sensor.persistencia.SensorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServicioImpl implements ISensorServicio{

    @Autowired
    private SensorRepositorio sensorRepositorio;

    @Autowired
    private SensorNuevoRepositorio sensorNuevoRepositorio;

    @Override
    public List<Sensor> findAll() {
        return (List<Sensor>) sensorRepositorio.findAll();
    }

    @Override
    public Sensor findById(Long id) {
        return sensorRepositorio.findById(id).orElseThrow();
    }

    @Override
    public List<SensorRegistro> findAllSensorRegistro() {
        return (List<SensorRegistro>) sensorNuevoRepositorio.findAll();
    }

    @Override
    public SensorRegistro findByIdSensorRegistro(Long id) {
        return sensorNuevoRepositorio.findById(id).orElseThrow();
    }

    @Override
    public void save(Sensor sensor) {
        sensorRepositorio.save(sensor);
    }

    @Override
    public void saveNew(SensorRegistro sensorRegistro) {
        sensorNuevoRepositorio.save(sensorRegistro);
    }

    @Override
    public List<Sensor> findByIdInvernadero(Long idInvernadero) {
        return sensorRepositorio.findAllByInvernaderoId(idInvernadero);
    }
}
