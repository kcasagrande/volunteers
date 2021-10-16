package launcher;

import org.example.volunteers.model.Person;
import org.example.volunteers.utils.CsvFileReader;
import org.example.volunteers.utils.DuplicateFinder;
import org.example.volunteers.utils.PersonParser;

import java.util.Comparator;
import java.util.List;

public class App {
    public static void main(String[] args){
        PersonParser parser = new PersonParser();

        try {
            var openedFile = CsvFileReader.extractDatas("src/main/resources/data.csv");

            List<Person> personList = parser.parse(openedFile);

            var listWithoutDuplicate = DuplicateFinder.eliminateDuplicate(personList);
            listWithoutDuplicate.sort(Comparator.comparing(Person::getSurname));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
