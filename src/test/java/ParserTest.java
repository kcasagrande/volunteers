import model.PersonProperties;
import org.junit.jupiter.api.Test;
import service.Parser;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

   private final Parser parser = new Parser();

    @Test
    public void testParseCsvFinishWithSuccess() throws IOException {
        List<Map<PersonProperties, String>> lines =  parser.parseCsv("src/test/resources/data.csv", ";");

        lines.forEach(line -> {
                assertEquals("Rébecca", line.get(PersonProperties.firstName));
                assertEquals("Compere", line.get(PersonProperties.lastName));
                assertEquals("rebeccacompere@example.org", line.get(PersonProperties.email));
                assertEquals("+33085552814", line.get(PersonProperties.phoneNumber));
                assertEquals("", line.get(PersonProperties.userName));
            }
        );
    }

    @Test
    public void testParseCsvWithIncorrectSeparator() throws IOException {
        List<Map<PersonProperties, String>> lines =  parser.parseCsv("src/test/resources/data.csv", "^");
        assertEquals(0, (long) lines.size());
    }
}
