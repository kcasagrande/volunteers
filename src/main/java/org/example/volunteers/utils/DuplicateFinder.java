package org.example.volunteers.utils;

import org.example.volunteers.model.Person;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

public class DuplicateFinder {
    private DuplicateFinder(){

    }

    public static List<Person> findDuplicate(List<Person> userList){
        List<Person> resultList = new ArrayList<>();

        var personListGroupedWithPhoneNumber = userList.stream().collect(Collectors.groupingBy(Person::getPhoneNumber));

        personListGroupedWithPhoneNumber.forEach((n, personListToCompareOrderByPhoneNumber) -> {
            List<Person> finalList = new ArrayList<>(personListToCompareOrderByPhoneNumber);

            for (Iterator<Person> iterator = personListToCompareOrderByPhoneNumber.iterator(); iterator.hasNext();) {
                Person nextPerson = iterator.next();

                if(iterator.hasNext()){
                    var fieldCompareResult = CompareTool.comparePersons(nextPerson, iterator.next());
                    if(fieldCompareResult) finalList.remove(nextPerson);
                }
            }

            resultList.addAll(finalList);
        });

        return resultList;
    }
}
