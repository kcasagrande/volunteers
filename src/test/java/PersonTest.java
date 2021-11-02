import model.PersonProperties;
import org.junit.jupiter.api.Test;
import service.Parser;
import service.PersonService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PersonTest {

    private final PersonService personService = new PersonService();
    private final Parser parser = new Parser();

    @Test
    public void testListPersonWDuplicate() throws IOException {
        //List<Map<PersonProperties, String>> parsedFile = parser.parseCsv("src/main/resources/data.csv",";");
        //List<Person> person = personService.getListPersonWDuplicate(parsedFile);
        //System.out.println();
    }
}
