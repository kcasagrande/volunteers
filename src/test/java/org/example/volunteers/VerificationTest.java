package org.example.volunteers;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class VerificationTest {

    public Verification verif = new Verification();
    @Test
    public void testNumberParam() {

        String[] line = "lafromboise;romaine;Banditto;Banditto7416@example.com;+33055520502".split(";");

        int num = verif.NumberParam(line);
        assertEquals(num, 5, "Ce message s'affiche si le testNumberParam échoue");
    }

    @Test
    public void testFusionLineUnique() throws IOException {

        List<String[]> lines = Files.readAllLines(Paths.get("src/main/resources/TestdataFusion.csv"))
                .stream().map(string -> string.split(";", -1))
                .collect(toList());
        String[] finalLine = verif.FusionLineUnique(lines);
        assertEquals(finalLine, "lafromboise;romaine;Banditto;Banditto7416@example.com;+33055520502", "Ce message s'affiche si le testNumberParam échoue");
    }
}