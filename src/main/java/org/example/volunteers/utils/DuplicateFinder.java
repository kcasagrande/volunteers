package org.example.volunteers.utils;

import org.example.volunteers.model.Person;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DuplicateFinder {
    private DuplicateFinder(){

    }

    public static List<Person> eliminateDuplicate(Map<String, List<Person>> personListGroupedWithPhoneNumber){
        List<Person> resultList = new ArrayList<>();

        personListGroupedWithPhoneNumber.forEach((n, personListToCompare) -> {
            List<Person> finalList = new ArrayList<>(personListToCompare);
            for (int i = 0; i < personListToCompare.size(); i++) {
                Person actualPerson = personListToCompare.get(i);
                int j = i+1;
                if(j < personListToCompare.size()){
                    while(j < personListToCompare.size()){
                        Person nextPerson = personListToCompare.get(j);
                        var fieldCompareResult = CompareTool.comparePersons(actualPerson, nextPerson);
                        if(fieldCompareResult) finalList.remove(actualPerson);
                        j++;
                    }
                }

            }

            resultList.addAll(finalList);
        });
        return resultList;
    }

    public static Person getDuplicateToChoose(Person person1, Person person2) throws IllegalAccessException {
        Field[] allFields = Person.class.getDeclaredFields();

        int person1Cost = 0;
        int person2Cost = 0;

        for(Field field : allFields){
            field.setAccessible(true);
            if(!field.get(person1).toString().equals("")){
                person1Cost++;
            }
            if(!field.get(person2).toString().equals("")){
                person2Cost++;
            }
        }
        return person1Cost == person2Cost || person1Cost < person2Cost ? person1 : person2;
    }
}
