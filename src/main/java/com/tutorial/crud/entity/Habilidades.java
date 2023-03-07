package com.tutorial.crud.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Habilidades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String especialidad;
    private String puntaje;
    private String segmento;


    public Habilidades() {

    }

    public Habilidades (String especialidad, String puntaje, String segmento) {

        this.especialidad=especialidad;
        this.puntaje=puntaje;
        this.segmento=segmento;

    }


}
