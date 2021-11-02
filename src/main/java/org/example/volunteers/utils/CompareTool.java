package org.example.volunteers.utils;

import org.example.volunteers.model.Person;

import java.lang.reflect.Field;

public class CompareTool {
    private CompareTool() {
    }

    public static boolean comparePersons(Person person1, Person person2){
        try {
            return compareFields(person1, person2) > 0.54 || compareNameSurname(person1, person2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean compareNameSurname(Person person1, Person person2){
        var compareResultSurnameName = StringSimilarity.similarity(person1.getSurname(), person2.getName()) > 0.7;
        var compareResultSurname = StringSimilarity.similarity(person1.getSurname(), person2.getSurname()) > 0.7;
        var compareResultName = StringSimilarity.similarity(person1.getName(), person2.getName()) > 0.7;

        return compareResultName && compareResultSurname || compareResultSurnameName;
    }

    public static double compareFields(Person person1, Person person2) throws IllegalAccessException {
        Field[] allFields = Person.class.getDeclaredFields();
        double totalSimilarity = 0;
        int fieldsLength = 0;

        for(Field field : allFields){
            field.setAccessible(true);
            if(!field.get(person1).toString().equals("") && !field.get(person2).toString().equals("")){
                var similarity = StringSimilarity.similarity(field.get(person1).toString(), field.get(person2).toString());
                totalSimilarity += similarity;
                fieldsLength++;
            }
        }

        return totalSimilarity/fieldsLength;
    }
}
