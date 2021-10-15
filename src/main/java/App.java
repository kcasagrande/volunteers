import model.Person;
import model.PersonProperties;
import service.Parser;
import service.sortList.GetAllPerson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class App {
    public static Parser parser = new Parser();
    public static GetAllPerson getAllPerson = new GetAllPerson();


    public static void main(String[] args) throws IOException {
         List<Map<PersonProperties, String>> lol = parser.parseCsv("src/main/resources/data.csv",";");
         System.out.println(lol);


        List<Person> listPerson = getAllPerson.getListPersonWDuplicate(lol);
        System.out.println(listPerson);

        // Apply dark magic here...

        System.out.println("Result goes here");
        System.out.println("Ouh ouh c'est reparti comme en 46");
    }
}
