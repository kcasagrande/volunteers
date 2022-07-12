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
    @Test
    public void checkFormatTextFromMultipleNames(){
        String testText = "De ville pin";

        String expectedResult = "De Ville Pin";

        String actualResult = Cleaner.formatText(testText);

        assertEquals(expectedResult, actualResult, "Le texte doit retourner une majuscule en premier caractère puis des minuscules pour chaque nom");
    }
    @Test
    public void checkFormatTextWithDash(){
        String testText = "Jean-eudes";

        String expectedResult = "Jean-Eudes";

        String actualResult = Cleaner.formatText(testText);

        assertEquals(expectedResult, actualResult, "Le texte doit retourner une majuscule en premier caractère puis des minuscules pour chaque nom séparés par des tirets");
    }
    @Test
    public void checkFormatTextWithApostrophe(){
        String testText = "D'l'arcours";

        String expectedResult = "D'L'Arcours";

        String actualResult = Cleaner.formatText(testText);

        assertEquals(expectedResult, actualResult, "Le texte doit retourner une majuscule en premier caractère puis des minuscules pour chaque nom séparés par des apostrophes");
    }
}
