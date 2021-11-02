package org.example.volunteers.utils;

import org.example.volunteers.model.Person;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

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
}
