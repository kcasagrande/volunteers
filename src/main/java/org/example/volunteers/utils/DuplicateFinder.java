package org.example.volunteers.utils;

import org.example.volunteers.model.Person;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

public class DuplicateFinder {
    private DuplicateFinder(){

    }

    public static List<Person> eliminateDuplicate(List<Person> userList){
        List<Person> resultList = new ArrayList<>();

        var personListGroupedWithPhoneNumber = userList.stream().collect(Collectors.groupingBy(Person::getPhoneNumber));

        personListGroupedWithPhoneNumber.forEach((n, personListToCompare) -> {
            List<Person> finalList = new ArrayList<>(personListToCompare);

            for (int i = 0; i < personListToCompare.size(); i++) {
                Person actualPerson = personListToCompare.get(i);

                if(i+1 < personListToCompare.size()){
                    Person nextPerson = personListToCompare.get(i+1);

                    var fieldCompareResult = CompareTool.comparePersons(actualPerson, nextPerson);
                    if(fieldCompareResult) finalList.remove(actualPerson);
                }
            }

            resultList.addAll(finalList);
        });

        return resultList;
    }
}
