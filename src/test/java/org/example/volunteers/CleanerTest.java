package org.example.volunteers;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CleanerTest {

    @Test
    public void doesNothingIfNicknameIsEmpty() {
        givenVolunteer("Hugo", "Bordais", "", "hugo.bordais@cuck.com", "+33070000000");
        whenCleaningUpVolunteers();
        thenNickNameIs("");
    }

    @Test
    public void transformPhoneNumberInFrenchFormat() {
        givenVolunteer("test", "test", "test", "test@test.test", "00-55-51-64-64");
        whenCleaningUpVolunteers();
        thenPhoneIs("+33055516464");
    }

    @Test
    public void transformPhoneNumberWith33AtBeginningInFrenchFormat() {
        givenVolunteer("test", "test", "test", "test@test.test", "+33(0)0-55-55-78-08");
        whenCleaningUpVolunteers();
        thenPhoneIs("+33055557808");
    }

    @Test
    public void transformPhoneNumberWith33InsideInFrenchFormat() {
        givenVolunteer("test", "test", "test", "test@test.test", "+33(0)0-55-33-78-08");
        whenCleaningUpVolunteers();
        thenPhoneIs("+33055337808");
    }

    @Test
    public void transformPhoneNumberWith33InsideInFrenchFormat2() {
        givenVolunteer("test", "test", "test", "test@test.test", "(0)0-55-33-78-08");
        whenCleaningUpVolunteers();
        thenPhoneIs("+33055337808");
    }

    @Test
    public void transformPhoneNumberWithDotsInsideFrenchFormat() {
        givenVolunteer("test", "test", "test", "test@test.test", "+330.45.55.69.06");
        whenCleaningUpVolunteers();
        thenPhoneIs("+33045556906");
    }

    @Test
    public void doesNotTransformPhoneNumberWhenEmpty() {
        givenVolunteer("test", "test", "test", "test@test.test", "");
        whenCleaningUpVolunteers();
        thenPhoneIs("");
    }
    private void givenVolunteer(String firstName, String lastName, String nickname, String eMail, String phone) {
        volunteers.add(new Volunteer(firstName, lastName, nickname, eMail, phone));
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
