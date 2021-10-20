import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertEquals(5, actualStringArray.length);
        for(int i = 0; i < actualStringArray.length; i++){
            assertEquals(expectedStringArray[i], actualStringArray[i]);
        }
    }

    @Test
    public void splitEmptyCsvStringToStringArray() {
        String actualFullRow = ";;;;";
        String[] actualStringArray = this.app.splitCSV.apply(actualFullRow);

        String[] expectedStringArray = new String[]{"", "", "", "", ""};

        assertEquals(5, actualStringArray.length);
        for(int i = 0; i < expectedStringArray.length; i++){
            assertEquals(expectedStringArray[i], actualStringArray[i]);
        }
    }

}
