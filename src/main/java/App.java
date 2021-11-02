import Services.csv.CsvService;
import exceptions.CsvEmptyException;
import exceptions.CsvNotExistException;
import user.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class  App {
    public static void main(String[] args) throws IOException, CsvNotExistException, CsvEmptyException {
        UserServices testUserServices= new UserServices();
        testUserServices.ShowUserList();
    }
}