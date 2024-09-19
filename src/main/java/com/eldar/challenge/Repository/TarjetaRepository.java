package com.eldar.challenge.Repository;

import com.eldar.challenge.Entities.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta,Long> {

    @Query(nativeQuery = true, value = "select * from tarjeta where card_holder_id = :ownerid")
    public List<Tarjeta> findByCardHolderId(@Param("ownerid") Long ownerid);

    @Query(value = "select * from tarjeta where numero = :numero", nativeQuery = true)
    public Optional<Tarjeta> findByNumero(@Param("numero")Long numero);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM tarjeta WHERE numero = :numero")
    public void deleteByNumero(@Param("numero")Long numero);

}
