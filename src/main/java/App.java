import org.example.volunteers.Volunteer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

        List<Volunteer> outputVolunteers = cleanUp(inputVolunteers);


        PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/output.csv"));
        writer.println(outputVolunteers);
    }

    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        List<Volunteer> uniqueVolunteers = volunteers;
        for (int i = 0; i < volunteers.size(); ++i) {
            for (int u = i+1; u < volunteers.size(); ++u) {
                if (!uniqueVolunteers.contains(volunteers.get(u))) {
                    if (volunteers.get(i).compare(volunteers.get(u))) {
                        uniqueVolunteers.remove(u);
                    }
                }
            }
        }
        return new ArrayList<>(uniqueVolunteers);
    }

}