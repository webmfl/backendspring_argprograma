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
public class Proyectos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nombre;
    private String enlace;
    private String descripcion;

    public Proyectos() {

    }

    public Proyectos (String nombre, String enlace, String descripcion) {

        this.nombre=nombre;
        this.enlace=enlace;
        this.descripcion=descripcion;

    }


}
