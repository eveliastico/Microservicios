package com.microservicio.invernadero.servicio;

import com.microservicio.invernadero.client.SensorCliente;
import com.microservicio.invernadero.dto.SensorDTO;
import com.microservicio.invernadero.entidades.Invernadero;
import com.microservicio.invernadero.http.response.SensorByInvernaderoResponse;
import com.microservicio.invernadero.persistencia.IInvernaderoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvernaderoServicioImpl implements IInvernaderoServicio{

    @Autowired
    private IInvernaderoRepositorio invernaderoRepositorio;

    //Se crea una instancia del cliente para llamar a la api search-by-invernadero.
    @Autowired
    private SensorCliente sensorCliente;

    @Override
    public List<Invernadero> findAll() {
        return (List<Invernadero>) invernaderoRepositorio.findAll();
    }

    @Override
    public Invernadero findById(Long id) {
        return invernaderoRepositorio.findById(id).orElseThrow();
    }

    @Override
    public void save(Invernadero invernadero) {
        invernaderoRepositorio.save(invernadero);
    }

    @Override
    public SensorByInvernaderoResponse findSensorByIdInvernadero(Long idInvernadero) {

        //Primero se consulta el invernadero, si no se encuentra se crea un invernadero sin parametros.
        Invernadero invernadero = invernaderoRepositorio.findById(idInvernadero).orElse(new Invernadero());

        //Se obtienen los sensores del invernadero.
        List<SensorDTO> listaSensorDTO = sensorCliente.findAllSensorByInvernadero(idInvernadero);

        //Se crea la respuesta con la clase de respuesta.
        return SensorByInvernaderoResponse.builder()
                .nombreInvernadero(invernadero.getNombre())
                .listaSensorDTO(listaSensorDTO)
                .build();
    }
}
