package Services.csv;

import exceptions.CsvNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvServiceTest {
    private CsvService csvService;

    @BeforeEach
    public void setUp() {
        csvService = new CsvService("src/test/java/resources/data.csv");
    }

    @Test
    void isCsvExistsTest() {
        try {
            csvService.isCsvExists();
            return;
        }
        catch (CsvNotExistException exception) {
            System.out.println(exception);
            fail();
        }
        catch (Exception exception) {
            fail();
        }
    }
    @Test
    void isCsvNotExistsTest() throws IOException {
        csvService.setCsvPath("src/test/java/resources/dataNotExist.csv");

        try {
            csvService.isCsvExists();
            fail();
        }
        catch (CsvNotExistException exception) {
            System.out.println(exception);
            return;
        }
        catch (Exception exception) {
            fail();
        }
    }

    @Test
    void readAllLinesResultEmpty() throws IOException, CsvNotExistException {
        csvService.setCsvPath("src/test/java/resources/dataEmpty.csv");
        List<String[]> lines = csvService.readAllLines();

        assertEquals(lines.size(), 0);
    }
    @Test
    void readAllLinesResultNotEmpty() throws IOException, CsvNotExistException {
        csvService.setCsvPath("src/test/java/resources/dataNotEmpty.csv");
        List<String[]> lines = csvService.readAllLines();

        assertNotEquals(lines.size(), 0);
    }
}