package com.eldar.challenge.Utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class ValidateClass {
    public static boolean validateText(String validate){

        if(validate == null || validate == "" || !validate.matches("[a-zA-Z]+"))
            return false;

        return true;
    }

    public static boolean validateMinNumber(Object validate,Integer limit){
        if(validate == null || String.valueOf(validate).length() < limit)
            return false;

        return true;
    }
    public static boolean validateMaxNumber(Object validate,Integer limit){
        if(validate == null || String.valueOf(validate).length() > limit) {
            return false;
        }
        return true;
    }

    public static boolean validateFormatDate(String validate){
        if(validate == null || validate == "")
            return true;

        String regex = "\\d{2}-\\d{2}-\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(validate);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    //VALIDAMOS EDAD
    public static boolean validBirthDate(LocalDate validate){

        if(Period.between(validate, LocalDate.now()).getYears() < 18)
            return false;

        return true;
    }

    public static boolean isExpired(String validate){

        if(toLocalDate(validate).isBefore(LocalDate.now())) {;
            return true;
        }
        return false;
    }

    public static boolean validateEmail(String validate){

        if(validate == null || validate == "") {
            return false;
        }
        // Verificar que contenga un "@" y un "." después del "@"
        int posicionArroba = validate.indexOf("@");
        int posicionPunto = validate.indexOf(".", posicionArroba);

        // Verificar que contenga ".com" en cualquier parte
        boolean contieneCom = validate.contains(".com");

        // El email es válido si contiene "@" no al inicio, un "." después del "@" y ".com" en cualquier lugar
        return posicionArroba > 0 && posicionPunto > posicionArroba && contieneCom;

    }

    public static LocalDate toLocalDate(String fecha){
        String [] datosFecha = fecha.split("-") ;

        return LocalDate.of(Integer.parseInt(datosFecha[2].trim()), //AÑO
                Integer.parseInt(datosFecha[1].trim()), // FECHA
                Integer.parseInt(datosFecha[0].trim())); // MES
    }

    public static String formatearNumeroTarjeta(String numero) {
        // Verifica que el número tenga 16 cifras
        if (numero == null || numero.length() != 16 || !numero.matches("\\d+")) {
            throw new IllegalArgumentException("El número de tarjeta debe tener 16 cifras.");
        }

        // Usa expresiones regulares para insertar los guiones en la posición correcta
        return numero.replaceAll("(\\d{4})(\\d{4})(\\d{4})(\\d{4})", "$1-$2-$3-$4");
    }


    public static Long generarNumeroDe16Cifras() {
        Random random = new Random();

        // Generar un número aleatorio de 16 cifras
        long numero = 1000000000000000L + (long)(random.nextDouble() * 9000000000000000L);

        return numero;
    }




    public static Integer generarNumeroDeCVV() {
        Random random = new Random();

        // Generar un número aleatorio de 3 cifras
        return 100 + random.nextInt(900);
    }


    public static String localDateToString(LocalDate date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }


    public static LocalDate crearFechaVencimiento(LocalDate fechaInicial, int meses) {
        // Suma los meses a la fecha inicial
        return fechaInicial.plusMonths(meses);
    }

   /* public static boolean nombreTitularValida(String cadena) {
        if (cadena == null || cadena.isEmpty()) {
            return false;
        }

        // Usamos IntStream para recorrer la cadena y verificar si todos los caracteres son válidos
        return cadena.chars()
                .allMatch(c -> Character.isLetter(c) || c == ' ');
    }*/

    public static boolean nombreTitularValida(String cadena) {
        if (cadena == null || cadena.length() < 2) {
            return false;
        }

        // Verificamos que el primer carácter sea una letra
        if (!Character.isLetter(cadena.charAt(0))) {
            return false;
        }

        // Creamos un Stream a partir del índice 1 (después de la primera letra)
        return IntStream.range(1, cadena.length())
                .anyMatch(i -> cadena.charAt(i) == ' ') && // Al menos un espacio
                cadena.chars().skip(1) // Saltamos el primer carácter
                        .allMatch(c -> Character.isLetter(c) || c == ' '); // Todos los demás son letras o espacios
    }
    public static boolean sinEspacios(String cadena) {
        if (cadena == null || cadena.isEmpty()) {
            return false;
        }

        System.out.println(cadena.chars()
                .noneMatch(c -> c == ' ') && cadena.chars()
                .allMatch(Character::isLetter));

        // Verificamos que no haya espacios y que todos los caracteres sean letras
        return cadena.chars()
                .noneMatch(c -> c == ' ') && cadena.chars()
                .allMatch(Character::isLetter);
    }
}
