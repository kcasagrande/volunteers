package org.example.volunteers.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {
    private final Pattern regexPatternEmail = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
    private Matcher regMatcher;
    private final Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[&^%$#@!~]");

    public Boolean validateEmailAddress(String emailAddress) {
        regMatcher = regexPatternEmail.matcher(emailAddress);
        return regMatcher.matches();
    }

    public Boolean validateFirstName(String firstName){
        regMatcher = SPECIAL_REGEX_CHARS.matcher(firstName);
        return regMatcher.find();
    }

    public Boolean validateLastName(String lastName){
        regMatcher = SPECIAL_REGEX_CHARS.matcher(lastName);
        return regMatcher.find();
    }
}
