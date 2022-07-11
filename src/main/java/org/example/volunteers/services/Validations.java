package org.example.volunteers.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {
    private Pattern regexPattern;
    private Matcher regMatcher;

    private static Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");

    public Boolean validateEmailAddress(String emailAddress) {
        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher = regexPattern.matcher(emailAddress);
        return regMatcher.matches();
    }

    public Boolean validateFirstName(String firstName){
        regexPattern = SPECIAL_REGEX_CHARS;
        regMatcher = regexPattern.matcher(firstName);
        return regMatcher.matches();
    }

    public Boolean validateLastName(String lastName){
        regexPattern = SPECIAL_REGEX_CHARS;
        regMatcher = regexPattern.matcher(lastName);
        return regMatcher.matches();
    }
}
