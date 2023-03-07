package com.tutorial.crud.controller;

import com.tutorial.crud.dto.HabilidadesDto;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.entity.Habilidades;
import com.tutorial.crud.service.HabilidadesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hab")
@CrossOrigin(origins = "*")
public class HabilidadesController {
    @Autowired
    HabilidadesService habilidadesService;

    @GetMapping("/list")
    public ResponseEntity<List<Habilidades>> list(){
        List<Habilidades> list = habilidadesService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/nuevo")
    public ResponseEntity<?> create(@RequestBody HabilidadesDto habilidadesDto){
        if(StringUtils.isBlank(habilidadesDto.getEspecialidad()))
            return new ResponseEntity(new Mensaje("la especialidad es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank((CharSequence) habilidadesDto.getPuntaje()))
            return new ResponseEntity(new Mensaje("Debe ingresar algun puntaje de habilidad"), HttpStatus.BAD_REQUEST);

        Habilidades habilidades = new Habilidades(habilidadesDto.getEspecialidad(), habilidadesDto.getPuntaje(), habilidadesDto. getSegmento() );
        habilidadesService.save(habilidades);
        return new ResponseEntity(new Mensaje("Habilidad guardada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody HabilidadesDto habilidadesDto){
        if(!habilidadesService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(habilidadesService.existsByEspecialidad(habilidadesDto.getEspecialidad()) && habilidadesService.getByEspecialidad(habilidadesDto.getEspecialidad()).get().getId() != id)
            return new ResponseEntity(new Mensaje("esa especialidad ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(habilidadesDto.getEspecialidad()))
            return new ResponseEntity(new Mensaje("la especialidad es obligatoria"), HttpStatus.BAD_REQUEST);


        Habilidades habilidades = habilidadesService.getOne(id).get();
        habilidades.setEspecialidad(habilidadesDto.getEspecialidad());
        habilidades.setPuntaje(habilidadesDto.getPuntaje());
        habilidades.setSegmento(habilidadesDto.getSegmento());

        habilidadesService.save(habilidades);
        return new ResponseEntity(new Mensaje("habilidad actualizada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!habilidadesService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        habilidadesService.delete(id);
        return new ResponseEntity(new Mensaje("especialidad eliminada"), HttpStatus.OK);
    }
}
