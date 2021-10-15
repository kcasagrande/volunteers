package launcher;

import org.example.volunteers.utils.CsvFileReader;
import org.example.volunteers.utils.PersonParser;

public class App {
    public static void main(String[] args){
        PersonParser parser = new PersonParser();

        try {
            var openedFile = CsvFileReader.extractDatas("src/main/resources/data.csv");
            parser.parse(openedFile);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
