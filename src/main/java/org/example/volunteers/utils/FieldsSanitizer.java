package org.example.volunteers.utils;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldsSanitizer {

    public String clearName(String name) {
        return name.replaceAll("[^\\p{L} ,.'-]", "");
    }

    public String clearPhone(String phone) {
        String result = phone.replaceAll("[^\\d.]|\\.", "");

        result = result.substring(Math.max(result.length() - 9, 0));

        return result.length() == 9 ? "0" + result : "";
    }

    public String clearEmail(String email) {
        if (email == null || email.isEmpty()) {
            return "";
        }

        String normalizedEmail = Normalizer.normalize(email.toLowerCase(), Normalizer.Form.NFD);
        String result = normalizedEmail.replaceAll("\\p{M}", "");

        String mailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";

        Matcher mailMatcher = Pattern.compile(mailRegex).matcher(result);
        if (mailMatcher.find()) {
            return mailMatcher.group();
        }

        return "";
    }
}
