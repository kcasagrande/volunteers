package com.volunters;
import org.example.volunteers.model.PersonProperties;
import org.junit.jupiter.api.Test;
import org.example.volunteers.service.Parser;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

   private final Parser parser = new Parser();

    @Test
    public void testParseCsvFinishWithSuccess() throws IOException {
        List<Map<PersonProperties, String>> lines = parser.parseCsv("src/test/resources/data.csv", ";");

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
        List<Map<PersonProperties, String>> lines = parser.parseCsv("src/test/resources/data.csv", "^");
        assertEquals(0, lines.size());
    }

    @Test
    public void testParseCsvWithIncorrectFilePath() throws IOException {
        List<Map<PersonProperties, String>> lines = parser.parseCsv("src/tests/resources/data.csv", ";");
        assertEquals(0, lines.size());
    }

    @Test
    public void testParseCsvWithEmptyFile() throws IOException {
        List<Map<PersonProperties, String>> lines = parser.parseCsv("src/test/resources/emptyData.csv", ";");
        assertEquals(0, lines.size());
    }

    @Test
    public void testParseCsvCantReturnSamePerson() throws IOException {
        List<Map<PersonProperties, String>> lines = parser.parseCsv("src/test/resources/sameData.csv", ";");
        assertEquals(1, lines.size());
    }
}
