package org.example.volunteers;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VolunteerTest {

    @BeforeAll
    public static void globalSetUp() {
    }

    @BeforeEach
    public void setUp() {
        System.out.println("");
    }

    @Test
    public void shouldAlwaysPass() {
        assertTrue(true);
    }

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
        
    @AfterEach
    public void tearDown() {
        System.out.println("Ce code est exécuté après chaque test");
    }

    @AfterAll
    public static void globalTearDown() {
        System.out.println("Ce code est exécuté une seule fois après l'ensemble des tests");
    }

}
