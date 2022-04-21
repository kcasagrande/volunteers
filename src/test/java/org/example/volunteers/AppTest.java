package org.example.volunteers;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void shouldGetVolunteer(){
        Volunteer volunteer = Volunteer.fromString("Guilloux;Sarah;;sarah_guilloux@example.org;+33085552877");
        assertEquals("Guilloux", volunteer.lastName);
    }

    @Test
    public void distinctPersonne(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        volunteers.add(Volunteer.fromString("Guilloux;sarah;sarax;sarah_guilloux@example.org;+33085552877"));
        volunteers.add(Volunteer.fromString("Allard;michel;michelx;michel_guilloux@example.org;+33085552877"));
        assertEquals(2, Cleaner.cleanUp(volunteers).size());
    }

    @Test
    public void volunteerOfSameFamily(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        volunteers.add(Volunteer.fromString("Guilloux;sarah;sarax;sarah_guilloux@example.org;+33085552877"));
        volunteers.add(Volunteer.fromString("Guilloux;michel;michelx;michel_guilloux@example.org;+33085552877"));
        assertEquals(2, Cleaner.cleanUp(volunteers).size());
    }

    @Test
    public void ShouldConcatenateByExactMatching(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        volunteers.add(Volunteer.fromString("Guilloux;Sarah;;sarah_guilloux@example.org;+33085552877"));
        volunteers.add(Volunteer.fromString("Guilloux;Sarah;;sarah_guilloux@example.org;+33085552877"));
        assertEquals(1, Cleaner.cleanUp(volunteers).size());
    }

    @Test
    public void ShouldConcatenateWithoutCaseSensitive(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        volunteers.add(Volunteer.fromString("Guilloux;sarah;;sarah_guilloux@example.org;+33085552877"));
        volunteers.add(Volunteer.fromString("guilloux;SArah;;sarah_guilloux@example.org;+33085552877"));
        assertEquals(1, Cleaner.cleanUp(volunteers).size());
    }

    @Test
    public void ShouldConcatenateWithoutTypoError(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        volunteers.add(Volunteer.fromString("Guilloux;sarah;;sarah_guilloux@example.org;+33085552877"));
        volunteers.add(Volunteer.fromString("Guilloux;sara;;sarah_guilloux@example.org;+33085552877"));
        //System.out.println(Cleaner.cleanUp(volunteers));
        assertEquals(1, Cleaner.cleanUp(volunteers).size());
    }

    @Test
    public void ShouldConcatenateWithBlankLastname(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        volunteers.add(Volunteer.fromString("Guilloux;sarah;sarax;sarah_guilloux@example.org;+33085552877"));
        volunteers.add(Volunteer.fromString(";sarah;sarax;sarah_guilloux@example.org;+33085552877"));
        assertEquals(1, Cleaner.cleanUp(volunteers).size());
    }

    @Test
    public void ShouldConcatenateWithBlankFirstName(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        volunteers.add(Volunteer.fromString("Guilloux;sarah;sarax;sarah_guilloux@example.org;+33085552877"));
        volunteers.add(Volunteer.fromString("Guilloux;;sarax;sarah_guilloux@example.org;+33085552877"));
        assertEquals(1, Cleaner.cleanUp(volunteers).size());
    }

    @Test
    public void ShouldConcatenateWithBlankNickname(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        volunteers.add(Volunteer.fromString("Guilloux;sarah;sarax;sarah_guilloux@example.org;+33085552877"));
        volunteers.add(Volunteer.fromString("Guilloux;sarah;;sarah_guilloux@example.org;+33085552877"));
        assertEquals(1, Cleaner.cleanUp(volunteers).size());
    }

    @Test
    public void ShouldConcatenateWithBlankMail(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        volunteers.add(Volunteer.fromString("Guilloux;sarah;sarax;sarah_guilloux@example.org;+33085552877"));
        volunteers.add(Volunteer.fromString("Guilloux;sarah;sarax;;+33085552877"));
        assertEquals(1, Cleaner.cleanUp(volunteers).size());
    }

    @Test
    public void ShouldConcatenateWithBlankPhone(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        volunteers.add(Volunteer.fromString("Guilloux;sarah;sarax;sarah_guilloux@example.org;+33085552877"));
        volunteers.add(Volunteer.fromString("Guilloux;sarah;sarax;sarah_guilloux@example.org;"));
        assertEquals(1, Cleaner.cleanUp(volunteers).size());
    }

    @Test
    public void shouldFormatePhoneNumber(){
        Volunteer volunteer = Volunteer.fromString("Guilloux;Sarah;;sarah_guilloux@example.org;+33(0)6-855-528 77");
        assertEquals("0685552877", volunteer.cleanPhone);
    }

    @Test
    public void shouldNotFormate(){
        Volunteer volunteer = Volunteer.fromString("Guilloux;Sarah;;sarah_guilloux@example.org;0855528773");
        assertEquals("0855528773", volunteer.cleanPhone);
    }

    @Test
    public void InvalidPhone(){
        Volunteer volunteer = Volunteer.fromString("Guilloux;Sarah;;sarah_guilloux@example.org;+33 0-855-528 77");
        assertEquals("085552877NaN", volunteer.cleanPhone);
    }

    @Test
    public void ShouldHaveSameFirstNameAndLastName(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        volunteers.add(Volunteer.fromString("Guilloux;Sarah;;sarah_guilloux@example.org;+33 0-855-528 77"));
        volunteers.add(Volunteer.fromString("Sarah;Guilloux;;sarah_guilloux@example.org;+33 0-656-528 77"));

        assertEquals(1, Cleaner.cleanUp(volunteers).size());
    }

    @Test
    public void DaughterCase(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        volunteers.add(Volunteer.fromString("Clothilde;Guilloux;;sarah_guilloux@example.org;+33 0-855-528 77"));
        volunteers.add(Volunteer.fromString("Sarah;Guilloux;;sarah_guilloux@example.org;+33 0-656-528 77"));
        assertEquals(2, Cleaner.cleanUp(volunteers).size());
    }

    @Test
    public void notSplittedMail(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        volunteers.add(Volunteer.fromString("Choffard;Ameline;amelinechoffad;amelinechoffard@example.org;+33055532252"));
        volunteers.add(Volunteer.fromString(";Ameline;;ameline.choffard@example.org;+33055532252"));
        assertEquals(1, Cleaner.cleanUp(volunteers).size());
    }

    @Test
    public void matchingMailAndNickname(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        Volunteer volunteer1 = Volunteer.fromString("Guilloux;sarah;sarax;sarah_guilloux@example.org;+33085552877");
        Volunteer volunteer2 = Volunteer.fromString("Guilloux;sarah;sarax;sarah_guilloux@example.org;+33085551888");
        assertEquals(true, volunteer1.MatchingMailNickName(volunteer2));
    }


    /*@Test
    public void ShouldHaveSameFirstNameAndLastname(){
        Volunteer volunteer1 = Volunteer.fromString("Sarah;Guilloux;;sarah_guilloux@example.org;+33 0-855-528 77");
        Volunteer volunteer2 = Volunteer.fromString("Guilloux;Sarah;;sarah_guilloux@example.org;+33 0-855-528 77");

        assertEquals(true,volunteer1.VolunteerFirstNameEqualLastname(volunteer2.lastName) );
    }

    @Test
    public void ShouldHaveSameLastNameAndFirstname(){
        Volunteer volunteer1 = Volunteer.fromString("Sarah;Guilloux;;sarah_guilloux@example.org;+33 0-855-528 77");
        Volunteer volunteer2 = Volunteer.fromString("Guilloux;Sarah;;sarah_guilloux@example.org;+33 0-855-528 77");

        assertEquals(true,volunteer1.VolunteerLastNameEqualFirstname(volunteer2.firstName) );
    }*/

    @Test
    public void ShouldHaveSameFirstNameAndLastnameWithMisspell(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        volunteers.add(Volunteer.fromString("Sarah;Guilou;;sarah_guilloux@example.org;+33 0-855-528 77"));
        volunteers.add(Volunteer.fromString("Guilloux;Sara;;sarah_guillou@example.org;+33 0-855-528 77"));

        assertEquals( 1,  Cleaner.cleanUp(volunteers).size());
        //assertEquals(true,volunteer1.VolunteerLaststnameEqual(volunteer2.lastName) );
    }

    @Test
    public void asChangedPhone(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        volunteers.add(Volunteer.fromString("Guilloux;sarah;sarax;sarah_guilloux@example.org;+33085552877"));
        volunteers.add(Volunteer.fromString("Guilloux;sarah;sarax;sarah_guilloux@example.org;+33085552890"));
        assertEquals(1, Cleaner.cleanUp(volunteers).size());
    }

    @Test
    public void asChangedMail(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        volunteers.add(Volunteer.fromString("Guilloux;sarah;sarax;sarah_guilloux@example.org;+33085552877"));
        volunteers.add(Volunteer.fromString("Guilloux;sarah;sarax;sarah_guilloux@example.com;+33085552877"));
        assertEquals(1, Cleaner.cleanUp(volunteers).size());
    }

    @Test
    public void asChangedMailAndPhone(){
        ArrayList<Volunteer> volunteers= new ArrayList<>();
        volunteers.add(Volunteer.fromString("Guilloux;sarah;sarax;sarah_guilloux@example.org;+33085552877"));
        volunteers.add(Volunteer.fromString("Guilloux;sarah;sarax;sarah_guilloux@example.com;+33085552890"));
        assertEquals(1, Cleaner.cleanUp(volunteers).size());
    }
}
