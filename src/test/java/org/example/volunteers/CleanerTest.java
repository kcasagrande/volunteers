package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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



    @Test
    public void testRemovedDuplicateVerifyFirstNameLastNameNicknamePseudoMailPhone() {

        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("doe", "john", "jojo2", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("doe", "john", "jojo", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("doe", "john", "jojo", "john@mail.com", "+33698675434"));

        List<Volunteer> result = Cleaner.removeDuplicateFirstNameLastNamePseudoMailPhone(volunteers);

        assertEquals(2, result.size());
    }

}
