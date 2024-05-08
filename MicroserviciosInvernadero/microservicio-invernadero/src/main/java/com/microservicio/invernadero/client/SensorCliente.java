package com.microservicio.invernadero.client;

import com.microservicio.invernadero.dto.SensorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//Nombre del servicio al cual se consultara.
@FeignClient(name = "msvc-sensor", url = "localhost:8092/api/sensor")
public interface SensorCliente {

    /*
    * No se debe de recibir una Entidad(Sensor) por lo que se necesita crear una DTO.
    * */
    @GetMapping("search-by-invernadero/{idInvernadero}")
    List<SensorDTO> findAllSensorByInvernadero(@PathVariable Long idInvernadero);

}
