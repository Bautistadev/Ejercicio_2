package com.eldar.challenge.Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncriptyClass {

    public static String encriptar(String CVV) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, crearClave("1234567812345678"));
        byte[] encrypted = cipher.doFinal(CVV.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }






    public static String desencriptar(String encriptadoCVV) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, crearClave("1234567812345678"));

        byte[] datosDescifrados = cipher.doFinal(Base64.getDecoder().decode(encriptadoCVV));
        return new String(datosDescifrados);
    }





    private static SecretKeySpec crearClave(String claveSecreta) throws Exception {
        byte[] key = claveSecreta.getBytes("UTF-8");
        return new SecretKeySpec(key, "AES");
    }




    public static String formatearNumeroTarjeta(String numero) {
        // Verifica que el número tenga 16 cifras
        if (numero == null || numero.length() != 16 || !numero.matches("\\d+")) {
            throw new IllegalArgumentException("El número de tarjeta debe tener 16 cifras.");
        }

        // Usa expresiones regulares para insertar los guiones en la posición correcta
        return numero.replaceAll("(\\d{4})(\\d{4})(\\d{4})(\\d{4})", "$1-$2-$3-$4");
    }
}
