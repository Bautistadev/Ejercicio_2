package com.eldar.challenge.Repository;

import com.eldar.challenge.Entities.Operacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface OperacionRespository extends JpaRepository<Operacion,Long> {
    @Query(nativeQuery = true, value = "SELECT fecha FROM operacion INNER JOIN tarjeta on operacion.tarjeta_id = tarjeta.id INNER JOIN marca on tarjeta.marca_id = marca.id INNER JOIN compra ON operacion.compra_id = compra.id WHERE marca.nombre = :marca AND compra.monto = :monto")
    public List<Timestamp> getOperationDateByMontoAndMarca(@Param("monto")BigDecimal monto, @Param("marca") String marca);
}
