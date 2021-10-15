package Services.csv;

import exceptions.CsvNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class CsvServiceTest {
    private CsvService csvService;

    @BeforeEach
    public void setUp() {
        csvService = new CsvService("src/main/resources/data.csv");
    }

    @Test
    void isCsvExistsTest() {
        csvService.setCsvPath("src/main/resources/data.csv");

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
        csvService.setCsvPath("src/main/resources/dataNotExist.csv");

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
}