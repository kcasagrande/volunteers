import org.example.volunteers.Cleaner;
import org.example.volunteers.Volunteer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

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

    ArrayList<Volunteer> outputVolunteers = Cleaner.clean(inputVolunteers);

    PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/output.csv"));

    try (writer) {
      writer.println(outputVolunteers.toString()
          .replaceAll("\\[|\\]", "")
          .replaceAll(", ", "\r\n")
      );
    }
  }
}
