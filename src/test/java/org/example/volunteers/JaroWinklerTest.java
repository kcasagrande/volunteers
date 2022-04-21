package org.example.volunteers;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JaroWinklerTest {
    @Test
    public void testLevensheinLd() {
        assertEquals(1.0, JaroWinklerDistance.compute("abcde", "abcde"));
    }

    @Test
    public void testLevensheinLdAccomodate() {
        assertEquals(0.5, JaroWinklerDistance.compute("accomodate", "accommodate"));
        assertEquals(0.6, JaroWinklerDistance.compute("accomodate", "accordant"));
        assertEquals(0.6, JaroWinklerDistance.compute("accomodate", "accolade"));
        assertEquals(0.6, JaroWinklerDistance.compute("accomodate", "accompanist"));
        assertEquals(0.7, JaroWinklerDistance.compute("accomodate", "accost"));
        assertEquals(0.9, JaroWinklerDistance.compute("accomodate", "accomodsme"));
        assertEquals(0.0, JaroWinklerDistance.compute("accomodate", "collable"));
    }
}
