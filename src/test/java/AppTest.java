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

    public void testActualAndExpected(List<String[]> actualList, List<String[]> expectedList){
        for(int i=0; i < actualList.size(); i++){
            for(int j=0; j < actualList.get(i).length; j++){
                assertEquals(actualList.get(i)[j], expectedList.get(i)[j]);
            }
        }
    }

    @BeforeEach
    public void setUp() {
        app = new App();
    }

    @Test
    public void entriesMustBeGroupedByLetter() {
        List<String[]> actualLines = new ArrayList<>();
        actualLines.add(new String[]{"B", "", "", "", ""});
        actualLines.add(new String[]{"A", "", "", "", ""});
        actualLines.add(new String[]{"b", "", "", "", ""});
        actualLines.add(new String[]{"a", "", "", "", ""});

        List<String[]> expectedLines = new ArrayList<>();
        expectedLines.add(new String[]{"A", "", "", "", ""});
        expectedLines.add(new String[]{"a", "", "", "", ""});
        expectedLines.add(new String[]{"B", "", "", "", ""});
        expectedLines.add(new String[]{"b", "", "", "", ""});

        actualLines = this.app.groupedInput(actualLines);

        this.testActualAndExpected(actualLines, expectedLines);

    }

    @Test
    public void entriesMustBeGroupedByLetterByNumber() {
        List<String[]> actualLines = new ArrayList<>();
        actualLines.add(new String[]{"A", "", "", "", "1"});
        actualLines.add(new String[]{"Aa", "", "", "", "1"});
        actualLines.add(new String[]{"a", "", "", "", "1"});
        actualLines.add(new String[]{"aA", "", "", "", "2"});
        actualLines.add(new String[]{"a", "", "", "", "2"});


        List<String[]> expectedLines = new ArrayList<>();
        expectedLines.add(new String[]{"A", "", "", "", "1"});
        expectedLines.add(new String[]{"a", "", "", "", "1"});
        expectedLines.add(new String[]{"a", "", "", "", "2"});
        expectedLines.add(new String[]{"Aa", "", "", "", "1"});
        expectedLines.add(new String[]{"aA", "", "", "", "2"});


        actualLines = this.app.groupedInput(actualLines);

        this.testActualAndExpected(actualLines, expectedLines);

    }

    @Test
    public void entriesMustBeGroupedAll() {
        List<String[]> actualLines = new ArrayList<>();
        actualLines.add(new String[]{"b", "", "", "", "1"});
        actualLines.add(new String[]{"A", "", "", "", "1"});
        actualLines.add(new String[]{"cC", "", "", "", "2"});
        actualLines.add(new String[]{"a", "", "", "", "1"});
        actualLines.add(new String[]{"b", "", "", "", "2"});
        actualLines.add(new String[]{"c", "", "", "", "2"});
        actualLines.add(new String[]{"Cc", "", "", "", "2"});
        actualLines.add(new String[]{"B", "", "", "", "2"});

        List<String[]> expectedLines = new ArrayList<>();
        expectedLines.add(new String[]{"A", "", "", "", "1"});
        expectedLines.add(new String[]{"a", "", "", "", "1"});
        expectedLines.add(new String[]{"b", "", "", "", "1"});
        expectedLines.add(new String[]{"b", "", "", "", "2"});
        expectedLines.add(new String[]{"B", "", "", "", "2"});
        expectedLines.add(new String[]{"c", "", "", "", "2"});
        expectedLines.add(new String[]{"Cc", "", "", "", "2"});
        expectedLines.add(new String[]{"cC", "", "", "", "2"});

        actualLines = this.app.groupedInput(actualLines);

        this.testActualAndExpected(actualLines, expectedLines);

    }

//    @Test
//    public void entriesMustBeFused() {
//        List<String[]> actualLines = new ArrayList<>();
//        actualLines.add(new String[]{"A", "", "", "", "1"});
//        actualLines.add(new String[]{"a", "", "", "", "1"});
//        actualLines.add(new String[]{"a", "", "", "", "2"});
//
//        List<String[]> expectedLines = new ArrayList<>();
//        expectedLines.add(new String[]{"A", "", "", "", "1"});
//        expectedLines.add(new String[]{"a", "", "", "", "2"});
//
//        actualLines = this.app.fusedInput(actualLines);
//        this.testActualAndExpected(actualLines, expectedLines);
//    }
}
