import org.example.volunteers.model.Person;
import org.example.volunteers.utils.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

class VolunteersTest {
    static List<String[]> stringTab;
    static PersonParser personParser;

    @BeforeAll
    static void initialize(){
        try {
            stringTab = CsvFileReader.extractDatas("src/main/resources/data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        personParser = new PersonParser();
    }

    @Test
    void personModel(){
        Person person = new Person("GUYON", "clement.guyon@gmail.com", "SahyyKI0", "Clément", "0781915332");
        Assertions.assertInstanceOf(Person.class, person);
    }

    @Test
    void personParser(){
        List<Person> listResult = personParser.parse(
                new ArrayList<>()
                {
                    {
                        add(new String[]{ "Delbecq", "Adeline","RedWappin", "adeline.delbecq@gmail.com", "01.02.03.04.05"});
                        add(new String[]{"Guyon", "Clément", "SahyyKI0", "clement.guyon@gmail.com", "0781915332"});
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
        Assertions.assertInstanceOf(ArrayList.class, stringTab);
    }

    @Test
    void formatPhoneNumber(){
        Assertions.assertEquals("0781915332", PhoneNumberFormatter.format("+33-(7)-81-91-53-32"));
        Assertions.assertEquals("0781915332", PhoneNumberFormatter.format("+33-(7)-81-91-53-32"));
    }

    @Test
    void findDuplicate(){
        List<Person> listResult = personParser.parse(
                new ArrayList<>()
                {
                    {
                        add(new String[]{ "Delbecq", "Adeline","RedWappin", "adeline.delbecq@gfrmailll.com", "01.02.03.04.05"});
                        add(new String[]{ "Delbecq", "Adeline","RedWappin", "adeline.delbecq@gfrmailll.com", "01.02.03.04.05"});
                        add(new String[]{"Guyon", "Clément", "SahyyKI0", "clement.guyon@gmail.com", "0781915332"});
                        add(new String[]{"Guyon", "Clément", "SahyyKI0", "clement.guyon@gmail.com", "0781915332"});
                    }
                }
        );

        var listWithoutDuplicate = DuplicateFinder.eliminateDuplicate(listResult);
        Assertions.assertEquals(2, listWithoutDuplicate.size());
    }

    @Test
    void stringSimilarity(){
        var sim = StringSimilarity.similarity("eleonore_cote@example.com", "eleonorecote@example.net");
        System.out.println(sim);

        var similarity = StringSimilarity.similarity("AZERTYUIOP", "AQSDFGHJKL");
        Assertions.assertEquals(0.1, similarity);
    }
}
