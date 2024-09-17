package com.eldar.challenge.Repository;

import com.eldar.challenge.Entities.*;
import com.eldar.challenge.Entities.Abstract.Marca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase
public class TarjetaTest {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @BeforeEach
    public void setUp() {

        Random random = new Random();

        /**
         * crea 20 objetos aleatorios
         * */
        IntStream.range(0, 20).forEach(i -> {

            Persona persona;
            Tarjeta tarjeta;
            try {

                persona = new Persona(RandomChar(5, 15), RandomChar(5, 15), random.nextInt(10000000, 99999999), toLocalDate("29-09-2021"), RandomChar(5, 10) + "@gmail.com");
                tarjeta = new Tarjeta(random.nextLong(1000000000000000L, 9999999999999999L), toLocalDate("27-09-2032"), persona.getApellido().concat(" " + persona.getNombre()),RandomMarca(),String.valueOf(random.nextInt(100,999)));

            } catch (Exception e) {
                throw new RuntimeException(e);

            }

        });
    }

    private String RandomChar(Integer inicio, Integer fin) {
        Random random = new Random();

        String word = "";
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"
                , "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Z", "a", "b", "c", "d", "e", "f", "g",
                "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "z"};


        word = IntStream.range(0, random.nextInt(inicio, fin))
                .mapToObj(i -> letters[random.nextInt(letters.length)])
                .collect(Collectors.joining());

        return word;
    }

    private LocalDate toLocalDate(String fecha) {
        String[] datosFecha = fecha.split("-");


        return LocalDate.of(Integer.parseInt(datosFecha[2].trim()), //AÑO
                Integer.parseInt(datosFecha[1].trim()), // FECHA
                Integer.parseInt(datosFecha[0].trim())); // MES

    }

    private Marca RandomMarca(){
        int rand =  new Random().nextInt(1,3);

        switch (rand){
            case 1: return new Visa();
            case 2: return new Amex();
            case 3: return new Nara();
            default: return null;
        }

    }

    @Test
    private void save() throws Exception {
        Persona persona = new Persona(RandomChar(5, 15), RandomChar(5, 15), new Random().nextInt(10000000, 99999999), toLocalDate("29-09-2021"), RandomChar(5, 10) + "@gmail.com");
        Tarjeta tarjeta = new Tarjeta(new Random().nextLong(1000000000000000L, 9999999999999999L), toLocalDate("27-09-2032"), persona.getApellido().concat(" " + persona.getNombre()),RandomMarca(),String.valueOf(new Random().nextInt(100,999)));

        Tarjeta tarjetadb = this.tarjetaRepository.save(tarjeta);
        assertNotNull(tarjetadb);
    }

    @Test
    private void findAll(){

    }

    @Test
    private void findById(){

    }

    private void removeById(){

    }

}
