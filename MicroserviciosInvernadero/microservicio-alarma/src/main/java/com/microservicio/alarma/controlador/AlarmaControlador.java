package com.microservicio.alarma.controlador;

import com.microservicio.alarma.entidades.Alarma;
import com.microservicio.alarma.servicio.IAlarmaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alarma")
public class AlarmaControlador {

    @Autowired
    private IAlarmaServicio alarmaServicio;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void guardarAlarma(@RequestBody Alarma alarma){
        alarmaServicio.save(alarma);
    }


    @GetMapping("/all")
    public ResponseEntity<?> findAllAlarma(){
        return ResponseEntity.ok(alarmaServicio.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(alarmaServicio.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAlarma(@PathVariable Long idAlarma){
        alarmaServicio.delete(idAlarma);
    }



}
