package com.tutorial.crud.service;

import com.tutorial.crud.entity.Educacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EducacionService {

    @Autowired
    com.tutorial.crud.repository.EducacionRepository educacionRepository;

    public List<Educacion> list() {
        return educacionRepository.findAll();
    }

    public Optional<Educacion> getOne(int id) {
        return educacionRepository.findById(id);
    }

    public Optional<Educacion> getByInstitucion(String institucion) {
        return educacionRepository.findByInstitucion(institucion);
    }

    public void save(Educacion educacion) {
        educacionRepository.save(educacion);
    }

    public void delete(int id) {
        educacionRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return educacionRepository.existsById(id);
    }

    public boolean existsByInstitucion(String institucion) {
        return educacionRepository.existsByInstitucion(institucion);
    }

}
