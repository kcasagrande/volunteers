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
        Merge merge = new Merge();

        for (Volunteer volunteer : volunteers) {

            if (volunteer.firstname.equals("") && volunteer.name.equals("")) continue;

            List<Volunteer> comparedVolunteers = compare.compareNameInMail(volunteers, volunteer.name);

            comparedVolunteers = compare.compareFirstName(comparedVolunteers, volunteer.firstname);

            Volunteer mergedVolunteer;

            if (comparedVolunteers.size() == 0) continue;
            else if (comparedVolunteers.size() > 1) mergedVolunteer = merge.mergeVolunteers(comparedVolunteers);
            else mergedVolunteer = comparedVolunteers.get(0);

            arrayVolunteers.add(mergedVolunteer);
            
        }
        
        List<Volunteer> uniqueVolunteersArray = arrayVolunteers.stream().distinct().collect(toList()); // Remove duplicated volunteers

        displayData(uniqueVolunteersArray);
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
