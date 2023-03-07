package com.tutorial.crud.repository;


import com.tutorial.crud.entity.Habilidades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabilidadesRepository extends JpaRepository<Habilidades, Integer> {

    Optional<Habilidades> findByEspecialidad(String especialidad);
    boolean existsByEspecialidad(String especialidad);

}
