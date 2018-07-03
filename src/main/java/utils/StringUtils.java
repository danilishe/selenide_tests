package utils;

import java.util.Random;

public class StringUtils {
    public static final String characters = "abcdefghijklmnopqrstuvwxyz";

    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        new Random().ints(0, characters.length())
                .limit(length)
                .forEach(i -> sb.append(characters.charAt(i)));
        return sb.toString();
    }
}
