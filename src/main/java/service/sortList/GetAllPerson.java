package service.sortList;


import model.Person;
import model.PersonProperties;
import java.util.*;

public class GetAllPerson {
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
}
