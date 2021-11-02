import org.example.volunteers.model.Person;
import java.nio.file.NoSuchFileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.example.volunteers.utils.*;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class VolunteersTest {
    static List<String[]> stringTab;
    static PersonParser personParser;
    static Person person1;
    static Person person2;


    @BeforeAll
    static void initialize(){
        try {
            stringTab = CsvFileReader.extractDatas("src/main/resources/data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        personParser = new PersonParser();
    }

    @BeforeAll
    static void personModel(){
        person1 = new Person("GUYON", "clement.guyon@gmail.com", "SahyyKI0", "Clément", "0781915332");
        person2 = new Person("GUYON", "clement.guyon@gmail.com", "SahyyKI0", "", "0781915332");

        Assertions.assertInstanceOf(Person.class, person1);
        Assertions.assertInstanceOf(Person.class, person2);
    }

    @Test
    void personParser(){
        List<Person> listResult = personParser.parse(
                new ArrayList<>()
                {
                    {
                        add(new String[]{ "Delbecq", "Adeline","RedWappin", "adeline@gmail.com", "01.02.03.04.05"});
                    }
                }
        );
        Assertions.assertEquals("adeline", listResult.get(0).getSurname());// a multiplier
        Assertions.assertEquals("delbecq", listResult.get(0).getName());// a multiplier
        Assertions.assertEquals("0102030405", listResult.get(0).getPhoneNumber());// a multiplier
        Assertions.assertEquals("adeline@gmail.com", listResult.get(0).getEmail());// a multiplier
    }

    @Test
    void noSuchFileExceptionThrow(){
        Assertions.assertThrows(NoSuchFileException.class, () -> CsvFileReader.extractDatas("src/main/resources/azdazd.csv") );
    }

    @Test
    void formatPhoneNumber(){
        Assertions.assertEquals("0781915332", PhoneNumberFormatter.format("+33-(7)-81-91-53-32"));
        Assertions.assertEquals("0781915332", PhoneNumberFormatter.format("+33-(7)-81-91-53-32"));
    }

    @Test
    void findDuplicateDifference(){
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

        var listWithoutDuplicate = DuplicateFinder.eliminateDuplicate(listResult.stream().collect(Collectors.groupingBy(Person::getPhoneNumber)));

        Assertions.assertEquals("delbecq", listWithoutDuplicate.get(0).getName());
        Assertions.assertEquals("adeline", listWithoutDuplicate.get(0).getSurname());
        Assertions.assertEquals("guyon", listWithoutDuplicate.get(1).getName());
        Assertions.assertEquals("clément", listWithoutDuplicate.get(1).getSurname());
    }
    @Test
    void findDuplicateSame(){
        List<Person> listResult = personParser.parse(
                new ArrayList<>()
                {
                    {
                        add(new String[]{"Guyon", "Clément", "SahyyKI0", "clement.guyon@gmail.com", "0781915332"});
                        add(new String[]{"Guyon", "Clément", "SahyyKI0", "clement.guyon@gmail.com", "0781915332"});
                    }
                }
        );

        var listWithoutDuplicate = DuplicateFinder.eliminateDuplicate(listResult.stream().collect(Collectors.groupingBy(Person::getPhoneNumber)));

        Assertions.assertEquals("guyon", listWithoutDuplicate.get(0).getName());
        Assertions.assertEquals("clément", listWithoutDuplicate.get(0).getSurname());
    }

    @Test
    void stringSimilarity(){
        var sim = StringSimilarity.similarity("eleonore_cote@example.com", "eleonorecote@example.net");
        System.out.println(sim);

        var similarity = StringSimilarity.similarity("AZERTYUIOP", "AQSDFGHJKL");
        Assertions.assertEquals(0.1, similarity);


    }

    @Test
    void testSimilarityEmpty(){
        var similarity2 = StringSimilarity.similarity("", "");
        Assertions.assertEquals(1.0, similarity2);
    }

    @Test
    void testSimilarityDifference(){
        var similarity3 = StringSimilarity.similarity("azertyuiopqsdfghjklm", "wxcvbn,;:=");
        Assertions.assertEquals(0.0, similarity3);
    }

    @Test
    void testSimilarityHyphen(){
        var similarity6 = StringSimilarity.similarity("bonjour", "bonjour-");
        Assertions.assertEquals(0.875, similarity6);
    }

    @Test
    void testSimilarityMaj(){
        var similarity5 = StringSimilarity.similarity("cleBment", "clemBent");
        Assertions.assertEquals(0.75, similarity5);

        var similarity4 = StringSimilarity.similarity("clemBent", "clemBent");
        Assertions.assertEquals(1.0, similarity4);
    }

    @Test
    void testWhichPersonToDelete(){
        try {
            var personToDelete = DuplicateFinder.getDuplicateToChoose(person1, person2);
            Assertions.assertEquals(person2, personToDelete);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
