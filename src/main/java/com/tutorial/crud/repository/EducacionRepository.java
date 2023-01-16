package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Educacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducacionRepository extends JpaRepository<Educacion, Integer> {
    Optional<Educacion> findByInstitucion(String institucion);
    boolean existsByInstitucion(String institucion);
}
