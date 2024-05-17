package com.microservicio.alarma.persistencia;
import com.microservicio.alarma.entidades.Alarma;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmaRepositorio extends CrudRepository<Alarma, Long> {

}
