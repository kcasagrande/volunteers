package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneCleanerTest {

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
        cleaner = new Cleaner();
        volunteers.add(new Volunteer(firstName, lastName, nickname, eMail, phone));
    }
    private void whenCleaningUpVolunteers() {
        cleanedVolunteers = cleaner.cleanUp(volunteers);
    }
    private void thenPhoneIs(String phone) {
        assertEquals(phone, cleanedVolunteers.get(0).phone);
    }

    private Cleaner cleaner = null;
    private List<Volunteer> volunteers = new ArrayList<Volunteer>();
    private List<Volunteer> cleanedVolunteers = new ArrayList<Volunteer>();
}
