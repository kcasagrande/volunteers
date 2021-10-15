import org.example.volunteers.models.ClientList;
import org.example.volunteers.services.CsvReader;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        CsvReader csvReader = new CsvReader();
        List<String[]> fileLines = csvReader.getLinesFromFile("src/main/resources/data.csv");

        ClientList clientList = new ClientList();
        clientList.setClientListFromFileLines(fileLines);
        clientList.normalize();
    }
}
