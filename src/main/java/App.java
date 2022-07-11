import org.example.volunteers.Cleaner;
import org.example.volunteers.Volunteer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        Pattern quotes = Pattern.compile("^\"([^\"]*)\"$");

        List<Volunteer> inputVolunteers = Files.readAllLines(Paths.get(args[0])).stream()
            .map(string -> Arrays.stream(string.split(";", -1))
            .map(token -> quotes.matcher(token).replaceAll("$1"))
            .collect(toList()))
            .map(tokens -> new Volunteer(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4)))
            .collect(toList());
        List<Volunteer> outputVolunteers = Cleaner.cleanUp(inputVolunteers);
        getDuplicatesVolunteer(outputVolunteers);
        PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/output.csv"));
        outputVolunteers.forEach(writer::println);
        writer.close();
    }

    public static Boolean ensureFieldIsNotEmpty(String field)
    {
        return field.isEmpty();
    }

    public static Boolean ensureMailIsValidFormat(String emailField)
    {
        String regexPatternEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regexPatternEmail);
        Matcher matcher = pattern.matcher(emailField);
        return matcher.matches();
    }

    public static List<Volunteer> getDuplicatesVolunteer(List<Volunteer> volunteers) {
        ArrayList<String> lastnames = new ArrayList<>();
        ArrayList<String> firstnames = new ArrayList<>();
        ArrayList<String> nicknames = new ArrayList<>();
        ArrayList<String> emails = new ArrayList<>();
        ArrayList<String> phones = new ArrayList<>();
        List<Volunteer> duplicates = null;
        for (int i = 0; i < volunteers.size(); i++) {
            if (volunteers.get(i).firstName != null){
                firstnames.add(volunteers.get(i).firstName);
            }
            if (volunteers.get(i).lastName != null){
                lastnames.add(volunteers.get(i).lastName);
            }
            if (volunteers.get(i).nickName != null){
                nicknames.add(volunteers.get(i).nickName);
            }
            if (volunteers.get(i).eMail != null){
                emails.add(volunteers.get(i).eMail);
            }
            if (volunteers.get(i).phone != null){
                phones.add(volunteers.get(i).phone);
            }
        }
        System.out.println(firstnames);
        return volunteers;
    }
}
