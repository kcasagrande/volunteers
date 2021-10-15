import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        List<String[]> lines = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
            .stream()
            .sorted()
            .map(string -> string.split(";"))
            .collect(toList());

        // Apply dark magic here...
        lines.stream().map(line -> String.join(";", line)).forEach(System.out::println);

        System.out.println("Result goes here");
    }
}
