import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevenshteinTest {
    private Levenshtein lv;

    @BeforeEach
    public void setUp() {
        lv = new Levenshtein();
    }

    @Test
    public void testLevensheinLd(){
        assertEquals(0,  lv.ld("kitten","kitten"));
        assertEquals(1,  lv.ld("kitten","sitten"));
        assertEquals(2,  lv.ld("kitten","sittes"));
        assertEquals(3,  lv.ld("kitten","sityteng"));
        assertEquals(4,  lv.ld("kitten","sittYing"));
        assertEquals(8,  lv.ld("rosettacode","raisethysword"));
        assertEquals(17, lv.ld("kitten","kittenaaaaaaaaaaaaaaaaa"));
        assertEquals(17, lv.ld("kittenaaaaaaaaaaaaaaaaa","kitten"));
    }
}
