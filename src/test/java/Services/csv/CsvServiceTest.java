package Services.csv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class CsvServiceTest {
    private CsvService csvService;

    @BeforeEach
    public void setUp() {
        csvService = new CsvService("src/main/resources/data.csv");
    }

    @Test
    void isCsvExistsTest() throws IOException {
        Path csvPath = Paths.get("src/main/resources/data.csv");
        assertTrue(Files.exists(csvPath));
    }
    @Test
    void isCsvNotExistsTest() throws IOException {
        Path csvPath = Paths.get("src/main/resources/dataNotExist.csv");
        assertFalse(Files.exists(csvPath));
    }
}