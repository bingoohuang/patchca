package com.github.bingoohuang.patchca.random;

import java.io.UnsupportedEncodingException;

public class ChineseUtils {
    public static boolean isChinese(char ch) {
        return ch >= '\u4e00' && ch <= '\u9fa5';
    }

    public static boolean containsChinese(String str) {
        for (int i = 0; i < str.length(); ++i) {
            if (isChinese(str.charAt(i))) return true;
        }
        return false;
    }

    public static boolean isChinese(String str) {
        for (int i = 0; i < str.length(); ++i) {
            if (!isChinese(str.charAt(i))) return false;
        }
        return true;
    }

    public static String levelOne() {
        return levelOneCharacters;
    }

    public static String levelTwo() {
        return generateCharacters(56, 87);
    }

    public static String generateCharacters(int from, int to) {
        StringBuilder sb = new StringBuilder();
        for (int i = from; i <= to; i++) {
            // looping through all characters in one row
            for (int j = 1; j <= 94; j++) {
                byte hi = (byte) (0xA0 + i);
                byte lo = (byte) (0xA0 + j);
                if (validGB(i, j)) {
                    try {
                        sb.append(new String(new byte[] { hi, lo }, "GBK"));
                    }
                    catch (UnsupportedEncodingException e) {
                        // Ignore
                    }
                }
            }
        }

        return sb.toString();
    }

    private static int b_out[] = { 201, 267, 279, 293, 484, 587, 625, 657, 734, 782, 827, 874, 901, 980, 5590 };
    private static int e_out[] = { 216, 268, 280, 294, 494, 594, 632, 694, 748, 794, 836, 894, 903, 994, 5594 };
    private static String levelOneCharacters;
    static {
        levelOneCharacters = generateCharacters(16, 55);
    }


    private static boolean validGB(int i, int j) {
        for (int l = 0; l < b_out.length; l++) {
            if (i * 100 + j >= b_out[l] && i * 100 + j <= e_out[l]) { return false; }
        }
        return true;
    }
}
