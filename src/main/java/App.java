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

        System.out.println("Bienvenue ! Selectionnez comment voulez vous triez la liste : (telephone), (mail), (nom), (tous)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choiceSelected = reader.readLine();

        List<Map<PersonProperties, String>> parsedFile = parser.parseCsv("src/main/resources/data.csv",";");

        List<Person> listPerson = personService.transformInPersonObject(parsedFile);
        List<Person> listFinal = null;
        switch (choiceSelected){
            case "nom":
                listFinal = personService.listSortByName(listPerson);
                break;
            case "telephone":
                listFinal = personService.filterPersonDuplicateByPhoneNUmber(listPerson);

                break;
            case "mail":
                listFinal = personService.filterPersonDuplicateByEmail(listPerson);

                break;
            case "tous":
                listFinal = personService.listSortByName(listPerson);
                listFinal = personService.filterPersonDuplicateByEmail(listFinal);
                listFinal = personService.filterPersonDuplicateByPhoneNUmber(listFinal);

                break;
            default:
                System.out.println("Cas non gérer");
                break;
        }
        System.out.println(listFinal);
        // Apply dark magic here...
    }
}
