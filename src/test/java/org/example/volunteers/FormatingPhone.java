package org.example.volunteers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatingPhone {

    @Test
    public void checkFormatPhoneSpace(){
        String textPhone = "+330 00 55 51 96";

        String expectedResult = "+33000555196";

        String actualResult = Cleaner.formatPhone(textPhone);

        assertEquals(expectedResult, actualResult, "Le numéro de téléphone ne doit pas avoir d'espace ( )");
    }

    @Test
    public void checkFormatPhoneDash(){
        String textPhone = "+330-00-55-51-96";

        String expectedResult = "+33000555196";

        String actualResult = Cleaner.formatPhone(textPhone);

        assertEquals(expectedResult, actualResult, "Le numéro de téléphone ne doit pas avoir de tiret (-)");
    }

    @Test
    public void checkFormatPhoneDot(){
        String textPhone = "+330.00.55.51.96";

        String expectedResult = "+33000555196";

        String actualResult = Cleaner.formatPhone(textPhone);

        assertEquals(expectedResult, actualResult, "Le numéro de téléphone ne doit pas avoir de point (.)");
    }

    @Test
    public void checkFormatPhoneParenthesis(){
        String textPhone = "+33(0)000555196";

        String expectedResult = "+33000555196";

        String actualResult = Cleaner.formatPhone(textPhone);

        assertEquals(expectedResult, actualResult, "Le numéro de téléphone ne doit pas avoir de parenthese ( () )");
    }

    @Test
    public void checkFormatPhoneCountryCode(){
        String textPhone = "0000555196";

        String expectedResult = "+33000555196";

        String actualResult = Cleaner.formatPhone(textPhone);

        assertEquals(expectedResult, actualResult, "Le numéro de téléphone doit contenir l'indicateur du pays (+33)");
    }
}
