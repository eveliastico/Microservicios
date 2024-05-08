package com.microservicio.invernadero.http.response;
/*
* Clase que va a mapear la respuesta que se le dara a la persona que consula el microservicio.
* */

import com.microservicio.invernadero.dto.SensorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SensorByInvernaderoResponse {

    private String nombreInvernadero;
    private List<SensorDTO> listaSensorDTO;

}
