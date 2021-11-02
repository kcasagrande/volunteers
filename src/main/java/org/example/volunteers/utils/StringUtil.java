package org.example.volunteers.utils;

import org.example.volunteers.model.PhoneNumberPattern;

import java.text.Normalizer;

public class StringUtil {

    public static String stripAccents(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        } else {
            s = Normalizer.normalize(s, Normalizer.Form.NFD);
            s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
            return s;
        }
    }

    /**
     * Formatted a given phone number
     *
     * @param phoneNumber phone number to be formated
     * @return String phone number
     */
    public String refactorPhoneNumberString(String phoneNumber) {
        //+33(0)0.75.55.99.79
        if (phoneNumber.matches(PhoneNumberPattern.pattern1.toString())) {
            return this.refactorPhoneNumberForSeparator(phoneNumber, "\\.");
        }

        //+33(0)0-75-55-55-20
        if (phoneNumber.matches(PhoneNumberPattern.pattern2.toString())) {
            return this.refactorPhoneNumberForSeparator(phoneNumber, "\\-");
        }

        //+33055587491
        if (phoneNumber.matches(PhoneNumberPattern.pattern3.toString())) {
            return phoneNumber.replace("+33", "0");
        }

        //+33(0)0 85 55 67 37
        if (phoneNumber.matches(PhoneNumberPattern.pattern4.toString())) {
            return this.refactorPhoneNumberForSeparator(phoneNumber, " ");
        }

        //+33(0)000555091
        if (phoneNumber.matches(PhoneNumberPattern.pattern5.toString())) {
            return phoneNumber.replace("+33(0)", "0");
        }

        //+330 00 55 52 25
        if (phoneNumber.matches(PhoneNumberPattern.pattern6.toString())) {
            return "00" + this.refactorPhoneNumberForSeparator(phoneNumber, " ");
        }

        //+330-55-55-66-33
        if (phoneNumber.matches(PhoneNumberPattern.pattern7.toString())) {
            return this.refactorPhoneNumberForSeparator(phoneNumber, "\\-");
        }

        //+330.00.55.52.42
        if (phoneNumber.matches(PhoneNumberPattern.pattern8.toString())) {
            return "00" + this.refactorPhoneNumberForSeparator(phoneNumber, "\\.");
        }

        //00 00 55 55 33
        if (phoneNumber.matches(PhoneNumberPattern.pattern9.toString())) {
            return phoneNumber.replace(" ", "");
        }

        //00-35-55-85-21
        if (phoneNumber.matches(PhoneNumberPattern.pattern10.toString())) {
            return phoneNumber.replace("-", "");
        }

        //00.45.55.63.57
        if (phoneNumber.matches(PhoneNumberPattern.pattern11.toString())) {
            return phoneNumber.replace(".", "");
        }

        //0000555302
        if (phoneNumber.matches(PhoneNumberPattern.pattern12.toString())) {
            return phoneNumber;
        }

        return phoneNumber;
    }

    private String refactorPhoneNumberForSeparator(String phoneNumber, String regexSeparator) {
        StringBuilder basedPhoneNumber = new StringBuilder("00");
        for (String s : phoneNumber.split(regexSeparator)) {
            if (s.length() == 2 && !s.equals("00")) {
                basedPhoneNumber.append(s);
            }
        }
        return basedPhoneNumber.toString();
    }

}
