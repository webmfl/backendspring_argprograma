package com.tutorial.crud.service;

import com.tutorial.crud.entity.Experiencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExperienciaService {

    @Autowired
    com.tutorial.crud.repository.ExperienciaRepository experienciaRepository;

    public List<Experiencia> list() {
        return experienciaRepository.findAll();
    }

    public Optional<Experiencia> getOne(int id) {
        return experienciaRepository.findById(id);
    }

    public Optional<Experiencia> getByEmpresa(String empresa) {
        return experienciaRepository.findByEmpresa(empresa);
    }

    public void save(Experiencia experiencia) {
        experienciaRepository.save(experiencia);
    }

    public void delete(int id) {
        experienciaRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return experienciaRepository.existsById(id);
    }

    public boolean existsByEmpresa(String empresa) {
        return experienciaRepository.existsByEmpresa(empresa);
    }


}


