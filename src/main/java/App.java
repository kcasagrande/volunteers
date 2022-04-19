import org.example.volunteers.Merge;
import org.example.volunteers.Volunteer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        Pattern quotes = Pattern.compile("^\"([^\"]*)\"$");

        List<Volunteer> inputVolunteers = Files.readAllLines(Paths.get("src/main/resources/data.csv")).stream()
            .map(string -> Arrays.stream(string.split(";", -1))
            .map(token -> quotes.matcher(token).replaceAll("$1"))
            .collect(toList()))
            .map(tokens -> new Volunteer(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4)))
            .collect(toList());

        List<Volunteer> outputVolunteers = cleanUp(inputVolunteers);
        try (BufferedWriter bf = Files.newBufferedWriter(Paths.get("src/main/resources/output.csv"), StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
            bf.write(outputVolunteers.stream().map(String::valueOf).collect(Collectors.joining("\n")));
        }
    }



    private static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.
        Merge mg = new Merge();
        List<Volunteer> listVolunteers = mg.mergeByName(volunteers);
        List<Volunteer> listVolunteersBis = mg.mergeByPhoneNumber(listVolunteers);
        return listVolunteersBis;
    }
}
