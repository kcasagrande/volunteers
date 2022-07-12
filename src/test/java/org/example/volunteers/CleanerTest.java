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
    public void removeDuplicateVerifyFirstNameLastNameNicknamePseudoMailPhone() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("john", "doe", "jojo2", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675434"));

        List<Volunteer> result = Cleaner.removeDuplicateFirstNameLastNamePseudoMailPhone(volunteers);

        assertEquals(2, result.size(), "La liste ne doit pas garder le doublon avec le nickName jojo car ils ont des données exactement similaires");
    }

    @Test
    public void removeDuplicateVerifyFirstNameInsteadOfLastName() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("john", "doe", "jojo2", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("doe", "john", "jojo", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675434"));

        List<Volunteer> result = Cleaner.removeDuplicateFirstNameLastNamePseudoMailPhone(volunteers);

        assertEquals(2, result.size(), "La liste ne doit pas garder le doublon avec le nickName jojo car ils ont des données similaires avec leur nom/prénom inversés");
    }

    @Test
    public void removeDuplicateVerifyMailPhone() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("john", "doe", "jojo2", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675439"));

        List<Volunteer> result = Cleaner.removeDuplicateMailPhone(volunteers);

        assertEquals(2, result.size(), "La liste ne doit pas garder le doublon sur le téléphone +33698675434 car le numéro de téléphone est similaire");
    }
    @Test
    public void removeSpecialCharacters() {
        List<Volunteer> volunteersA = new ArrayList<>();
        volunteersA.add(new Volunteer("Éric", "Doé", "jojo", "john@mail.com", "+33698675434"));

        List<Volunteer> result = Cleaner.removeSpecialCharacters(volunteersA);

        assertEquals(result.get(0).firstName, "Eric", "Les accents doivent être remplacés par des caractères classiques");
        assertEquals(result.get(0).lastName, "Doe", "Les accents doivent être remplacés par des caractères classiques");
    }
    @Test
    public void EmailInsteadOfPhone() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("doe", "john", "jojo2", "+33698675434", "john@mail.com"));
        volunteers.add(new Volunteer("doe", "john", "jojo2", "john@mail.com", "+33698675487"));

        List<Volunteer> result = Cleaner.sanitizeEmailInsteadOfPhone(volunteers);

        List<Volunteer> resultExpected = new ArrayList<>();
        resultExpected.add(new Volunteer("doe", "john", "jojo2", "john@mail.com", "+33698675434"));
        resultExpected.add(new Volunteer("doe", "john", "jojo2", "john@mail.com", "+33698675487"));

        assertEquals(resultExpected.toString(), result.toString());
    }
}
