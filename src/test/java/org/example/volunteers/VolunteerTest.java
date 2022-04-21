package org.example.volunteers;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VolunteerTest {

    @Test
    public void volunteerToExcelString(){
        String expected_string = "\"damien\";\"max\";\"maxi\";\"max.dam@example.net\";\"00984567\"";
        Volunteer volunteer = new Volunteer("Max", "Damien", "Maxi", "max.dam@example.net", "+330984567");
        assertEquals(expected_string, volunteer.toString());
    }

    //* COMPARAISON

    //? COMPARAISON NOM
    @Test
    public void compareVolunteersByNameShouldBeTrue(){
        Volunteer userA = new Volunteer("mac", "Angeline", "Loc", "zme@ere.Ev", "+33000555019");
        Volunteer userB = new Volunteer("mac", "Angeline", "Lor", "erf@er.E", "+33000555017");
        assertTrue(userA.compare(userB));
    }
    @Test
    public void compareVolunteersByNameShouldBeFalse(){
        Volunteer userA = new Volunteer("max", "Angeline", "Loc", "zme@ere.Ev", "+33000555019");
        Volunteer userB = new Volunteer("mac", "Angeline", "Lor", "erf@er.E", "+33000555017");
        assertFalse(userA.compare(userB));
    }

    //? COMPARAISON CASE
    @Test
    public void compareVolunteersCaseShouldBeTrue(){
        Volunteer userA = new Volunteer("Mac", "Angeline", "loc", "ZME@ere.Ev", "+33000555019");
        Volunteer userB = new Volunteer("mac", "angeline", "Lor", "zme@ere.Ev", "+33000555017");
        assertTrue(userA.compare(userB));
    }

    //? COMPARAISON EMAIL
    @Test
    public void compareVolunteersByEmailShouldBeTrue(){
        Volunteer userA = new Volunteer("Body", "Tcheque", "Loc", "zme@ere.ev", "+33000555019");
        Volunteer userB = new Volunteer("Antoine", "Molo", "Lor", "zme@ere.ev", "+33000555017");
        assertTrue(userA.compare(userB));
    }
    @Test
    public void compareVolunteersByEmailShouldBeFalse(){
        Volunteer userA = new Volunteer("Body", "Angeline", "Loc", "zme@ere.Ev", "+33000555019");
        Volunteer userB = new Volunteer("mac", "Angeline", "Lor", "erf@er.E", "+33000555017");
        assertFalse(userA.compare(userB));
    }

    //? COMPARAISON PHONE
    @Test
    public void compareVolunteersByPhoneShouldBeTrue(){
        Volunteer userA = new Volunteer("Body", "Tcheque", "Loc", "body@move.co", "+33000555019");
        Volunteer userB = new Volunteer("Antoine", "Molo", "Lor", "Ant@oine.daniel", "+33000555019");
        assertTrue(userA.compare(userB));
    }
    @Test
    public void compareVolunteersByPhoneShouldBeFalse(){
        Volunteer userA = new Volunteer("Body", "Angeline", "Loc", "zme@ere.Ev", "+33000555019");
        Volunteer userB = new Volunteer("mac", "Angeline", "Lor", "erf@er.E", "+33000555017");
        assertFalse(userA.compare(userB));
    }


    //* FORMAT
    @Test
    public void formatPhoneTest(){
        Volunteer volunteer = new Volunteer("", "", "", "", "+33(0)09434532");
        String expected = "009434532";
        assertEquals(expected, volunteer.formatPhone(volunteer.phone));
    }

    @Test
    public void formatPhoneScoreTest(){
        Volunteer volunteer = new Volunteer("", "", "", "", "-06434532");
        String expected = "06434532";
        assertEquals(expected, volunteer.formatPhone(volunteer.phone));
    }

    @Test

    public void formatPhoneScoreTestDot(){
        Volunteer volunteer = new Volunteer("", "", "", "", "06.43.45.32");
        String expected = "06434532";
        assertEquals(expected, volunteer.formatPhone(volunteer.phone));
    }


    @Test
    public void formatPhonePlus33Test(){
        Volunteer volunteer = new Volunteer("", "", "", "", "+336434532");
        String expected = "06434532";
        assertEquals(expected, volunteer.formatPhone(volunteer.phone));
    }

    @Test
    public void formatPhonePlus33ZeroTest(){
        Volunteer volunteer = new Volunteer("", "", "", "", "+33(0)6434532");
        String expected = "06434532";
        assertEquals(expected, volunteer.formatPhone(volunteer.phone));
    }

    @Test
    public void formatPhonePlus33DotTest(){
        Volunteer volunteer = new Volunteer("", "", "", "", "+33.64.34.53.2");
        String expected = "06434532";
        assertEquals(expected, volunteer.formatPhone(volunteer.phone));
    }

    @Test
    public void formatPhonePlus33DashTest(){
        Volunteer volunteer = new Volunteer("", "", "", "", "+33-64-34-53-2");
        String expected = "06434532";
        assertEquals(expected, volunteer.formatPhone(volunteer.phone));
    }

    @Test
    public void formatMailTest(){
        Volunteer volunteer = new Volunteer("", "", "", "DidierTet@example.com", "");
        String expected = "didiertet@example.com";
        assertEquals(expected, volunteer.formatString(volunteer.eMail));
    }
    //* Creation
    @Test
    public void VolunteerFromLine() {
        List<String> line = Arrays.asList("Ant", "Oin", "Antoine", "antoine.Tony@tot.tot", "+330923453");
        Volunteer user = new Volunteer(line.get(0), line.get(1), line.get(2), line.get(3), line.get(4));

        assertEquals(user.lastName, "ant");
        assertEquals(user.firstName, "oin");
        assertEquals(user.nickName, "antoine");
        assertEquals(user.eMail, "antoine.tony@tot.tot");
        assertEquals(user.phone, "00923453");

    }
}
