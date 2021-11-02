import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompareTest {
    private Compare compare;
    private Parser parser;
    List<Volunteer> volunteers;

    @BeforeEach
    public void setUp() {     
        volunteers = new ArrayList<Volunteer>();

        volunteers.add(new Volunteer("Doe", "John", "", "john.doe@exemple.org", "+33055513225"));
        volunteers.add(new Volunteer("Doe", "Marc", "md", "marcdoe@exemple.org", "+33096426764"));
        volunteers.add(new Volunteer("k","John", "jd", "john_doe@exemple.org","+33055513225"));      
        volunteers.add(new Volunteer("jean","michel","","miche.jean@exemple.org","+33055913225"));      

        compare = new Compare();

    }

    @Test
    public void compareVolunteersBetweenMailAndName() {
       List<Volunteer> comparedVolunteer = compare.compareNameInMail(volunteers, "doe");

        List<Volunteer> expected = new ArrayList<Volunteer>();

       expected.add(new Volunteer("Doe", "John", "", "john.doe@exemple.org", "+33055513225"));
       expected.add(new Volunteer("Doe", "Marc", "md", "marcdoe@exemple.org", "+33096426764"));
       expected.add(new Volunteer("k","John", "jd", "john_doe@exemple.org","+33055513225"));      

       assertEquals(3, comparedVolunteer.size());
       assertTrue(comparedVolunteer.containsAll(expected) && expected.containsAll(comparedVolunteer));
    }

    @Test
    public void compareVolunteersBetweenName() {

       List<Volunteer> expected = new ArrayList<Volunteer>();

       expected.add(new Volunteer("John", "Doe", "", "john.doe@exemple.org", "+33055513225"));
       expected.add(new Volunteer("John","k", "jd", "john_doe@exemple.org","+33055513225"));      

       List<Volunteer> comparedVolunteers = compare.compareFirstName(volunteers, "John");

       assertEquals(2, comparedVolunteers.size());
       assertTrue(comparedVolunteers.containsAll(expected) && expected.containsAll(comparedVolunteers));

    }
}
