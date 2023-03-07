package com.tutorial.crud.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProyectosDto {

    @NotBlank
    private String nombre;

    @NotBlank
    private String enlace;

    @NotBlank
    private String descripcion;

    public ProyectosDto() {

    }

    public ProyectosDto(@NotBlank String nombre, @NotBlank String enlace, @NotBlank String descripcion) {
        this.nombre=nombre;
        this.enlace=enlace;
        this.descripcion=descripcion;

    }

}

