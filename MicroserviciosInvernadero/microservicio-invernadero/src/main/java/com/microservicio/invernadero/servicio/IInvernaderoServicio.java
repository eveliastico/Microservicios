package com.microservicio.invernadero.servicio;

import com.microservicio.invernadero.entidades.Invernadero;
import com.microservicio.invernadero.http.response.SensorByInvernaderoResponse;

import java.util.List;

public interface IInvernaderoServicio {

    List<Invernadero> findAll();
    Invernadero findById(Long id);

    void save(Invernadero invernadero);

    /*
    * Respuesta personalizada para el cliente.
    * */
    SensorByInvernaderoResponse findSensorByIdInvernadero(Long idInvernadero);


}
