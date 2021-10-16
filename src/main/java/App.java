import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class App {

    public static List<Volunteer> arrayVolunteers = new ArrayList<Volunteer>();

    public static void main(String[] args) throws IOException {
        List<String[]> lines = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
            .stream().map(string -> string.split(";"))
            .collect(toList());

        // Apply dark magic here...

        System.out.println("Result goes here");

        Parser parser = new Parser(lines);

        List<Volunteer> volunteers = parser.format();

        for (Volunteer volunteer : volunteers) volunteer.tel = parser.formatPhoneNumber(volunteer.tel);

        Compare compare = new Compare();

        for (Volunteer volunteer : volunteers) {

            if (volunteer.firstname.equals("") && volunteer.name.equals("")) continue;

            List<Volunteer> comparedVolunteers = compare.compareNameInMail(volunteers, volunteer.name);
            
            displayData(comparedVolunteers);
        }


    }

    public static void displayData(List<Volunteer> volunteers) {
        for (Volunteer volunteer : volunteers) {
            System.out.println("\n");
            System.out.println("Name : " + volunteer.name);
            System.out.println("Firstname : " + volunteer.firstname);
            System.out.println("NameTag : " + volunteer.nametag);
            System.out.println("Mail : " + volunteer.mail);
            System.out.println("Tel : " + volunteer.tel);
        }
        System.out.println("\n===== "+ volunteers.size()+" Volunteers =====");
    }
}
