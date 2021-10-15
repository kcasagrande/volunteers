import Services.csv.CsvService;
import exceptions.CsvEmptyException;
import exceptions.CsvNotExistException;
import user.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException, CsvNotExistException, CsvEmptyException {
        CsvService csvService = new CsvService("src/main/resources/data.csv");
        List<String[]> lines = csvService.readAllLines();
        List<User> linesUser = csvService.convertListUser(lines);

        System.out.println("Result goes here");
    }
}
