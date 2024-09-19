package com.eldar.challenge.Repository;

import com.eldar.challenge.Entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Long> {
    Optional<Persona> findByDni(Integer dni);

    Boolean existsByDni(Integer dni);
}
