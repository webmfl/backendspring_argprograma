package com.tutorial.crud.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class HabilidadesDto {
    @NotBlank
    private String especialidad;

    @NotBlank
    private String puntaje;

    @NotBlank
    private String segmento;

    public HabilidadesDto() {

    }

    public HabilidadesDto(@NotBlank String especialidad, @NotBlank String puntaje, @NotBlank String segmento) {
        this.especialidad= especialidad;
        this.puntaje= puntaje;
        this.segmento= segmento;

    }


}
