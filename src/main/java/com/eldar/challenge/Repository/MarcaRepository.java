package com.eldar.challenge.Repository;

import com.eldar.challenge.Entities.Abstract.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca,Long> {
}
