package com.microservicio.sensor.persistencia;

import com.microservicio.sensor.entidades.Sensor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepositorio extends CrudRepository<Sensor, Long> {

    /*
    Asi lo haria con un query.
    @Query("SELECT s FROM Sensor s WHERE s.invernaderoId = :idInvernadero")
    List<Sensor> findAllSensor(Long idInvernadero);
    */

    List<Sensor> findAllByInvernaderoId(Long idInvernadero);
}
