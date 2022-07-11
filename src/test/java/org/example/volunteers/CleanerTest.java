package org.example.volunteers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CleanerTest {
    @Test
    public void validEmailAddressShouldVeValidated() {
        Volunteer volunteer = new Volunteer("firstName", "lastName", "nickname", "adresse.mail@mail.com", "123456");
        boolean isEmail = Cleaner.isValidEmail(volunteer);
        assertTrue(isEmail, "Une adresse email valide est validée");
    }
    @Test
    public void invalidEmailShouldNotBeValidated() {
        Volunteer volunteer = new Volunteer("firstName", "lastName", "nickname", "adresse.mailmailcom", "123456");
        boolean isEmail = Cleaner.isValidEmail(volunteer);
        assertFalse(isEmail, "Une adresse email invalide n'est pas validée");
    }
}
