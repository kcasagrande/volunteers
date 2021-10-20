import org.example.volunteers.Demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

    private App app;

    @BeforeEach
    public void setUp() {
        app = new App();
    }

    @Test
    public void splitFullCsvStringToStringArray() {
        String actualFullRow = "Simon;Marina;Marina;marina.simon@example.net;+33065557043";
        String[] actualStringArray = this.app.splitCSV.apply(actualFullRow);

        String[] expectedStringArray = new String[]{"Simon", "Marina", "Marina", "marina.simon@example.net", "+33065557043"};

        for(int i = 0; i < actualStringArray.length; i++){
            assertEquals(actualStringArray[i], expectedStringArray[i]);
        }

    }

    @Test
    public void splitEmptyCsvStringToStringArray() {
        String actualFullRow = ";;;;";
        String[] actualStringArray = this.app.splitCSV.apply(actualFullRow);

        String[] expectedStringArray = new String[]{"", "", "", "", ""};

        for(int i = 0; i < actualStringArray.length; i++){
            assertEquals(actualStringArray[i], expectedStringArray[i]);
        }

    }

    @Test
    public void entryBecomeUser() {
        String[] line = new String[]{"Simon", "Marina", "Marina", "marina.simon@example.net", "+33065557043"};

        User user = this.app.createUserFromLine(line);

        assertEquals(user.lastname, "Simon");
        assertEquals(user.firstname, "Marina");
        assertEquals(user.username, "Marina");
        assertEquals(user.email, "marina.simon@example.net");
        assertEquals(user.phone, "+33065557043");

    }

    @Test
    public void userBecomeRow() {
        User user = new User("Simon", "Marina", "Marina", "marina.simon@example.net", "+33065557043");

        String[] actualRow = user.getRow();
        String[] expectedRow = new String[]{"Simon", "Marina", "Marina", "marina.simon@example.net", "+33065557043"};

        for(int i=0; i < actualRow.length; i++){
            assertEquals(actualRow[i], expectedRow[i]);
        }
    }

    @Test
    public void userToString(){
        String expected_full_row = "Simon;Marina;Marina;marina.simon@example.net;+33065557043";
    }

    @Test
    public void identicalEntriesGroupByLastname() {
//        List<String[]> actualLines = new ArrayList<>();
//        actualLines.add(new String[]{"a", "", "a", "", "a"});
//        actualLines.add(new String[]{"a", "a", "", "a", ""});
//
//        List<String[]> expectedLines = new ArrayList<>();
//        expectedLines.add(new String[]{"a", "a", "a", "a", "a"});
//
//        actualLines = this.app.groupByLastname(actualLines);
//
//        this.testActualAndExpected(actualLines, expectedLines);

    }
}
