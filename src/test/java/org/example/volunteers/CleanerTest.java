package org.example.volunteers;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CleanerTest {

    @Test
    public void useNameAsNickname() {
        givenVolunteer(new Volunteer("Hugo", "Bordais", "", "hugo.bordais@cuck.com", "0700000000"));
        whenCleaningUpVolunteers();
        thenNickNameIs("Hugo");
    }

    @Test
    public void firstLetterOfFirstNameOrLastNameIsUppercase() {
        givenVolunteer(new Volunteer("hugo", "bordais", "", "hugo.bordais@cuck.com", "0700000000"));
        whenCleaningUpVolunteers();
        thenFirstNameIs("Hugo");
        thenLastNameIs("Bordais");
    }

    private void givenVolunteer(Volunteer volunteer) {
        volunteers.add(volunteer);
    }

    private void whenCleaningUpVolunteers() {
        cleanedVolunteers = Cleaner.cleanUp(volunteers);
    }

    private void thenVolunteerIs(String firstName, String lastName, String nickname, String eMail, String phone) {
        thenFirstNameIs(firstName);
        thenLastNameIs(lastName);
        thenNickNameIs(nickname);
        thenMailIs(eMail);
        thenPhoneIs(phone);
    }

    private void thenFirstNameIs(String firstName) {
        assertEquals(firstName, cleanedVolunteers.get(0).firstName);
    }

    private void thenLastNameIs(String lastName) {
        assertEquals(lastName, cleanedVolunteers.get(0).lastName);
    }

    private void thenNickNameIs(String nickname) {
        assertEquals(nickname, cleanedVolunteers.get(0).nickName);
    }

    private void thenMailIs(String eMail) {
        assertEquals(eMail, cleanedVolunteers.get(0).eMail);
    }

    private void thenPhoneIs(String phone) {
        assertEquals(phone, cleanedVolunteers.get(0).phone);
    }

    private final List<Volunteer> volunteers = new ArrayList<Volunteer>();
    private List<Volunteer> cleanedVolunteers = new ArrayList<Volunteer>();
}
