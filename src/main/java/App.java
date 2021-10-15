import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class App {

    public static Function<String, String[]> splitCSV = (line) -> line.split(";");
    public static Function<String[], String> joinCSV = (line) -> String.join(";", line);

    public static void main(String[] args) throws IOException {
        List<String[]> lines = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
            .stream()
            .sorted()
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

//    public List<String[]> fusedInput(List<String[]> actualLines) {
//        return actualLines
//                .stream()
//                .ma
//    }
}
