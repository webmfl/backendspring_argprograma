package com.tutorial.crud.controller;

import com.tutorial.crud.dto.ExperienciaDto;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.entity.Experiencia;
import com.tutorial.crud.service.ExperienciaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exp")
@CrossOrigin(origins = "*")
public class ExperienciaController {
    @Autowired
    ExperienciaService experienciaService;

    @GetMapping("/list")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = experienciaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/nuevo")
    public ResponseEntity<?> create(@RequestBody ExperienciaDto experienciaDto){
        if(StringUtils.isBlank(experienciaDto.getEmpresa()))
            return new ResponseEntity(new Mensaje("la empresa es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienciaDto.getDetalle()))
            return new ResponseEntity(new Mensaje("Debe comentar alguna experiencia"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienciaDto.getPeriodo()))
            return new ResponseEntity(new Mensaje("Debe ingresar un periodo de tiempo"), HttpStatus.BAD_REQUEST);

        Experiencia experiencia = new Experiencia(experienciaDto.getEmpresa(), experienciaDto.getPeriodo(), experienciaDto.getDetalle() );
        experienciaService.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia guardada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ExperienciaDto experienciaDto){
        if(!experienciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(experienciaService.existsByEmpresa(experienciaDto.getEmpresa()) && experienciaService.getByEmpresa(experienciaDto.getEmpresa()).get().getId() != id)
            return new ResponseEntity(new Mensaje("esa empresa ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienciaDto.getEmpresa()))
            return new ResponseEntity(new Mensaje("la empresa es obligatoria"), HttpStatus.BAD_REQUEST);


        Experiencia experiencia = experienciaService.getOne(id).get();
        experiencia.setEmpresa(experienciaDto.getEmpresa());
        experiencia.setDetalle(experienciaDto.getDetalle());
        experiencia.setPeriodo(experienciaDto.getPeriodo());
        experienciaService.save(experiencia);
        return new ResponseEntity(new Mensaje("experiencia actualizada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!experienciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        experienciaService.delete(id);
        return new ResponseEntity(new Mensaje("experiencia eliminada"), HttpStatus.OK);
    }
}
