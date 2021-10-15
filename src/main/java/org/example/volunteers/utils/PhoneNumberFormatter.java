package org.example.volunteers.utils;

public class PhoneNumberFormatter {
    private PhoneNumberFormatter(){
    }

    public static String format(String phoneNumber) {
        return phoneNumber
            .trim()
            .replace("+33", "0")
            .replace(" ", "")
            .replace("-", "")
            .replace(".", "")
            .replace("(", "")
            .replace(")", "");
    }
}
