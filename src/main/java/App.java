import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        List<String[]> lines = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
            .stream().map(string -> string.split(";",-1))
            .collect(toList());

        // Apply dark magic here...
        for (String[] strings : lines) {
            System.out.println(Arrays.toString(strings));
        }

        System.out.println("Result goes here");
    }
}
