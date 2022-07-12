package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmailTest {
    @Test
    public void validEmailAddressShouldVeValidated() {
        String emailAddress = "adresse.mail@mail.com";
        boolean isEmail = Email.isValidEmail(emailAddress);
        assertTrue(isEmail, "Une adresse email valide est validée");
    }

    @Test
    public void invalidEmailShouldNotBeValidated() {
        String emailAddress = "adressemailmailcom";
        boolean isEmail = Email.isValidEmail(emailAddress);
        assertFalse(isEmail, "Une adresse email invalide n'est pas validée");
    }

    @Test
    public void addressCleanerShouldReplaceCorrectCharacters() {
        String toClean = "éèëêœàäâîïìûùüôòö";
        toClean = Email.cleanEmailAddress(toClean);
        assertEquals("eeeeoeaaaiiiuuuooo", toClean, "L'addresse email est nettoyée des caractères avec accents");
    }

    @Test
    public void addressCleanerShouldNotReplaceNormalCharacters() {
        String toClean = "eeeeoeaaaiiiuuuooo";
        toClean = Email.cleanEmailAddress(toClean);
        assertEquals("eeeeoeaaaiiiuuuooo", toClean, "L'addresse email n'est pas affectée si elle contient des caractères normaux");
    }

    @Test
    public void mailAddressCleanupShouldSetEmptyAddressIfInvalid() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Nom", "Prenom", "pseudo", "email@gmailcom", "+33600000000"));
        volunteers = Email.cleanupMailAddresses(volunteers);
        assertEquals("", volunteers.get(0).eMail, "L'adresse email invalide se retrouve vide");
    }

    @Test
    public void mailAddressCleanupShouldLowerCaseAddress() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Nom", "Prenom", "pseudo", "eMail@GMail.com", "+33600000000"));
        volunteers = Email.cleanupMailAddresses(volunteers);
        assertEquals("email@gmail.com", volunteers.get(0).eMail, "L'adresse email invalide se retrouve vide");
    }

    @Test
    public void mailAddressCleanupShouldReplaceAccentedCharacters() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Nom", "Prenom", "pseudo", "dédé-la-bagarre@gmail.com", "+33600000000"));
        volunteers = Email.cleanupMailAddresses(volunteers);
        assertEquals("dede-la-bagarre@gmail.com", volunteers.get(0).eMail, "L'adresse email contenant des caractères spéciaux est remplacée");
    }
}
