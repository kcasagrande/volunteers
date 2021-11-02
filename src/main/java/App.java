

import org.example.volunteers.model.Person;
import org.example.volunteers.model.PersonProperties;
import org.example.volunteers.service.Parser;
import org.example.volunteers.service.PersonService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class App {
    public static Parser parser = new Parser();
    public static PersonService personService = new PersonService();


    public static void main(String[] args) throws IOException {

        System.out.println("Bienvenue ! Comment souhaitez-vous trier la liste : (telephone), (mail), (nom), (tous)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choiceSelected = reader.readLine();

        List<Map<PersonProperties, String>> parsedFile = parser.parseCsv("src/main/resources/data.csv", ";");

        List<Person> listPerson = personService.transformInPersonObject(parsedFile);
        List<Person> listFinal = new ArrayList<>();
        switch (choiceSelected) {
            case "nom":
                listFinal = personService.listSortByName(listPerson);
                break;
            case "telephone":
                listFinal = personService.filterPersonDuplicateByPhoneNumber(listPerson);
                break;
            case "mail":
                listFinal = personService.filterPersonDuplicateByEmail(listPerson);
                break;
            case "tous":
                listFinal = personService.listSortByName(listPerson);
                listFinal = personService.filterPersonDuplicateByEmail(listFinal);
                listFinal = personService.filterPersonDuplicateByPhoneNumber(listFinal);
                break;
            default:
                System.out.println("Cas non géré");
                break;
        }
        listFinal.forEach(person -> {
            System.out.println("First name : " + person.getFirstName() + " Last name : " + person.getLastName() +" Surname : " + person.getUserName() + " Email : " + person.getEmail() + " Phone number : " + person.getPhoneNumber());
        });
        System.out.println("Total : " + listFinal.size());
    }
}
