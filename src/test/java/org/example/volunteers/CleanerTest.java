package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        volunteers.add(new Volunteer("john", "doe", "jojo2", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675434"));

        List<Volunteer> result = Cleaner.removeDuplicateFirstNameLastNamePseudoMailPhone(volunteers);

        assertEquals(2, result.size(), "La liste ne doit pas garder le doublon avec le nickName jojo");
    }

    @Test
    public void testRemovedDuplicateVerifyMailPhone() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("john", "doe", "jojo2", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675439"));

        List<Volunteer> result = Cleaner.removeDuplicateMailPhone(volunteers);

        assertEquals(2, result.size(), "La liste ne doit pas garder le doublon sur le téléphone +33698675434");
    }

    @Test
    public void removeSpecialCharacters() {
        List<Volunteer> volunteersA = new ArrayList<>();
        volunteersA.add(new Volunteer("john'doe", "doé", "jojo", "john@mail.com", "+33698675434"));
        volunteersA.add(new Volunteer("john-doe", "doe", "jojo", "john@mail.com", "+33698675434"));

        List<Volunteer> result = Cleaner.removeSpecialCharacters(volunteersA);

        assertEquals(result.get(0).firstName, "john doe", "Les apostrophes doivent être supprimés");
        assertEquals(result.get(0).lastName, "doe", "Les accents doivent être remplacés par des caractères classiques");
        assertEquals(result.get(1).firstName, "john doe", "Les tirets doivent être supprimés");
    }

    @Test
    public void testEmailInsteadOfPhone() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("doe", "john", "jojo2", "+33698675434", "john@mail.com"));
        //volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675434"));
        //volunteers.add(new Volunteer("doe", "john", "jojo", "+33698675439", "john@mail.com"));

        List<Volunteer> result = Cleaner.sanitizeEmailInsteadOfPhone(volunteers);

        List<Volunteer> resultExpected = Cleaner.sanitizeEmailInsteadOfPhone(volunteers);
        volunteers.add(new Volunteer("doe", "john", "jojo2", "john@mail.com", "+33698675434"));

        assertEquals(resultExpected, result.size());
    }
}
