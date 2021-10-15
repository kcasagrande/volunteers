import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() throws IOException {
        List<String[]> lines = new ArrayList<String[]>();
        
        String[] data = {"Guilloux;Sarah;;sarah_guilloux@example.org;+33085552877", "Thévenet;Camille;;camille_thevenet@example.net;+33007709351", ";;Chimera;helenemalet@example.org;+33075552706"};

        for (String volunteer : data) lines.add(volunteer.split(";"));
        
        parser = new Parser(lines);
    }
    
    @Test
    public void formatDataAndCreateVolunteersArray() {
        List<Volunteer> volunteer = parser.format();

        assertEquals("Guilloux", volunteer.get(0).firstname);
        assertEquals("Sarah", volunteer.get(0).name);
        assertEquals("", volunteer.get(0).nametag);
        assertEquals("sarah_guilloux@example.org", volunteer.get(0).mail);
        assertEquals("+33085552877", volunteer.get(0).tel);
    }
}
