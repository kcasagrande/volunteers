package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailCleanerTest {

    @Test
    public void removeEmailAddressIfNoSymbol() {
        givenVolunteer("test", "test", "test", "test.test", "");
        whenCleaningUpVolunteers();
        thenMailIs("");
    }

    @Test
    public void removeEmailAdressIfNoDomain() {
        givenVolunteer("test", "test", "test", "test@", "");
        whenCleaningUpVolunteers();
        thenMailIs("");
    }

    @Test
    public void removeEmailAddressIfNoLocalAddress() {
        givenVolunteer("test", "test", "test", "@test.com", "");
        whenCleaningUpVolunteers();
        thenMailIs("");
    }

    @Test
    public void removeEmailAddressIfNoASCIICharacterInLocalAddress() {
        givenVolunteer("test", "test", "test", "te*st@test.com.", "");
        whenCleaningUpVolunteers();
        thenMailIs("");
    }

    @Test
    public void removeEmailAddressIfNoASCIICharacterInLocalAddressOneOrMultipleTime() {
        givenVolunteer("test", "test", "test", "t*e*st@test.com.", "");
        whenCleaningUpVolunteers();
        thenMailIs("");
    }

    @Test
    public void removeEmailAddressIfNoASCIICharacterInDomainAddress() {
        givenVolunteer("test", "test", "test", "test@tes*t.com.", "");
        whenCleaningUpVolunteers();
        thenMailIs("");
    }

    @Test
    public void removeEmailAddressIfNoTLD() {
        givenVolunteer("test", "test", "test", "test@test", "");
        whenCleaningUpVolunteers();
        thenMailIs("");
    }

    @Test
    public void removeEmailAddressIfNoTLD2() {
        givenVolunteer("test", "test", "test", "test@test.", "");
        whenCleaningUpVolunteers();
        thenMailIs("");
    }

    @Test
    public void removeDotIfDotAtEndOfMail() {
        givenVolunteer("test", "test", "test", "test@test.com.", "");
        whenCleaningUpVolunteers();
        thenMailIs("test@test.com");
    }

    @Test
    public void validateEmailAddressWithUnderscoreCharacters() {
        givenVolunteer("test", "test", "test", "test-test@test.com", "");
        whenCleaningUpVolunteers();
        thenMailIs("test-test@test.com");
    }

    @Test
    public void validateEmailAddress() {
        givenVolunteer("test", "test", "test", "test@test.com", "");
        whenCleaningUpVolunteers();
        thenMailIs("test@test.com");
    }

    @Test
    public void validateEmailAddressWithUppercaseLetter() {
        givenVolunteer("test", "test", "test", "Test@test.com.", "");
        whenCleaningUpVolunteers();
        thenMailIs("Test@test.com");
    }

    @Test
    public void validateEmailUnderscoreInDomainAddress() {
        givenVolunteer("test", "test", "test", "test@test-test.com.", "");
        whenCleaningUpVolunteers();
        thenMailIs("test@test-test.com");
    }

    @Test
    public void doesNotChangeEmailIfEmpty() {
        givenVolunteer("test", "test", "test", "", "");
        whenCleaningUpVolunteers();
        thenMailIs("");
    }

    private void givenVolunteer(String firstName, String lastName, String nickname, String eMail, String phone) {
        volunteers.add(new Volunteer(firstName, lastName, nickname, eMail, phone));
    }
    private void whenCleaningUpVolunteers() {
        cleanedVolunteers = Cleaner.cleanUp(volunteers);
    }
    private void thenMailIs(String eMail) {
        assertEquals(eMail, cleanedVolunteers.get(0).eMail);
    }
    private List<Volunteer> volunteers = new ArrayList<Volunteer>();
    private List<Volunteer> cleanedVolunteers = new ArrayList<Volunteer>();
}
