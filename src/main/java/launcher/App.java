package launcher;

import org.example.volunteers.model.Person;
import org.example.volunteers.utils.CsvFileReader;
import org.example.volunteers.utils.DuplicateFinder;
import org.example.volunteers.utils.PersonParser;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args){
        PersonParser parser = new PersonParser();

        try {
            var openedFile = CsvFileReader.extractDatas("src/main/resources/data.csv");

            List<Person> personList = parser.parse(openedFile);

            var listWithoutDuplicate = DuplicateFinder.eliminateDuplicate(personList.stream().collect(Collectors.groupingBy(Person::getPhoneNumber)));
            listWithoutDuplicate = DuplicateFinder.eliminateDuplicate(listWithoutDuplicate.stream().collect(Collectors.groupingBy(Person::getName)));

            listWithoutDuplicate.sort(Comparator.comparing(Person::getName));

            System.out.println(listWithoutDuplicate.size());
            listWithoutDuplicate.forEach(person -> System.out.println(person.toString()));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
