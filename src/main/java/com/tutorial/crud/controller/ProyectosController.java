package com.tutorial.crud.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.ProyectosDto;
import com.tutorial.crud.entity.Proyectos;
import com.tutorial.crud.service.ProyectosService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pro")
@CrossOrigin(origins = "*")
public class ProyectosController {
    @Autowired
    ProyectosService proyectosService;

    @GetMapping("/list")
    public ResponseEntity<List<Proyectos>> list(){
        List<Proyectos> list = proyectosService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/nuevo")
    public ResponseEntity<?> create(@RequestBody ProyectosDto proyectosDto){
        if(StringUtils.isBlank(proyectosDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank((CharSequence) proyectosDto.getEnlace()))
            return new ResponseEntity(new Mensaje("Debe ingresar un enlace"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank((CharSequence) proyectosDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("Debe ingresar una descripción"), HttpStatus.BAD_REQUEST);

        Proyectos proyectos = new Proyectos(proyectosDto.getNombre(), proyectosDto.getEnlace(), proyectosDto.getDescripcion() );
        proyectosService.save(proyectos);
        return new ResponseEntity(new Mensaje("Proyecto guardado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ProyectosDto proyectosDto){
        if(!proyectosService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(proyectosService.existsByNombre(proyectosDto.getNombre()) && proyectosService.getByNombre(proyectosDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(proyectosDto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre del proyecto es obligatorio"), HttpStatus.BAD_REQUEST);


        Proyectos proyectos = proyectosService.getOne(id).get();
        proyectos.setNombre(proyectosDto.getNombre());
        proyectos.setEnlace(proyectosDto.getEnlace());
        proyectos.setDescripcion(proyectosDto.getDescripcion());

        proyectosService.save(proyectos);
        return new ResponseEntity(new Mensaje("proyecto actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!proyectosService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        proyectosService.delete(id);
        return new ResponseEntity(new Mensaje("proyecto eliminado"), HttpStatus.OK);
    }
}
