import org.example.volunteers.model.Person;
import org.example.volunteers.utils.CsvFileReader;
import org.example.volunteers.utils.PersonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

class DemoTest {
    @Test
    void testModel(){
        Person person = new Person("GUYON", "clement.guyon@gmail.com", "SahyyKI0", "Clément", "0781915332");
        Assertions.assertInstanceOf(Person.class, person);
    }

    @Test
    void testParser(){
        PersonParser personParser = new PersonParser();
        Assertions.assertInstanceOf(ArrayList.class, personParser.parse(new ArrayList<>()));
    }

    @Test
    void noSuchFileExceptionThrow(){
        Assertions.assertThrows(NoSuchFileException.class, () -> CsvFileReader.extractDatas("src/main/resources/azdazd.csv") );
    }
}
