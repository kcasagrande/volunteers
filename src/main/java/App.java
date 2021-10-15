import model.Person;
import model.PersonProperties;
import service.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class App {
    public static Parser parser = new Parser();


    public static void main(String[] args) throws IOException {
         List<Map<PersonProperties, String>> lol = parser.parseCsv("src/main/resources/data.csv",";");
         System.out.println(lol);

        List<Person> listPersonWDuplicate = new ArrayList<>();

        for ( int i = 0; i < lol.size(); i++){
            Person person = new Person();

            person.id = i +1;
            person.lastName = lol.get(i).get(PersonProperties.lastName);
            person.firstName = lol.get(i).get(PersonProperties.firstName);
            person.userName = lol.get(i).get(PersonProperties.userName);
            person.email = lol.get(i).get(PersonProperties.email);
            person.phoneNumber = lol.get(i).get(PersonProperties.phoneNumber);

            listPersonWDuplicate.add(person);
        }
System.out.println(listPersonWDuplicate);
        // Apply dark magic here...

        System.out.println("Result goes here");
        System.out.println("Ouh ouh c'est reparti comme en 46");
    }
}
