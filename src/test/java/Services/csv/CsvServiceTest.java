package Services.csv;

import exceptions.CsvConversionToUserException;
import exceptions.CsvEmptyException;
import exceptions.CsvNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.User;

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
        } catch (CsvNotExistException exception) {
            fail();
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    void isCsvNotExistsTest() {
        csvService.setCsvPath("src/test/java/resources/dataNotExist.csv");

        try {
            csvService.isCsvExists();
            fail();
        } catch (CsvNotExistException exception) {
            return;
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    void readAllLinesResultEmpty() {
        csvService.setCsvPath("src/test/java/resources/dataEmpty.csv");

        try {
            csvService.readAllLines();
            fail();
        } catch (CsvEmptyException exception) {
            return;
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    void readAllLinesResultNotEmpty() {
        csvService.setCsvPath("src/test/java/resources/dataNotEmpty.csv");

        try {
            csvService.readAllLines();
            return;
        } catch (CsvEmptyException exception) {
            fail();
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    void convertListUserTestSuccessful() throws CsvNotExistException, IOException, CsvEmptyException {
        csvService.setCsvPath("src/test/java/resources/dataInsertForAUser.csv");

        List<String[]> linesString = csvService.readAllLines();

        List<User> linesUsers = csvService.convertListUser(linesString);
        User user;
        String[] userString;
        boolean result = true;

        for(int i = 0;linesUsers.size() <= i; i++)
        {
            user = linesUsers.get(i);
            userString = linesString.get(i);

            if(!user.getLastName().equals(userString[0]) ||
                !user.getFirstName().equals(userString[1]) ||
                !user.getUserName().equals(userString[2]) ||
                !user.getEmail().equals(userString[3]) ||
                !user.getPhone().equals(userString[4]))
            {
                result = false;
            }

            i++;
        }

        assertTrue(result);
    }


    @Test
    void convertListUserTestFailed() throws CsvNotExistException, IOException, CsvEmptyException {
        csvService.setCsvPath("src/test/java/resources/dataInsertForAUser.csv");

        List<String[]> linesString = csvService.readAllLines();

        List<User> linesUsers = csvService.convertListUser(linesString);
        User user;
        String[] userString;
        boolean result = true;

        for(int i = 0;linesUsers.size() <= i; i++)
        {
            user = linesUsers.get(i);
            userString = linesString.get(i);

            if(!user.getLastName().equals(userString[0]) ||
                    !user.getFirstName().equals(userString[1]) ||
                    !user.getUserName().equals(userString[2]) ||
                    !user.getEmail().equals(userString[3]) ||
                    !user.getPhone().equals(userString[4]))
            {
                result = false;
            }

            i++;
        }

        assertTrue(result);
    }
}