package org.example.volunteers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VolunteerTest {
    @Test
    public void testPhoneBasic(){
        // Given
        String phoneTest = "+33012345678";

        // When
        String phoneWanted = Utils.convertPhone(phoneTest);

        // Then
        assertEquals("00.12.34.56.78", phoneWanted);
    }

    @Test
    public void testPhoneTooShort(){
        // Given
        String phoneTest = "+33012";

        // When
        String phoneWanted = Utils.convertPhone(phoneTest);

        // Then
        assertEquals("", phoneWanted);
    }

    @Test
    public void testPhoneTooLong(){
        // Given
        String phoneTest = "+33012345678912345";

        // When
        String phoneWanted = Utils.convertPhone(phoneTest);

        // Then
        assertEquals("", phoneWanted);
    }

    @Test
    public void testPhoneFormatWeird() {
        // Given
        String phoneTest = "+33(0)0-35-55-07-36";

        // When
        String phoneWanted = Utils.convertPhone(phoneTest);

        // Then
        assertEquals("00.35.55.07.36", phoneWanted);
    }

    @Test
    public void testPhoneFormatWeirdBis() {
        // Given
        String phoneTest = "00-35-55-85-21";

        // When
        String phoneWanted = Utils.convertPhone(phoneTest);

        // Then
        assertEquals("00.35.55.85.21", phoneWanted);
    }

    @Test
    public void testPhoneFormatNull() {
        // Given
        String phoneTest = null;

        // When
        String phoneWanted = Utils.convertPhone(phoneTest);

        // Then
        assertEquals("", phoneWanted);
    }

    @Test
    public void testPhoneFormatEmpty() {
        // Given
        String phoneTest = "";

        // When
        String phoneWanted = Utils.convertPhone(phoneTest);

        // Then
        assertEquals("", phoneWanted);
    }

    /*
    Email
     */
    @Test
    public void testMailBasic() {
        // Given
        String mailTest = "rabbi.jacob@gmail.com";

        // When
        String mailWanted = Utils.validateEmail(mailTest);

        // Then
        assertEquals("rabbi.jacob@gmail.com", mailWanted);
    }

    @Test
    public void testMailWithoutUsername() {
        // Given
        String mailTest = "@gmail.com";

        // When
        String mailWanted = Utils.validateEmail(mailTest);

        // Then
        assertEquals("@gmail.com", mailWanted);
    }

    @Test
    public void testMailWithoutDomain() {
        // Given
        String mailTest = "rabi.jacob@";

        // When
        String mailWanted = Utils.validateEmail(mailTest);

        // Then
        assertEquals("rabi.jacob@", mailWanted);
    }

    @Test
    public void testMailWithoutDomainAndSubdomain() {
        // Given
        String mailTest = "rabi.jacob@gmail";

        // When
        String mailWanted = Utils.validateEmail(mailTest);

        // Then
        assertEquals("rabi.jacob@gmail", mailWanted);
    }

    @Test
    public void testMailWithoutAt() {
        // Given
        String mailTest = "rabi.jacob.gmail";

        // When
        String mailWanted = Utils.validateEmail(mailTest);

        // Then
        assertEquals("rabi.jacob.gmail", mailWanted);
    }

    @Test
    public void testMailFormatNull() {
        // Given
        String mailTest = null;

        // When
        String mailWanted = Utils.validateEmail(mailTest);

        // Then
        assertEquals("", mailWanted);
    }

    @Test
    public void testMailFormatEmpty() {
        // Given
        String mailTest = "";

        // When
        String mailWanted = Utils.validateEmail(mailTest);

        // Then
        assertEquals("", mailWanted);
    }

    @Test
    public void testMailFormatUpperCase() {
        // Given
        String mailTest = "RABBI.JACOB@GMAIL.COM";

        // When
        String mailWanted = Utils.validateEmail(mailTest);

        // Then
        assertEquals("rabbi.jacob@gmail.com", mailWanted);
    }

    @Test
    public void testVolunteerTotallyEquals() {
        // Given
        Volunteer volunteerOne = new Volunteer("Pierre"
                , "Pocheron"
                , "PIERROT"
                , "pierre.pocheron@gmail.com"
                , "0626695429");
        Volunteer volunteerTwo = new Volunteer("Pierre"
                , "Pocheron"
                , "PIERROT"
                , "pierre.pocheron@gmail.com"
                , "0626695429");
        boolean isSame = false;

        // When
        isSame = volunteerOne.isSameThan(volunteerTwo);

        // Then
        assertEquals(isSame, true);
    }

    @Test
    public void testVolunteerNotEquals() {
        // Given
        Volunteer volunteerOne = new Volunteer("Pierre"
                , "Pocheront"
                , "PIERROT"
                , "pierre.pochero@gmail.com"
                , "0626695429");
        Volunteer volunteerTwo = new Volunteer("Pierre"
                , "Pocheront"
                , "PIERROT"
                , "pierre.pocheron@gmail.com"
                , "0626695420");
        boolean isSame = false;

        // When
        isSame = volunteerOne.isSameThan(volunteerTwo);

        // Then
        assertEquals(isSame, false);
    }

    @Test
    public void testVolunteerEqualsPartiallyByPhone() {
        // Given
        Volunteer volunteerOne = new Volunteer("Pierre"
                , ""
                , "PIERR00T"
                , "pierre.pochero@gmail.com"
                , "0626695429");
        Volunteer volunteerTwo = new Volunteer(""
                , "Pocheront"
                , "PIERROTE"
                , "pierre.pocheron@gmail.com"
                , "0626695429");
        boolean isSame = false;

        // When
        isSame = volunteerOne.isSameThan(volunteerTwo);

        // Then
        assertEquals(isSame, true);
    }

    @Test
    public void testVolunteerEqualsPartiallyByEmail() {
        // Given
        Volunteer volunteerOne = new Volunteer("Pierre"
                , "Pocheront"
                , "PIERROT"
                , "pierre.potus@gmail.com"
                , "0624695420");
        Volunteer volunteerTwo = new Volunteer("Pierre"
                , "Pocheront"
                , "PIERROT"
                , "pierre.potus@gmail.com"
                , "0626645429");
        boolean isSame = false;

        // When
        isSame = volunteerOne.isSameThan(volunteerTwo);

        // Then
        assertEquals(isSame, true);
    }
}
