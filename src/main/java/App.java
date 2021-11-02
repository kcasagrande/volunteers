import exceptions.CsvEmptyException;
import exceptions.CsvNotExistException;
import java.io.IOException;

public class  App {
    public static void main(String[] args) throws IOException, CsvNotExistException, CsvEmptyException {
        UserServices testUserServices = new UserServices();
        testUserServices.ShowUserList();
    }
}