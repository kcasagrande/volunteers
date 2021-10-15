package launcher;

import org.example.volunteers.model.Person;
import org.example.volunteers.utils.CsvFileReader;
import org.example.volunteers.utils.DuplicateFinder;
import org.example.volunteers.utils.PersonParser;

import java.util.List;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args){
        PersonParser parser = new PersonParser();

        try {
            var openedFile = CsvFileReader.extractDatas("src/main/resources/data.csv");
            List<Person> personList = parser.parse(openedFile);
            var listWithoutDuplicate = DuplicateFinder.findDuplicate(personList);
            listWithoutDuplicate.forEach(person -> Logger.getLogger(person.toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
