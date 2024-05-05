package com.microservicio.invernadero.controlador;

import com.microservicio.invernadero.entidades.Sensor;
import com.microservicio.invernadero.servicio.ISensorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensor")
public class sensorControlador {

    @Autowired
    private ISensorServicio sensorServicio;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void guardarSensor(@RequestBody Sensor sensor){
        sensorServicio.save(sensor);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllSensor(){
        return ResponseEntity.ok(sensorServicio.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(sensorServicio.findById(id));
    }



}
