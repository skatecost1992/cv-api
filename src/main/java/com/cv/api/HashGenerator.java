package com.cv.api;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class HashGenerator {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java HashGenerator <valor>");
            System.out.println("Ejemplo: java HashGenerator mi-nuevo-secreto");
            return;
        }
        String hash = sha256(args[0]);
        System.out.println("Valor: " + args[0]);
        System.out.println("SHA-256: " + hash);
    }

    public static String sha256(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(value.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }
}
