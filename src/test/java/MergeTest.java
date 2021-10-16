import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MergeTest {
    List<Volunteer> volunteers = new ArrayList<>();
    Merge merge;
    Volunteer volunteer1;
    Volunteer volunteer2;

    @BeforeEach
    public void setUp() {
        merge = new Merge();

        volunteer1 = new Volunteer();
        volunteer1.firstname = "John";
        volunteer1.name = "Doe";
        volunteer1.nametag = "";
        volunteer1.mail = "john.doe@exemple.org";
        volunteer1.tel = "";

        volunteer2 = new Volunteer();
        volunteer2.firstname = "John";
        volunteer2.name = "";
        volunteer2.nametag = "jd";
        volunteer2.mail = "john_doe@exemple.org";
        volunteer2.tel = "0055513225";
        
        volunteers.add(volunteer1);
        volunteers.add(volunteer2);
    }

    @Test
    public void mergeVolunteers() {
        Volunteer mergedVolunteer = merge.mergeVolunteers(volunteers);

        assertEquals("Doe", mergedVolunteer.name);
        assertEquals("John", mergedVolunteer.firstname);
        assertEquals("jd", mergedVolunteer.nametag);
        assertEquals("john.doe@exemple.org", mergedVolunteer.mail);
        assertEquals("0055513225", mergedVolunteer.tel);
    }
}
