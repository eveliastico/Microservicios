package com.microservicio.invernadero.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorDTO {

    private Long invernaderoId;
    private float humedad;
    private float humedadLimite;
    private float temperatura;
    private float temperaturaLimite;
    private String fechaHora;

}

