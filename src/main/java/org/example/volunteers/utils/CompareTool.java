package org.example.volunteers.utils;

import org.example.volunteers.model.Person;

public class CompareTool {
    private CompareTool() {
    }

    public static boolean comparePersons(Person person1, Person person2){
        if(compareMailAdress(person1.getEmail(), person2.getEmail()) > 0.7 && !person1.getEmail().equals(" ") && !person2.getEmail().equals(" ") && person1.getEmail() != null && person2.getEmail() != null){
            return compareNameSurname(person1, person2);
        }
        return false;
    }

    private static double compareMailAdress(String mailAdress1, String mailAdress2){
        return StringSimilarity.similarity(mailAdress1, mailAdress2);
    }

    private static boolean compareNameSurname(Person person1, Person person2){
        var compareResultSurnameName = StringSimilarity.similarity(person1.getSurname(), person2.getName()) > 0.7;
        var compareResultSurname = StringSimilarity.similarity(person1.getSurname(), person2.getSurname()) > 0.7;
        var compareResultName = StringSimilarity.similarity(person1.getName(), person2.getName()) > 0.7;

        return compareResultName && compareResultSurname || compareResultSurnameName;
    }
}
