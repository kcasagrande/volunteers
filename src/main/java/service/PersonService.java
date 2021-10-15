package service;

import model.Person;
import model.PersonProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonService {
    public List<Person> getListPersonWDuplicate(List<Map<PersonProperties, String>> listMap){
        List<Person> listPersonWDuplicate = new ArrayList<>();

        for ( int i = 0; i < listMap.size(); i++){
            Person person = new Person();

            person.id = i +1;
            person.lastName = listMap.get(i).get(PersonProperties.lastName);
            person.firstName = listMap.get(i).get(PersonProperties.firstName);
            person.userName = listMap.get(i).get(PersonProperties.userName);
            person.email = listMap.get(i).get(PersonProperties.email);
            person.phoneNumber = listMap.get(i).get(PersonProperties.phoneNumber);

            listPersonWDuplicate.add(person);
        }
        return listPersonWDuplicate;
    }
    public List<Person> listSortByName(List<Person> personList){

        List<Person> toDelete = new ArrayList<>();

        for (Person person : personList){
           List<Person> listPerson = personList.stream().filter(w -> {
                if (person.firstName.equals(w.firstName) && person.lastName.equals(w.lastName) ){
                    return true;
                }
                return false;
            }).collect(Collectors.toList());

            if (listPerson.size() > 1 ){
                toDelete.add(person);
            }
        }
        personList.removeAll(toDelete);
        return  personList;
    }
}
