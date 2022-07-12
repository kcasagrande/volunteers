package org.example.volunteers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneTest {
    @Test
    public void phoneValidOne() {
        String input = "0601765381";
        String result = Cleaner.phoneNumberFormatter(input);
        assertEquals("+33601765381",result, "Numéro de téléphone sans +33, retourne le numéro sans le 0 et avec le +33");
    }

    @Test
    public void phoneValidTwo() {
        String input = "+33(0)6-75-55-55-20";
        String result = Cleaner.phoneNumberFormatter(input);
        assertEquals("+33675555520",result,"Le numéro n'est pas au bon format, on renvoit le numéro re-formaté sans le (0) et les tirets");
    }

    @Test
    public void phoneValidThree() {
        String input = "+336.55.50.61.99";
        String result = Cleaner.phoneNumberFormatter(input);
        assertEquals("+33655506199",result,"Le numéro n'est pas au bon format, on renvoit le numéro re-formaté sans les points");
    }

    @Test
    public void phoneValidFour() {
        String input = "+33675558829";
        String result = Cleaner.phoneNumberFormatter(input);
        assertEquals("+33675558829",result,"Numéro déja formater, doit renvoyé le numéro");
    }

    @Test
    public void phoneValidFive() {
        String input = "06-35-55-85-21";
        String result = Cleaner.phoneNumberFormatter(input);
        assertEquals("+33635558521",result, "Numéro  formatable, doit renvoyé le numéro formaté");
    }

    @Test
    public void phoneValidSix() {
        String input = "+33(0)6.00.55.58.37";
        String result = Cleaner.phoneNumberFormatter(input);
        assertEquals("+33600555837",result,"Le numéro n'est pas au bon format, on renvoit le numéro re-formaté sans le (0) et les points");
    }

    @Test
    public void phoneValidSeven() {
        String input = "+336 00 55 57 66";
        String result = Cleaner.phoneNumberFormatter(input);
        assertEquals("+33600555766",result, "Le numéro n'est pas au bon format, on renvoit le numéro re-formaté sans les espaces");
    }

    @Test
    public void phoneValidEight() {
        String input = "+33(0)6..00  55.58.37";
        String result = Cleaner.phoneNumberFormatter(input);
        assertEquals("+33600555837",result,"Le numéro n'est pas au bon format, on renvoit le numéro re-formaté sans les points et les espaces");
    }

    @Test
    public void phoneValidErrorTooLong() {
        String input = "+33(0)6.00.55.58.37.8";
        String result = Cleaner.phoneNumberFormatter(input);
        assertEquals("Null",result, "Numéro non formatable, doit renvoyé null");
    }

    @Test
    public void phoneValidErrorTooShort() {
        String input = "+336.00.55.58.3";
        String result = Cleaner.phoneNumberFormatter(input);
        assertEquals("Null",result, "Numéro non formatable, doit renvoyé null");
    }

    @Test
    public void phoneValidErrorSpecialChar() {
        String input = "+33/0)6.00.55.58.37";
        String result = Cleaner.phoneNumberFormatter(input);
        assertEquals("+33600555837", result, "Numéro comprenant des caractéres spéciaux(/), doit renvoyé le numéro formaté");
    }
}
