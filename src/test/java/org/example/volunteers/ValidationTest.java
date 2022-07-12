package org.example.volunteers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationTest {
    @Test
    public void shouldHaveValidPhoneNumber() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        boolean hasValidPhone = testVolunteer.hasValidPhoneNumber();
        assertEquals(true,hasValidPhone);
    }

    @Test
    public void shouldHaveInvalidPhoneNumberWithHyphen() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+555-2658575");
        boolean hasValidPhone = testVolunteer.hasValidPhoneNumber();
        assertEquals(false,hasValidPhone);
    }

    @Test
    public void shouldHaveInvalidPhoneNoPlus() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "33052658575");
        boolean hasValidPhone = testVolunteer.hasValidPhoneNumber();
        assertEquals(false, hasValidPhone);
    }

    @Test
    public void shouldHaveInvalidPhoneTooLong() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+3305265857512345");
        boolean hasValidPhone = testVolunteer.hasValidPhoneNumber();
        assertEquals(false,hasValidPhone);
    }

    @Test
    public void shouldHaveInvalidPhoneTooShort() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+");
        boolean hasValidPhone = testVolunteer.hasValidPhoneNumber();
        assertEquals(false,hasValidPhone);
    }

    @Test
    public void shouldHaveValidMail() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        boolean hasValidMail = testVolunteer.hasValidMail();
        assertEquals(true, hasValidMail);
    }

    @Test
    public void shouldHaveInvalidMailArobase() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livaingmail.com"
                , "+33052658575");
        boolean hasValidMail = testVolunteer.hasValidMail();
        assertEquals(false, hasValidMail);
    }

    @Test
    public void shouldHaveInvalidMailANoDotAtEnd() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisselivain@gmailcom"
                , "+33052658575");
        boolean hasValidMail = testVolunteer.hasValidMail();
        assertEquals(false, hasValidMail);
    }

    @Test
    public void shouldHaveInvalidMailANoDotAtEndWithDotBefore() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmailcom"
                , "+33052658575");
        boolean hasValidMail = testVolunteer.hasValidMail();
        assertEquals(false, hasValidMail);
    }

    @Test
    public void shouldHaveValidFullName() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        boolean hasFullName = testVolunteer.hasFullName();
        assertEquals(true, hasFullName);
    }

    @Test
    public void shouldHaveInvalidNoFullNameNoFirstName() {
        Volunteer testVolunteer = new Volunteer("", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        boolean hasFullName = testVolunteer.hasFullName();
        assertEquals(false, hasFullName);
    }

    @Test
    public void shouldHaveInvalidNoFullNameNoLastName() {
        Volunteer testVolunteer = new Volunteer("Matisse", "",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        boolean hasFullName = testVolunteer.hasFullName();
        assertEquals(false, hasFullName);
    }

    @Test
    public void shouldHaveInvalidNoFullNameNoLastNameNoFirstName() {
        Volunteer testVolunteer = new Volunteer("", "",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        boolean hasFullName = testVolunteer.hasFullName();
        assertEquals(false, hasFullName);
    }
}
