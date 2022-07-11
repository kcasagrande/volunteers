package org.example.volunteers.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {
    private Pattern regexPattern;
    private Matcher regMatcher;

    public Boolean validateEmailAddress(String emailAddress) {
        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher = regexPattern.matcher(emailAddress);
        return regMatcher.matches();
    }

    public Boolean validatePhoneNumber(String phoneNumber) {
        regexPattern = Pattern.compile("(\\+\\d{2}|0|\\+\\d{2}\\(0\\))\\d{9}");
        regMatcher = regexPattern.matcher(phoneNumber);
        return regMatcher.matches();
    }
}
