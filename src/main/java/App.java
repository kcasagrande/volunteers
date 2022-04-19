import org.example.Tools;
import org.example.volunteers.Volunteer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
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
        Path path = Paths.get(args[0]);
        List<Volunteer> inputVolunteers = Files.readAllLines(path).stream()
                .map(string -> Arrays.stream(string.split(";", -1))
                        .map(token -> quotes.matcher(token).replaceAll("$1"))
                        .collect(toList()))
                .map(tokens -> new Volunteer(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4)))
                .collect(toList());

        List<Volunteer> outputVolunteers = cleanUp(inputVolunteers);

        PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/output.csv"));

        outputVolunteers.forEach(volunteer -> {
            writer.println(volunteer);
        });

        writer.close();
    }

    private static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.
        List<Volunteer> formatListVolunteers = new ArrayList<Volunteer>();

        volunteers.forEach(volunteer -> {
            formatListVolunteers.add(Tools.toFormatVolunteer(volunteer));
        });

        return new ArrayList<>(formatListVolunteers);
    }
}
