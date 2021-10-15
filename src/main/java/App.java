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

        List<Person> listPerson = personService.getListPersonWDuplicate(parsedFile);
        System.out.println(listPerson);
        // Apply dark magic here...

        System.out.println("Result goes here");
    }
}
