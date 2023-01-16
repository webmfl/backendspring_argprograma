package com.tutorial.crud.dto;

import javax.validation.constraints.NotBlank;

public class EducacionDto {
    @NotBlank
    private String institucion;

    @NotBlank
    private String titulo;

    @NotBlank
    private String detalle;

    public EducacionDto() {

    }

    public EducacionDto(@NotBlank String institucion, @NotBlank String titulo, @NotBlank String detalle) {
        this.detalle= detalle;
        this.institucion= institucion;
        this.titulo= titulo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
