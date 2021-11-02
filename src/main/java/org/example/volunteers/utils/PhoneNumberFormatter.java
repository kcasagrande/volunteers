package org.example.volunteers.utils;

/**
 * Phone Number Formatter
 */
public class PhoneNumberFormatter {
    private PhoneNumberFormatter(){}

    /**
     * Format phone number gave in parameters
     * @param phoneNumber Phone number to format
     * @return Formatted phone number
     */
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
