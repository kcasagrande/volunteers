package org.volunteers.app;

import org.example.volunteers.Volunteer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

        PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/output.csv"));
        writer.println(outputVolunteers);
    }

    public static List<Volunteer> cleanUp(List<Volunteer> inputVolunteers) {
        List<Volunteer> outputVolunteers = new ArrayList<>();

        for (Volunteer volunteer : inputVolunteers) {

            volunteer.setPhone(volunteer.formatPhone(volunteer.getPhone()));

            if (outputVolunteers.stream().noneMatch(v -> v.equals(volunteer))) {
                outputVolunteers.add(volunteer);
            }
        }

        return outputVolunteers;
    }
}
