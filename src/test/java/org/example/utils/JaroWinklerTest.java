package org.example.utils;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class JaroWinklerTest
{
    @Test
    public void shouldHaveJaroWinklerOfOne()
    {
        String first = "Miler";
        String second = "Miler";

        double distance = JaroWinkler.compute(first, second);
        assertEquals(1.0, distance);
    }

    @Test
    public void shouldHaveJaroWinklerOfZeroEmptyFirstString()
    {
        String first = "";
        String second = "Miler";

        double distance = JaroWinkler.compute(first, second);
        assertEquals(0.0, distance);
    }

    @Test
    public void shouldHaveJaroWinklerOfZeroEmptyStrings()
    {
        String first = "";
        String second = "";

        double distance = JaroWinkler.compute(first, second);
        assertEquals(0.0, distance);
    }

    @Test
    public void shouldHaveHighJaroWinklerCoefficient()
    {
        String first = "Miler";
        String second = "MiLer";

        double distance = JaroWinkler.compute(first, second);
        assertEquals(0.91, Math.round(distance * 100.0) / 100.0);
    }
}
