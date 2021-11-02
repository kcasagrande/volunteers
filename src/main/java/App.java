import lombok.extern.java.Log;
import model.Person;
import model.PersonProperties;
import service.Parser;
import service.PersonService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;


public class App {
    public static Parser parser = new Parser();
    public static PersonService personService = new PersonService();


    public static void main(String[] args) throws IOException {

        System.out.println("Bienvenu ! Selectionnez comment voulez vous triez la liste : (telephone), (mail), (nom), (tous)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choiceSelected = reader.readLine();

        switch (choiceSelected){
            case "nom":
                List<Map<PersonProperties, String>> parsedFile = parser.parseCsv("src/main/resources/data.csv",";");

                List<Person> listPerson = personService.getListPersonWDuplicate(parsedFile);
                System.out.println(listPerson);
                break;
            case "telephone":

                break;
            case "mail":

                break;
            case "tous":

                break;
            default:
                System.out.println("Cas non gérer");
                break;
        }

        // Apply dark magic here...
        System.out.println("Result goes here");
        System.out.println(personService.refactorPhoneNumber("00-35-55-85-21"));
    }
}
