package com.microservicio.alarma.servicio;

import com.microservicio.alarma.entidades.Alarma;

import java.util.List;

public interface IAlarmaServicio {
    List<Alarma> findAll();

    Alarma findById(Long id);

    void save(Alarma alarma);

    void delete(Long idAlarma);

}
