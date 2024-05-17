package com.microservicio.alarma.servicio;

import com.microservicio.alarma.entidades.Alarma;
import com.microservicio.alarma.persistencia.AlarmaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmaServicioImpl implements IAlarmaServicio{

    @Autowired
    private AlarmaRepositorio alarmaRepositorio;

    @Override
    public List<Alarma> findAll() {
        return (List<Alarma>) alarmaRepositorio.findAll();
    }

    @Override
    public Alarma findById(Long id) {
        return alarmaRepositorio.findById(id).orElseThrow();
    }

    @Override
    public void save(Alarma alarma) {
        alarmaRepositorio.save(alarma);
    }

    @Override
    public void delete(Long idAlarma) {
        alarmaRepositorio.deleteById(idAlarma);
    }


}
