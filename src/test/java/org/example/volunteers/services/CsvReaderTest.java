package org.example.volunteers.services;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvReaderTest {
    @Test
    public void TestAllLineAreImported() throws IOException {
        CsvReader reader = new CsvReader();

        List<String[]> data = reader.getLinesFromFile("src/test/resources/data-test.csv");

        assertEquals(4, data.size());
        assertEquals("+33(0)0 55 53 75 36", data.get(0)[4]);
        assertEquals("", data.get(3)[2]);
    }
}
