package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CleanerTest {

    @Test
    public void ensureEmailAddressIsCorrected() {
        String invalidEmail = "fdpgmail.com";
        String expectedEmail = "fdp@gmail.com";

        String actualEmail = Cleaner.correctEmail(invalidEmail);

        assertEquals(expectedEmail, actualEmail, "Email shouldn't have @ in");
    }

    @Test
    public void ensurePhoneNumberIsValid(){
        String phone = "+33(0)000555196";
        String expectedPhoneNumber = "+330000555196";
        String actualPhoneNumber = Cleaner.formatPhoneNumber(phone);
        assertEquals(expectedPhoneNumber, actualPhoneNumber, "Le numéro de téléphone doit etre valide");
    }

    @Test
    public void getInvalidAddresses(){
        Volunteer vol1 = new Volunteer("Florian", "Mousset", "", "aa.com", "");
        Volunteer vol2 = new Volunteer("Gerard", "", "nrgix", "aa.com", "");
        Volunteer vol3 = new Volunteer("Antoine", "Mousset", "test", "uaai@aa.com", "123456890");
        List<Volunteer> volunteers = Arrays.asList(vol1, vol2, vol3);
        List<Volunteer> expectedVolunteer = Arrays.asList(vol1, vol2);
        List<Volunteer> invalidAddresses = Cleaner.getInvalidEmailAddresses(volunteers);
        assertEquals(expectedVolunteer, invalidAddresses, "Les adresses mails doivent être invalides");
    }

    @Test
    public void isUpperCaseAndLowerCase() {
        String name = "qfsdGGDFgee";
        Boolean toTitleCase = Cleaner.toTitleCase(name);
        assertTrue(toTitleCase, "Non valide");
    }

    @Test
    public void transformFirstLetterToUppercase() {
        String name = "mousset";
        String expectedResult = "Mousset";

        String actualResult = Cleaner.firstLetterToUppercase(name);
        assertEquals(expectedResult, actualResult, "La première lettre n'est pas minuscule");
    }
}
