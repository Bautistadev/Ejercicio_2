package com.eldar.challenge.Entities.Abstract;

import com.eldar.challenge.Entities.Interfaces.Tasa;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@Entity
@Table(name="Marca")
public abstract class Marca implements Tasa {
    private String nombre;

}
