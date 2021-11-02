import lombok.extern.java.Log;
import model.Person;
import model.PersonProperties;
import service.Parser;
import service.PersonService;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class App {
    public static Parser parser = new Parser();
    public static PersonService personService = new PersonService();


    public static void main(String[] args) throws IOException {
         List<Map<PersonProperties, String>> parsedFile = parser.parseCsv("src/main/resources/data.csv",";");

        List<Person> listPerson = personService.transformInPersonObject(parsedFile);
        System.out.println(listPerson);

        List<Person> listTest= personService.listSortByName(listPerson);
        System.out.println(listTest);
        List<Person> listEmail = personService.filterPersonDuplicateByEmail(listTest);
        // Apply dark magic here...
//        personService.filterPersonDuplicateByPhoneNUmber(listPerson);
        System.out.println("Result goes here");
        System.out.println(personService.refactorPhoneNumber("00-35-55-85-21"));
    }
}
