package org.example.utils;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LevenshteinTest
{
    @Test
    public void shouldHaveLevenshteinDistanceOfOne()
    {
        String first = "Miler";
        String second = "Miller";

        int distance = Levenshtein.calculate(first, second);
        assertEquals(1, distance);
    }

    @Test
    public void shouldHaveLevenshteinDistanceOfFirstStringLength()
    {
        String first = "Miler"; // length: 5
        String second = "";

        int distance = Levenshtein.calculate(first, second);
        assertEquals(first.length(), distance);
    }

    @Test
    public void shouldHaveLevenshteinDistanceOfSecondStringLength()
    {
        String first = "";
        String second = "Miller"; // length: 6

        int distance = Levenshtein.calculate(first, second);
        assertEquals(second.length(), distance);
    }

    @Test
    public void shouldHaveLevenshteinDistanceOfZero()
    {
        String first = "";
        String second = "";

        int distance = Levenshtein.calculate(first, second);
        assertEquals(0, distance);
    }

    @Test
    public void shouldHaveComplexStringLevenshteinDistance()
    {
        String first = "LOZl!&.$";
        String second = "Mi*ler";

        int distance = Levenshtein.calculate(first, second);
        assertEquals(7, distance);
    }
}
