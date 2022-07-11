package org.example.volunteers.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {

    private Matcher regMatcher;
    private final Pattern regexPatternEmail = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");

    private final Pattern regexPatternName = Pattern.compile("[&^%$#@!~]");

    private final Pattern regexPatternPhone = Pattern.compile("(\\+\\d{2}|0|\\+\\d{2}\\(0\\))\\d{9}");

    public Boolean validateEmailAddress(String emailAddress) {
        regMatcher = regexPatternEmail.matcher(emailAddress);
        return regMatcher.matches();
    }

    public Boolean validateFirstName(String firstName){
        regMatcher = regexPatternName.matcher(firstName);
        return regMatcher.find();
    }

    public Boolean validateLastName(String lastName) {
        regMatcher = regexPatternName.matcher(lastName);
        return regMatcher.find();
    }

    public Boolean validatePhoneNumber(String phoneNumber) {
        regMatcher = regexPatternPhone.matcher(phoneNumber);
        return regMatcher.matches();
    }
}