package com.eldar.challenge.Repository;

import com.eldar.challenge.Entities.Operacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperacionRespository extends JpaRepository<Operacion,Long> {
}
