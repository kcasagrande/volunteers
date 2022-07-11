package org.example.volunteers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatingText {

    @Test
    public void checkFormatTextFromLowercase(){
        String testText = "bonjour";

        String expectedResult = "Bonjour";

        String actualResult = Cleaner.formatText(testText);

        assertEquals(expectedResult, actualResult, "Le texte doit retourner une majuscule en premier caractère puis des minuscules");
    }

    @Test
    public void checkFormatTextFromUppercase(){
        String testText = "BONJOUR";

        String expectedResult = "Bonjour";

        String actualResult = Cleaner.formatText(testText);

        assertEquals(expectedResult, actualResult, "Le texte doit retourner une majuscule en premier caractère puis des minuscules");
    }

    @Test
    public void checkFormatTextFromMixedCase(){
        String testText = "BoNjOuR";

        String expectedResult = "Bonjour";

        String actualResult = Cleaner.formatText(testText);

        assertEquals(expectedResult, actualResult, "Le texte doit retourner une majuscule en premier caractère puis des minuscules");
    }
}
