package com.microservicio.invernadero.persistencia;

import com.microservicio.invernadero.entidades.Invernadero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvernaderoRepositorio extends CrudRepository<Invernadero, Long> {

}
