package com.gong.core.utils;

import java.util.Random;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/9/20 17:16
 */
public class RandomCodeUtils {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 8;

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            String recommendationCode = generateRecommendationCode(1);
            System.out.println("推荐码: " + recommendationCode);
        }
    }

    public static String generateRecommendationCode() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        while (sb.length() < CODE_LENGTH) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public static String generateRecommendationCode(int codeLength) {
        if(codeLength < 6){
            codeLength = 6;
        }
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        while (sb.length() < codeLength) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}