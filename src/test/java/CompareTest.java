import static org.junit.jupiter.api.Assertions.assertEquals;

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
        List<String[]> lines = new ArrayList<String[]>();
        
        String[] data = {"Doe;John;;john.doe@exemple.org;+33055513225", "Doe;Marc;md;marcdoe@exemple.org;+33096426764", ";John;jd;john_doe@exemple.org;+33055513225"};

        for (String volunteer : data) lines.add(volunteer.split(";"));
        
        parser = new Parser(lines);
        
        volunteers = parser.format();

        compare = new Compare();

    }

    @Test
    public void compareVolunteersBetweenMailAndName() {
       List<Volunteer> comparedVolunteer = compare.compareNameInMail(volunteers, "doe");

       assertEquals(3, comparedVolunteer.size());
    }
}
