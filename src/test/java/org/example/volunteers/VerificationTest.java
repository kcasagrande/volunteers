package org.example.volunteers;

import org.junit.jupiter.api.Test;
import org.example.volunteers.Verification;
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
    public void testFusionLineUnique() {

        String[] line = "lafromboise;romaine;Banditto;Banditto7416@example.com;+33055520502".split(";");
        int num = verif.FusionLineUnique(line);
        assertEquals(num, 5, "Ce message s'affiche si le testNumberParam échoue");
    }
}