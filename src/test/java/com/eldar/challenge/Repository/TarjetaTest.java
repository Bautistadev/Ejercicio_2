package com.eldar.challenge.Repository;

import com.eldar.challenge.Entities.*;
import com.eldar.challenge.Entities.Abstract.Marca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class TarjetaTest {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @BeforeEach
    public void setUp() {

        Random random = new Random();

        /**
         * crea 20 objetos aleatorios
         *
         * */
        this.marcaRepository.save(new Visa());
        this.marcaRepository.save(new Nara());
        this.marcaRepository.save(new Amex());

        IntStream.range(0, 20).forEach(i -> {

            Persona persona;
            Tarjeta tarjeta = null;
            try {
                tarjeta = new Tarjeta();
                persona = new Persona(RandomChar(5, 15), RandomChar(5, 15), random.nextInt(10000000, 99999999), toLocalDate("29-09-2021"), RandomChar(5, 10) + "@gmail.com");
                Persona personaDB = this.personaRepository.save(persona);

                tarjeta.setNumero(new Random().nextLong(1000000000000000L, 9999999999999999L));
                tarjeta.setFecha_vencimiento(toLocalDate("27-09-2032"));
                tarjeta.setCVV(String.valueOf(new Random().nextInt(100,999)));
                tarjeta.setNombre_completo_titular(persona.getApellido().concat(" " + persona.getNombre()));
                tarjeta.setMarca(RandomMarca());
                tarjeta.setCashHolder(persona);

                this.tarjetaRepository.save(tarjeta);

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
    public void save() throws Exception {

        Persona persona = this.personaRepository.findById(2L).get();
        Tarjeta tarjeta = new Tarjeta();
        
        tarjeta.setNumero(new Random().nextLong(1000000000000000L, 9999999999999999L));
        tarjeta.setFecha_vencimiento(toLocalDate("27-09-2032"));
        tarjeta.setCVV(String.valueOf(new Random().nextInt(100,999)));
        tarjeta.setNombre_completo_titular(persona.getApellido().concat(" " + persona.getNombre()));
        tarjeta.setMarca(RandomMarca());
        tarjeta.setCashHolder(persona);
        

        Tarjeta tarjetadb = this.tarjetaRepository.save(tarjeta);

        assertNotNull(tarjetadb);
        assertEquals(tarjetadb,tarjeta);
        assertNotNull(tarjetadb.getCVV());
    }

    @Test
    public void findAll(){
        List<Tarjeta> tarjetasList =  this.tarjetaRepository.findAll();

        assertNotNull(tarjetasList);
        assertTrue(tarjetasList.isEmpty() == false);
        assertTrue(tarjetasList.stream().count() >= 20);
        assertNotNull(tarjetasList.get(2));
    }

    @Test
    public void findById(){
        List<Tarjeta> tarjetasList =  this.tarjetaRepository.findAll();
        Tarjeta tarjetaDB = this.tarjetaRepository.findById(tarjetasList.get(2).getId()).get();


        assertNotNull(tarjetaDB);
        assertEquals(tarjetaDB.getId(),tarjetasList.get(2).getId());
        assertEquals(tarjetaDB.getCVV(),tarjetasList.get(2).getCVV());
        assertEquals(tarjetaDB,tarjetasList.get(2));
    }

    @Test
    public void removeById(){
        Tarjeta tarjeta = this.tarjetaRepository.findById(2L).get();

        assertNotNull(tarjeta);

        this.tarjetaRepository.delete(tarjeta);

        assertFalse(this.tarjetaRepository.findById(2L).isPresent());
    }

}
