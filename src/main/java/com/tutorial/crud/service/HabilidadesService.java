package com.tutorial.crud.service;

import com.tutorial.crud.entity.Habilidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HabilidadesService {
    @Autowired
    com.tutorial.crud.repository.HabilidadesRepository habilidadesRepository;

    public List<Habilidades> list() {
        return habilidadesRepository.findAll();
    }
    public Optional<Habilidades> getOne(int id) {
        return habilidadesRepository.findById(id);
    }
    public Optional<Habilidades> getByEspecialidad(String especialidad) {
        return habilidadesRepository.findByEspecialidad(especialidad);
    }
    public void save(Habilidades habilidades) {
        habilidadesRepository.save(habilidades);
    }
    public void delete(int id) {
        habilidadesRepository.deleteById(id);
    }
    public boolean existsById(int id) {
        return habilidadesRepository.existsById(id);
    }
    public boolean existsByEspecialidad(String especialidad) {
        return habilidadesRepository.existsByEspecialidad(especialidad);
    }

}
