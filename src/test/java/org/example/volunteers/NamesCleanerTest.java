package org.example.volunteers;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NamesCleanerTest {

    @Test
    public void doesNothingIfNicknameIsEmpty() {
        givenVolunteer("Hugo", "Bordais", "", "hugo.bordais@cuck.com", "+33070000000");
        whenCleaningUpVolunteers();
        thenNickNameIs("");
    }

    @Test
    public void firstNameIsFormatted() {
        givenVolunteer("hugo", "Bordais", "", "hugo.bordais@cuck.com", "0700000000");
        whenCleaningUpVolunteers();
        thenFirstNameIs("Hugo");
    }

    @Test
    public void lastNameIsFormatted() {
        givenVolunteer("Hugo", "bordais", "", "hugo.bordais@cuck.com", "0700000000");
        whenCleaningUpVolunteers();
        thenLastNameIs("Bordais");
    }

    @Test
    public void lowercaseFirstNameWhenIsFullUppercase() {
        givenVolunteer("HUGO", "Bordais", "", "hugo.bordais@cuck.com", "0700000000");
        whenCleaningUpVolunteers();
        thenFirstNameIs("Hugo");
    }

    @Test
    public void lowercaseFirstNameWhenIsFullUppercaseWithDash() {
        givenVolunteer("JEAN-JACQUES", "Bordais", "", "hugo.bordais@cuck.com", "0700000000");
        whenCleaningUpVolunteers();
        thenFirstNameIs("Jean-Jacques");
    }

    @Test
    public void lowercaseFirstNameWhenSomeLettersAreUppercase() {
        givenVolunteer("JeAn-JaCqUeS", "Bordais", "", "hugo.bordais@cuck.com", "0700000000");
        whenCleaningUpVolunteers();
        thenFirstNameIs("Jean-Jacques");
    }

    private void givenVolunteer(String firstName, String lastName, String nickname, String eMail, String phone) {
        cleaner = new Cleaner();
        volunteers.add(new Volunteer(firstName, lastName, nickname, eMail, phone));
    }

    private void whenCleaningUpVolunteers() {
        cleanedVolunteers = cleaner.cleanUp(volunteers);
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

    private Cleaner cleaner = null;
    private List<Volunteer> volunteers = new ArrayList<Volunteer>();
    private List<Volunteer> cleanedVolunteers = new ArrayList<Volunteer>();
}
