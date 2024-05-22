package com.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncryption {

    private static final String HASH_ALGORITHM = "SHA-256";

    public static String hashPassword(String password) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.reset();
            digest.update(salt);
            byte[] hashedBytes = digest.digest(password.getBytes());

            String saltBase64 = Base64.getEncoder().encodeToString(salt);
            String hashBase64 = Base64.getEncoder().encodeToString(hashedBytes);
            return saltBase64 + ":" + hashBase64;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verifyPassword(String password, String hashedPassword, byte[] salt) {
        byte[] hashedBytes = Base64.getDecoder().decode(hashedPassword);

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.reset();
            digest.update(salt);
            byte[] checkHash = digest.digest(password.getBytes());
            return MessageDigest.isEqual(hashedBytes, checkHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }
}
