import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        List<String[]> lines = new ArrayList<String[]>();
        
        String[] data = {"Guilloux;Sarah;;sarah_guilloux@example.org;+33085552877", "Thévenet;Camille;;camille_thevenet@example.net;+33007709351", ";;Chimera;helenemalet@example.org;+33075552706"};

        for (String volunteer : data) lines.add(volunteer.split(";"));
        
        parser = new Parser(lines);
    }
    
    @Test
    public void formatDataAndCreateVolunteersArray() {
        List<Volunteer> volunteers = parser.format();

        assertEquals("Guilloux", volunteers.get(0).name);
        assertEquals("Sarah", volunteers.get(0).firstname);
        assertEquals("", volunteers.get(0).nametag);
        assertEquals("sarah_guilloux@example.org", volunteers.get(0).mail);
        assertEquals("+33085552877", volunteers.get(0).tel);
    }

    @Test
    public void formatPhoneNumberData() {
        List<Volunteer> volunteers = parser.format();

        for (Volunteer volunteer : volunteers) volunteer.tel = parser.formatPhoneNumber(volunteer.tel);

        assertEquals("0085552877", volunteers.get(0).tel);
        assertEquals("0007709351", volunteers.get(1).tel);
        assertEquals("0075552706", volunteers.get(2).tel);
    }
}