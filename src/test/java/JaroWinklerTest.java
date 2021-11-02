import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JaroWinklerTest {
    private JaroWinkler jw;

    @BeforeEach
    public void setUp() {
        jw = new JaroWinkler();
    }

    @Test
    public void testLevensheinLd(){
        assertEquals(0.0, jw.jaroWinklerDistance("abcde", "abcde"));
    }

    @Test
    public void testLevensheinLdAccomodate(){
        assertEquals(0.0182, jw.jaroWinklerDistance("accomodate", "accommodate"));
        assertEquals(0.1045, jw.jaroWinklerDistance("accomodate", "accordant"));
        assertEquals(0.1136, jw.jaroWinklerDistance("accomodate", "accolade"));
        assertEquals(0.1328, jw.jaroWinklerDistance("accomodate", "accompanist"));
        assertEquals(0.1334, jw.jaroWinklerDistance("accomodate", "accost"));
    }
}
