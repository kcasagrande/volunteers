import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.example.volunteers.comparison.Comparator;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        List<String[]> lines = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
            .stream().map(string -> string.split(";",-1))
            .collect(toList());

        // Apply dark magic here...
        System.out.println("Result goes here");

        System.out.println(lines.size());
        Comparator comparator = new Comparator();
        comparator.VoidedCompareList(lines);
        System.out.println(lines.size());

        //for (String[] strings : lines) {
        //    System.out.println(Arrays.toString(strings));
        //}
    }
}
