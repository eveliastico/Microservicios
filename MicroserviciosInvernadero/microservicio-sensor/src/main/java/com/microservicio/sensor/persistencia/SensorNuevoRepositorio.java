package com.microservicio.sensor.persistencia;

import com.microservicio.sensor.entidades.SensorRegistro;
import org.springframework.data.repository.CrudRepository;

public interface SensorNuevoRepositorio extends CrudRepository<SensorRegistro, Long> {
}
