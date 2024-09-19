package com.eldar.challenge.Repository;

import com.eldar.challenge.Entities.Persona;
import org.junit.Ignore;
import org.junit.experimental.categories.Category;
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
public class PersonaTest {

    @Autowired
    private PersonaRepository personaRepository;

    @BeforeEach
    public void setUp(){

        Random random = new Random();

        /**
         * crea 20 objetos aleatorios
         * */
        IntStream.range(0,20).forEach(i->{

            Persona persona = null;
            try {

                persona = new Persona(RandomChar(),RandomChar(), random.nextInt(10000000,99999999), toLocalDate("29-09-2021"),RandomChar()+"@gmail.com");
                this.personaRepository.save(persona);

            } catch (Exception e) {
                throw new RuntimeException(e);

            }

        });
    }

    private String RandomChar(){
        Random random = new Random();

        String word = "";
        String[] letters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N"
                ,"O","P","Q","R","S","T","U","V","W","X","Z","a","b","c","d","e","f","g",
                "h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","z"};


        word = IntStream.range(0,random.nextInt(5,15))
                .mapToObj(i->letters[random.nextInt(letters.length)])
                .collect(Collectors.joining());

        return word;
    }

    @Test
    public void save() throws Exception {

        Persona persona = new Persona("test","test",10000000,toLocalDate("29-09-2021"),"admin@gmail.com");


        Persona dbResponse = this.personaRepository.save(persona);

        assertNotNull(dbResponse);
        assertEquals(dbResponse,persona);
        assertNotNull(dbResponse.getDni());
    }



    private LocalDate toLocalDate(String fecha){
        String [] datosFecha = fecha.split("-") ;


        return LocalDate.of(Integer.parseInt(datosFecha[2].trim()), //AÑO
                Integer.parseInt(datosFecha[1].trim()), // FECHA
                Integer.parseInt(datosFecha[0].trim())); // MES
    }

    @Test
    public void findAll(){
        List<Persona> personaList =  this.personaRepository.findAll();

        assertNotNull(personaList);
        assertTrue(personaList.isEmpty() == false);
        assertTrue(personaList.stream().count() >= 20);
        assertNotNull(personaList.get(2));
    }

    @Test
    public void findById(){
        List<Persona> personaList = this.personaRepository.findAll();
        Persona personaDB =  this.personaRepository.findById(personaList.get(2).getId()).get();


        assertNotNull(personaDB);
        assertEquals(personaDB.getId(),personaList.get(2).getId());
        assertEquals(personaDB.getDni(),personaList.get(2).getDni());
        assertEquals(personaDB,personaList.get(2));
    }

}
