package org.example.volunteers;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class VolunteerTest {

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
    public void shouldMergeStrings() {
        String base = "Coucou";
        String current = "Au revoir";
        String result = Cleaner.mergeStrings(base,current);
        assertEquals(result,"Coucou,Au revoir");
    }

    @Test
    public void shouldMergeStringsWithEmptyStringCurrent() {
        String base = "Coucou";
        String current = "";
        String result = Cleaner.mergeStrings(base,current);
        assertEquals(result,"Coucou");
    }

    @Test
    public void shouldMergeStringsWithEmptyStringBase() {
        String base = "";
        String current = "Coucou";
        String result = Cleaner.mergeStrings(base,current);
        assertEquals(result,"Coucou");
    }

    @Test
    public void shouldDeleteDuplicateVolunteers() {
        Volunteer testVolunteer = new Volunteer("Antoine", "Bonin",
                "Abo", "antoine.bonin@gmail.com"
                , "+3306205678");
        Volunteer testVolunteer2 = new Volunteer("Emeline", "Pal",
                "Epa", "emeline.pal@gmail.com"
                , "+33052658575");
        Volunteer testVolunteer3 = new Volunteer("Dimitri", "Rivoire",
                "Dri", "dimitri.rivoire@gmail.com"
                , "+3306040465");
        Volunteer testVolunteer4 = new Volunteer("Emeline", "Pal",
                "Epa", "emeline.pal@gmail.com"
                , "+33052658575");
        Volunteer testVolunteer5 = new Volunteer("Matisse", "livain henRy",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        Volunteer testVolunteer6 = new Volunteer("Antoine", "Bonin",
                "Abo", "antoine.bonin@gmail.com"
                , "+3306205678");
        List<Volunteer> baseList = new ArrayList<Volunteer>();
        List<Volunteer> verifyList = new ArrayList<Volunteer>();
        baseList.add(testVolunteer);
        baseList.add(testVolunteer2);
        baseList.add(testVolunteer3);
        baseList.add(testVolunteer4);
        baseList.add(testVolunteer5);
        baseList.add(testVolunteer6);
        verifyList.add(testVolunteer);
        verifyList.add(testVolunteer2);
        verifyList.add(testVolunteer3);
        verifyList.add(testVolunteer5);
        List<Volunteer> noDuplicateList =  Cleaner.handleDuplicates(baseList);
        assertEquals(true, verifyList.equals(noDuplicateList));
    }


    @Test
    public void shouldNotDeleteDuplicateWhenNone() {
        Volunteer testVolunteer = new Volunteer("Antoine", "Bonin",
                "Abo", "antoine.bonin@gmail.com"
                , "+3306205678");
        Volunteer testVolunteer2 = new Volunteer("Emeline", "Pal",
                "Epa", "emeline.pal@gmail.com"
                , "+33052658575");
        Volunteer testVolunteer3 = new Volunteer("Dimitri", "Rivoire",
                "Dri", "dimitri.rivoire@gmail.com"
                , "+3306040465");
        Volunteer testVolunteer4 = new Volunteer("Matisse", "livain henRy",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        List<Volunteer> baseList = new ArrayList<Volunteer>();
        List<Volunteer> verifyList = new ArrayList<Volunteer>();
        baseList.add(testVolunteer);
        baseList.add(testVolunteer2);
        baseList.add(testVolunteer3);
        baseList.add(testVolunteer4);
        verifyList.add(testVolunteer);
        verifyList.add(testVolunteer2);
        verifyList.add(testVolunteer3);
        verifyList.add(testVolunteer4);
        List<Volunteer> noDuplicateList =  Cleaner.handleDuplicates(baseList);
        assertEquals(true, verifyList.equals(noDuplicateList));
    }
}
