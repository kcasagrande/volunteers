package utils;

import java.text.Normalizer;

public class stringUtils {

    public static String stripAccents(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        } else {
            s = Normalizer.normalize(s, Normalizer.Form.NFD);
            s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
            return s;
        }
    }
}
