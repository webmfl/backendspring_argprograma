package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Integer> {
    Optional<Experiencia> findByEmpresa(String empresa);
    boolean existsByEmpresa(String empresa);
}
