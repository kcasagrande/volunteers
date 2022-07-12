package org.example.volunteers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatTest {

    @Test
    public void shouldFormatPhoneNumberHyphens() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+3305-2658-575");
        testVolunteer.formatPhoneNumber();
        assertEquals("+33052658575", testVolunteer.phone);
    }

    @Test
    public void shouldNotFormatPhoneNumberAlreadyFormated() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        testVolunteer.formatPhoneNumber();
        assertEquals("+33052658575", testVolunteer.phone);
    }
    @Test
    public void shouldFormatPhoneNumberOpenParenthesis() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+3305(2658(575");
        testVolunteer.formatPhoneNumber();
        assertEquals("+33052658575", testVolunteer.phone);
    }

    @Test
    public void shouldFormatPhoneNumberCloseParenthesis() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+3305)2658)575");
        testVolunteer.formatPhoneNumber();
        assertEquals("+33052658575", testVolunteer.phone);
    }
    @Test
    public void shouldFormatPhoneNumberSlash() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+3305/2658/575");
        testVolunteer.formatPhoneNumber();
        assertEquals("+33052658575", testVolunteer.phone);
    }

    @Test
    public void shouldFormatPhoneNumberAntiSlash() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+3305\\2658\\575");
        testVolunteer.formatPhoneNumber();
        assertEquals("+33052658575", testVolunteer.phone);
    }

    @Test
    public void shouldFormatPhoneDot() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+3305.2658.575");
        testVolunteer.formatPhoneNumber();
        assertEquals("+33052658575", testVolunteer.phone);
    }

    @Test
    public void shouldFormatPhoneSpace() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+3305 2658 575");
        testVolunteer.formatPhoneNumber();
        assertEquals("+33052658575", testVolunteer.phone);
    }


    @Test
    public void shouldNotFormatFirstNameAlreadyFormated() {
        Volunteer testVolunteer = new Volunteer("Matisse", "Livain",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        testVolunteer.formatFirstName();
        assertEquals("Matisse", testVolunteer.firstName);
    }

    @Test
    public void shouldNotFormatFirstNameAlreadyFormatedWithHyphens() {
        Volunteer testVolunteer = new Volunteer("Henry-Matisse", "Livain",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        testVolunteer.formatFirstName();
        assertEquals("Henry-Matisse", testVolunteer.firstName);
    }

    @Test
    public void shouldFormatFirstNameNormal() {
        Volunteer testVolunteer = new Volunteer("matiSse", "Livain",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        testVolunteer.formatFirstName();
        assertEquals("Matisse", testVolunteer.firstName);
    }

    @Test
    public void shouldFormatFirstNameWithHyphens() {
        Volunteer testVolunteer = new Volunteer("henry-matiSse", "Livain",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        testVolunteer.formatFirstName();
        assertEquals("Henry-Matisse", testVolunteer.firstName);
    }

    @Test
    public void shouldFormatFirstNameWithSpaces() {
        Volunteer testVolunteer = new Volunteer("henry matiSse", "Livain",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        testVolunteer.formatFirstName();
        assertEquals("Henry Matisse", testVolunteer.firstName);
    }

    @Test
    public void shouldNotFormatLastNameAlreadyFormated() {
        Volunteer testVolunteer = new Volunteer("Matisse", "Livain",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        testVolunteer.formatLastName();
        assertEquals("Livain", testVolunteer.lastName);
    }

    @Test
    public void shouldNotFormatLastNameAlreadyFormatedWithHyphens() {
        Volunteer testVolunteer = new Volunteer("Henry-Matisse", "Livain-Henry",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        testVolunteer.formatLastName();
        assertEquals("Livain-Henry", testVolunteer.lastName);
    }

    @Test
    public void shouldFormatLastNameNormal() {
        Volunteer testVolunteer = new Volunteer("Matisse", "liVain",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        testVolunteer.formatLastName();
        assertEquals("Livain", testVolunteer.lastName);
    }

    @Test
    public void shouldFormatLastNameWithHyphens() {
        Volunteer testVolunteer = new Volunteer("Matisse", "Livain-hEnry",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        testVolunteer.formatLastName();
        assertEquals("Livain-Henry", testVolunteer.lastName);
    }

    @Test
    public void shouldFormatLastNameWithSpaces() {
        Volunteer testVolunteer = new Volunteer("Matisse", "livain henRy",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        testVolunteer.formatLastName();
        assertEquals("Livain Henry", testVolunteer.lastName);
    }

    @Test
    public void shouldFormatAll() {
        Volunteer testVolunteer = new Volunteer("matIsse-henry", "livain henRy",
                "matli", "Matisse.livAin@Gmail.com"
                , "+3305-2658-575");
        Volunteer verifyVolunteer = new Volunteer("Matisse-Henry", "Livain Henry",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        testVolunteer.format();
        assertEquals(true, testVolunteer.equals(verifyVolunteer));
    }

    @Test
    public void shouldNotFormatAllWhenAlreadyFormated() {
        Volunteer testVolunteer = new Volunteer("Matisse-Henry", "Livain Henry",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        Volunteer verifyVolunteer = new Volunteer("Matisse-Henry", "Livain Henry",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        testVolunteer.format();
        System.out.println(testVolunteer);
        System.out.println(verifyVolunteer);
        assertEquals(true, testVolunteer.equals(verifyVolunteer));
    }
}
