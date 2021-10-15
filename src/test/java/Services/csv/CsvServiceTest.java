package Services.csv;

import exceptions.CsvEmptyException;
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
            return;
        }
        catch (Exception exception) {
            fail();
        }
    }

    @Test
    void readAllLinesResultEmpty() throws IOException, CsvNotExistException, CsvEmptyException {
        csvService.setCsvPath("src/test/java/resources/dataEmpty.csv");

        try {
            csvService.readAllLines();
            fail();
        }
        catch (CsvEmptyException exception) {
            return;
        }
        catch (Exception exception) {
            fail();
        }
    }

    @Test
    void readAllLinesResultNotEmpty() throws IOException, CsvNotExistException, CsvEmptyException {
        csvService.setCsvPath("src/test/java/resources/dataNotEmpty.csv");

        try {
            csvService.readAllLines();
            return;
        }
        catch (CsvEmptyException exception) {
            fail();
        }
        catch (Exception exception) {
            fail();
        }
    }
}