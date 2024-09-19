package com.eldar.challenge.Utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static boolean validateFormatDate(String validate,String message){
        if(validate == null || validate == "")
            return true;

        String regex = "\\d{2}-\\d{2}-\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(validate);
        if (!matcher.matches()) {
            System.out.println(message);
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

    public static boolean isExpired(String validate,String message){

        if(toLocalDate(validate).isBefore(LocalDate.now())) {
            System.out.println(message);
            return true;
        }
        return false;
    }

    public static boolean validateEmail(String validate){

        if(validate == null || validate == "") {
            return false;
        }

        String regex = "^[\\w-\\.]+@[\\w-]+\\.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(validate.toLowerCase());
        if (!matcher.matches()) {
            return false;
        }
        return true;
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
}
