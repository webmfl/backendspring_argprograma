package com.tutorial.crud.dto;


import javax.validation.constraints.NotBlank;

public class ExperienciaDto {

    @NotBlank
    private String detalle;

    @NotBlank
    private String empresa;

    @NotBlank
    private String periodo;
    
    public ExperienciaDto() {

    }

    public ExperienciaDto(@NotBlank String detalle, @NotBlank String empresa, @NotBlank String periodo) {
        this.detalle= detalle;
        this.empresa= empresa;
        this.periodo= periodo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
