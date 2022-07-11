package org.example.volunteers.utils;

import java.text.Normalizer;

public class FieldsSanitizer {

    public static String clearName(String name) {
        return name.replaceAll("[^\\p{L} ,.'-]", "");
    }

    public static String clearPhone(String phone) {
        String result = phone.replaceAll("[^\\d.]|\\.", "");

        result = result.substring(Math.max(result.length() - 9, 0));

        return result.length() == 9 ? "0" + result : "";
    }
}
