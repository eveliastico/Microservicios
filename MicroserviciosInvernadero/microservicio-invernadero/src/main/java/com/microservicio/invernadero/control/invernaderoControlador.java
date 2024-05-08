package com.microservicio.invernadero.control;

import com.microservicio.invernadero.entidades.Invernadero;
import com.microservicio.invernadero.servicio.IInvernaderoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invernadero")
public class invernaderoControlador {

    @Autowired
    private IInvernaderoServicio invernaderoServicio;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void guardarSensor(@RequestBody Invernadero invernadero){
        invernaderoServicio.save(invernadero);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllSensor(){
        return ResponseEntity.ok(invernaderoServicio.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(invernaderoServicio.findById(id));
    }

    @GetMapping("/search-sensores/{idInvernadero}")
    public ResponseEntity<?> findSensoresByIdInvernadero(@PathVariable Long idInvernadero){
        return ResponseEntity.ok(invernaderoServicio.findSensorByIdInvernadero(idInvernadero));
    }
}
