package com.lsp.baas.Util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class CuidGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int RANDOM_LENGTH = 12;
    private static final String PREFIX = "cus_";
    private final SecureRandom secureRandom = new SecureRandom();

    public String generateCuid(){
        StringBuilder sb = new StringBuilder(PREFIX);
        for (int i = 0; i <RANDOM_LENGTH; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
}
