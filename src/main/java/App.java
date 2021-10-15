import org.example.volunteers.Searcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        List<String[]> lines = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
            .stream().map(string -> string.split(";", -1))
            .collect(toList());

        // Apply dark magic here...
        Searcher chercheur = new Searcher();
        chercheur.searchSimilar(lines.get(0),lines);
        System.out.println("Result goes here");
    }

   /* */

}
