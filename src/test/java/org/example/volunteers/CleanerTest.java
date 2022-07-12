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
        String emailAddress = "adresse.mail@mail.com";
        boolean isEmail = Cleaner.isValidEmail(emailAddress);
        assertTrue(isEmail, "Une adresse email valide est validée");
    }
    @Test
    public void invalidEmailShouldNotBeValidated() {
        String emailAddress = "adressemailmailcom";
        boolean isEmail = Cleaner.isValidEmail(emailAddress);
        assertFalse(isEmail, "Une adresse email invalide n'est pas validée");
    }
    @Test
    public void addressCleanerShouldReplaceCorrectCharacters() {
        String toClean = "éèëêœàäâîïìûùüôòö";
        toClean = Cleaner.cleanEmailAddress(toClean);
        assertEquals("eeeeoeaaaiiiuuuooo", toClean, "L'addresse email est nettoyée des caractères avec accents");
    }
    @Test
    public void addressCleanerShouldNotReplaceNormalCharacters() {
        String toClean = "eeeeoeaaaiiiuuuooo";
        toClean = Cleaner.cleanEmailAddress(toClean);
        assertEquals("eeeeoeaaaiiiuuuooo", toClean, "L'addresse email n'est pas affectée si elle contient des caractères normaux");
    }

    @Test
    public void shouldRemoveDuplicateWhenAllDataAreTheSame() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Nom", "Prenom", "pseudo", "email@gmail.com", "+33600000000"));
        volunteers.add(new Volunteer("Nom", "Prenom", "pseudo", "email@gmail.com", "+33600000000"));
        volunteers.add(new Volunteer("Nom2", "Prenom2", "pseudo2", "email2@gmail.com", "+33600000002"));
        volunteers.add(new Volunteer("Nom3", "Prenom3", "pseudo3", "email3@gmail.com", "+33600000003"));
        volunteers.add(new Volunteer("Nom2", "Prenom2", "pseudo2", "email2@gmail.com", "+33600000002"));

        List<Volunteer> cleanedVolunteers = Cleaner.removeDuplicate(volunteers);

        assertEquals(3, cleanedVolunteers.size(), "La liste nettoyée doit être de taille 3");
    }

    @Test
    public void mailAddressCleanupShouldSetEmptyAddressIfInvalid() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Nom", "Prenom", "pseudo", "email@gmailcom", "+33600000000"));
        volunteers = Cleaner.cleanupMailAddresses(volunteers);
        assertEquals("", volunteers.get(0).eMail, "L'adresse email invalide se retrouve vide");
    }
    @Test
    public void mailAddressCleanupShouldLowerCaseAddress() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Nom", "Prenom", "pseudo", "eMail@GMail.com", "+33600000000"));
        volunteers = Cleaner.cleanupMailAddresses(volunteers);
        assertEquals("email@gmail.com", volunteers.get(0).eMail, "L'adresse email invalide se retrouve vide");
    }
    @Test
    public void mailAddressCleanupShouldReplaceAccentedCharacters() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Nom", "Prenom", "pseudo", "dédé-la-bagarre@gmail.com", "+33600000000"));
        volunteers = Cleaner.cleanupMailAddresses(volunteers);
        assertEquals("dede-la-bagarre@gmail.com", volunteers.get(0).eMail, "L'adresse email contenant des caractères spéciaux est remplacée");
    }

//    @Test
//    public void testRemovedDuplicateVerifyFirstNameLastNameNicknamePseudoMailPhone() {
//
//        List<Volunteer> volunteers = new ArrayList<>();
//        volunteers.add(new Volunteer("doe", "john", "jojo2", "john@mail.com", "+33698675434"));
//        volunteers.add(new Volunteer("doe", "john", "jojo", "john@mail.com", "+33698675434"));
//        volunteers.add(new Volunteer("doe", "john", "jojo", "john@mail.com", "+33698675434"));
//
//        List<Volunteer> result = Cleaner.removeDuplicateFirstNameLastNamePseudoMailPhone(volunteers);
//
//        assertEquals(2, result.size());
//    }

}
