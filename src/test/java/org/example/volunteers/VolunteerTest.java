package org.example.volunteers;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class VolunteerTest {

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
}
