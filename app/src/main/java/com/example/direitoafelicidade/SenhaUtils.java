package com.example.direitoafelicidade;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SenhaUtils {

    public static String[] gerarSaltKeyECriptografarSenhaComSalting(String senha){
        String salt = gerarSalt();
        return new String[]{criptografarSenhaComSalt(senha,salt), salt};
    }

    public static String criptografarSenhaComSalt(String senha, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest((senha+salt).getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao criptografar a senha", e);
        }
    }

    private static String gerarSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16]; // 16 bytes = 128 bits
        random.nextBytes(salt);
        StringBuilder hexString = new StringBuilder();
        for (byte b : salt) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }

}
