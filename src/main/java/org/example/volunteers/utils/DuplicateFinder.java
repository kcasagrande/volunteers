package org.example.volunteers.utils;

import org.example.volunteers.model.Person;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Duplicate Finder
 */
public class DuplicateFinder {
    /**
     * Private constructor
     */
    private DuplicateFinder(){}

    /**
     * Eliminate duplicated lines in the given list
     * @param personListGroupedWithPhoneNumber Map which contains list of person parsed from file and grouped with
     *                                         phone number
     * @return List of person without duplicated lines
     */
    public static List<Person> eliminateDuplicate(Map<String, List<Person>> personListGroupedWithPhoneNumber){
        List<Person> resultList = new ArrayList<>();

        personListGroupedWithPhoneNumber.forEach((n, personListToCompare) -> {
            List<Person> finalList = new ArrayList<>(personListToCompare);

            for (int i = 0; i < personListToCompare.size(); i++) {
                Person actualPerson = personListToCompare.get(i);
                int nextIterator = i+1;
                if(nextIterator < personListToCompare.size()){
                    while(nextIterator < personListToCompare.size()){
                        Person nextPerson = personListToCompare.get(nextIterator);
                        var isSamePerson = CompareTool.comparePersons(actualPerson, nextPerson);
                        if(isSamePerson) {
                            try {
                                finalList.remove(getDuplicateToChoose(actualPerson, nextPerson));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                        nextIterator++;
                    }
                }
            }
            resultList.addAll(finalList);
        });
        return resultList;
    }

    /**
     * Allows to determine which duplicated person have to be deleted
     * @param person1 First person to compare
     * @param person2 Second person to compare
     * @return Returns the person which have to be deleted
     * @throws IllegalAccessException Exception thrown if system can't access to fields
     */
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
