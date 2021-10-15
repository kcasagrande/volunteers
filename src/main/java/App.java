import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class App {

    public static Function<String, String[]> splitCSV = (line) -> line.split(";");
    public static Function<String[], String> joinCSV = (line) -> String.join(";", line);

    public static void main(String[] args) throws IOException {
        List<String[]> lines = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
            .stream()
            .map(splitCSV)
            .collect(toList());

        // Apply dark magic here...
        lines.stream().map(joinCSV).forEach(System.out::println);
    }

    public static List<String[]> groupedInput(List<String[]> actualLines) {
        return actualLines
                .stream()
                .map(joinCSV)
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .map(splitCSV)
                .collect(toList());
    }

    public User createUserFromLine(String[] line) {
        return new User(line[0], line[1], line[2], line[3], line[4]);
    }

    public List<String[]> groupByLastname(List<String[]> actualLines) {
        return actualLines;
    }

}
