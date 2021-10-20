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

    public void testActualAndExpected(List<String[]> actualList, List<String[]> expectedList) {
        for (int i = 0; i < actualList.size(); i++) {
            for (int j = 0; j < actualList.get(i).length; j++) {
                assertEquals(actualList.get(i)[j], expectedList.get(i)[j]);
            }
        }
    }

    @BeforeEach
    public void setUp() {
        app = new App();
    }

    @Test
    public void entryBecomeUser() {
        String[] line = new String[]{"Simon", "Marina", "Marina", "marina.simon@example.net", "+33065557043"};
        assertTrue(this.app.createUserFromLine(line) instanceof User);
    }

    @Test
    public void identicalEntriesGroupByLastname() {
        List<String[]> actualLines = new ArrayList<>();
        actualLines.add(new String[]{"a", "", "a", "", "a"});
        actualLines.add(new String[]{"a", "a", "", "a", ""});

        List<String[]> expectedLines = new ArrayList<>();
        expectedLines.add(new String[]{"a", "a", "a", "a", "a"});

        actualLines = this.app.groupByLastname(actualLines);

        this.testActualAndExpected(actualLines, expectedLines);

    }
}
