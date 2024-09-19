package com.eldar.challenge.Repository;

import com.eldar.challenge.Entities.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle,Long> {
}
