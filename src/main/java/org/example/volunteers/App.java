package org.example.volunteers;

import org.example.volunteers.services.VolunteerService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        List<Volunteer> inputVolunteers = readVolunteersFile(args[0]);

        List<Volunteer> outputVolunteers = Cleaner.cleanUp(inputVolunteers);
        outputNewVolunteersFile(outputVolunteers, "output");
    }

    public static List<Volunteer> readVolunteersFile (String filePath) throws IOException {
        Pattern quotes = Pattern.compile("^\"([^\"]*)\"$");
        AtomicInteger index = new AtomicInteger(1);

        return Files.readAllLines(Paths.get(filePath)).stream()
                .map(string -> Arrays.stream(string.split(";", -1))
                        .map(token -> quotes.matcher(token).replaceAll("$1"))
                        .collect(toList()))
                .map(tokens -> {
                    try {
                        return VolunteerService.createNewVolunteer(
                                index.getAndIncrement(),
                                tokens.get(1),
                                tokens.get(0),
                                tokens.get(2),
                                tokens.get(3),
                                tokens.get(4)
                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(toList());
    }

    public static void outputNewVolunteersFile(List<Volunteer> volunteers, String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/" + fileName + ".csv"));
        volunteers.forEach(writer::println);
        writer.close();
    }
}
