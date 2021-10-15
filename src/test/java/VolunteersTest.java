import org.example.volunteers.model.Person;
import org.example.volunteers.utils.CsvFileReader;
import org.example.volunteers.utils.PersonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

class VolunteersTest {
    @Test
    void testModel(){
        Person person = new Person("GUYON", "clement.guyon@gmail.com", "SahyyKI0", "Clément", "0781915332");
        Assertions.assertInstanceOf(Person.class, person);
    }

    @Test
    void testParser(){
        PersonParser personParser = new PersonParser();
        List<Person> listResult = personParser.parse(
                new ArrayList<>()
                {
                    {
                        add(new String[]{ "Delbecq", "Adeline","RedWappin", "adeline.delbecq@gmail.com", "01.02.03.04.05"});
                        add(new String[]{"Guyon", "Clément", "SahyyKI0", "clement.guyon@gmail.com", "0781915332"});
                        add(new String[]{"Casagrande", "Kevin", "KCasa", "kevin.casagrande@gmail.com", "823892043"});
                    }
                }
        );

        Assertions.assertEquals("adeline", listResult.get(0).getSurname());
    }

    @Test
    void noSuchFileExceptionThrow(){
        Assertions.assertThrows(NoSuchFileException.class, () -> CsvFileReader.extractDatas("src/main/resources/azdazd.csv") );
    }

    @Test
    void openCsv() {
        try {
            List<String[]> stringTab = CsvFileReader.extractDatas("src/main/resources/data.csv");
            Assertions.assertInstanceOf(ArrayList.class, stringTab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
