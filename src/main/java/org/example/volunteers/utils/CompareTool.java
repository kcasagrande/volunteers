package org.example.volunteers.utils;

import org.example.volunteers.model.Person;

public class CompareTool {
    private CompareTool() {
    }

    public static boolean comparePersons(Person person1, Person person2){
        if(compareMailAdress(person1.getEmail(), person2.getEmail()) > 0.9){
            return compareNameSurname(person1, person2);
        }
        return false;
    }

    private static double compareMailAdress(String mailAdress1, String mailAdress2){
        return StringSimilarity.similarity(mailAdress1, mailAdress2);
    }

    private static boolean compareNameSurname(Person person1, Person person2){
        var compareResultSurname = StringSimilarity.similarity(person1.getSurname(), person2.getSurname()) > 0.9;
        var compareResultName = StringSimilarity.similarity(person1.getName(), person2.getName()) > 0.9;

        return compareResultName && compareResultSurname;
    }
}
