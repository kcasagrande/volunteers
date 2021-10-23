import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserTest {

    private App app;

    @BeforeEach
    public void setUp() {
        app = new App();
    }

    @Test
    public void entryBecomeUser() {
        String[] line = new String[]{"Simon", "Marina", "Marina", "marina.simon@example.net", "+33065557043"};

        User user = this.app.createUserFromLine.apply(line);

        assertEquals(user.lastname, "Simon");
        assertEquals(user.firstname, "Marina");
        assertEquals(user.username, "Marina");
        assertEquals(user.email, "marina.simon@example.net");
        assertEquals(user.phone, "+33065557043");

    }

    @Test
    public void emptyEntryBecomeUser() {
        String[] line = new String[]{"", "", "", "", ""};

        User user = this.app.createUserFromLine.apply(line);

        assertEquals(user.lastname, "");
        assertEquals(user.firstname, "");
        assertEquals(user.username, "");
        assertEquals(user.email, "");
        assertEquals(user.phone, "");

    }

    @Test
    public void userBecomeRow() {
        User user = new User("Simon", "Marina", "Marina", "marina.simon@example.net", "+33065557043");

        String[] actualRow = user.getRow();
        String[] expectedRow = new String[]{"Simon", "Marina", "Marina", "marina.simon@example.net", "+33065557043"};

        for(int i=0; i < actualRow.length; i++){
            assertEquals(actualRow[i], expectedRow[i]);
        }
    }

    @Test
    public void userToString(){
        String expected_full_row = "Simon;Marina;Marina;marina.simon@example.net;+33065557043";
        User user = new User("Simon", "Marina", "Marina", "marina.simon@example.net", "+33065557043");

        assertEquals(expected_full_row, user.toString());
    }

    @Test
    public void twoUsersAreSameLastname(){
        User userA = new User("ABOUT", "Axelle", "Axelle", "axelleabout@example.net", "+33000555503");
        User userB = new User("About", "Axelle", "Axelle", "", "00-00-55-55-03");

        assertTrue(userA.isSameLastname(userB));
    }
}
