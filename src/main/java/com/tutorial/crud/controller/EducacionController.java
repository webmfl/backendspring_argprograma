package com.tutorial.crud.controller;

import com.tutorial.crud.dto.EducacionDto;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.entity.Educacion;
import com.tutorial.crud.service.EducacionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edu")
@CrossOrigin(origins = "*")
public class EducacionController {

    @Autowired
    EducacionService educacionService;

    @GetMapping("/list")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = educacionService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/nuevo")
    public ResponseEntity<?> create(@RequestBody EducacionDto educacionDto){
        if(StringUtils.isBlank(educacionDto.getInstitucion()))
            return new ResponseEntity(new Mensaje("la institucion es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educacionDto.getDetalle()))
            return new ResponseEntity(new Mensaje("Debe comentar detalles sobre su formación"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educacionDto.getTitulo()))
            return new ResponseEntity(new Mensaje("Debe ingresar una titulación"), HttpStatus.BAD_REQUEST);

        Educacion educacion = new Educacion(educacionDto.getInstitucion(), educacionDto.getTitulo(), educacionDto.getDetalle() );
        educacionService.save(educacion);
        return new ResponseEntity(new Mensaje("Educación guardada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody EducacionDto educacionDto){
        if(!educacionService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(educacionService.existsByInstitucion(educacionDto.getInstitucion()) && educacionService.getByInstitucion(educacionDto.getInstitucion()).get().getId() != id)
            return new ResponseEntity(new Mensaje("esa institucion ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educacionDto.getInstitucion()))
            return new ResponseEntity(new Mensaje("la empresa es obligatoria"), HttpStatus.BAD_REQUEST);


        Educacion educacion = educacionService.getOne(id).get();
        educacion.setInstitucion(educacionDto.getInstitucion());
        educacion.setDetalle(educacionDto.getDetalle());
        educacion.setTitulo(educacionDto.getTitulo());
        educacionService.save(educacion);
        return new ResponseEntity(new Mensaje("educación actualizada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!educacionService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        educacionService.delete(id);
        return new ResponseEntity(new Mensaje("experiencia eliminada"), HttpStatus.OK);
    }
}
