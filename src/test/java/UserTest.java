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
        assertEquals(user.phone, "0065557043");

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
    public void twoUsersGetScoreIdenticalButEmptyFieldPlusOne(){
        User userA = new User("ABOUT", "Axelle", "Axelle", "axelleabout@example.net", "+33000555503");
        User userB = new User("About", "Axelle", "Axelle", "", "00-00-55-55-03");

        assertEquals(5, userA.getScoreTo(userB));
    }

    @Test
    public void formatPhone33(){
        User user = new User("", "", "", "", "+33055557808");
        String expected = "0055557808";
        assertEquals(expected, user.formatPhone(user.phone));
    }

    @Test
    public void formatPhoneSpecial33(){
        User user = new User("", "", "", "", "+33(0)055557808");
        String expected = "0055557808";
        assertEquals(expected, user.formatPhone(user.phone));
    }

    @Test
    public void formatPhoneSpecialComplex33(){
        User user = new User("", "", "", "", " +33(0)0-55-55-78-08");
        String expected = "0055557808";
        assertEquals(expected, user.formatPhone(user.phone));
    }

    @Test
    public void formatPhoneSpecialOtherComplex33(){
        User user = new User("", "", "", "", " +33(0)0.55.55.78.08");
        String expected = "0055557808";
        assertEquals(expected, user.formatPhone(user.phone));
    }

    @Test
    public void compareUsersByPhone(){
        User userA = new User("Bonhomme", "Jean-Noël", "", "jean.noelbonhomme@example.com", "+330.55.58.90.50");
        User userB = new User("Bofrand", "Odile", "", "odileboffrand@example.org", "+33000088891");
        assertEquals(1, userA.compareTo(userB));
        assertEquals(-1, userB.compareTo(userA));
    }

    @Test
    public void getEmptyFields(){
        User userA = new User("Barthet", "Angeline", "", "", "+33055589050");
        assertEquals(2, userA.getNbEmptyFields());

        User userB = new User("Barthet", "Angeline", "Locust", "Locust1681@example.com", "+33000555017");
        assertEquals(0, userB.getNbEmptyFields());

        User userC = new User("", "", "", "", "+33055589050");
        assertEquals(4, userC.getNbEmptyFields());

    }

    @Test
    public void compareUsersBySamePhoneSoCompareByEmptyFields(){
        // Celui qui a le plus de champ rempli est le référent
        User userA = new User("Barthet", "Angeline", "", "", "+33055589050");
        User userB = new User("", "aaa", "", "", "+33055589050");

        assertEquals(1, userA.compareTo(userB));
    }

    @Test
    public void compareUsersBySamePhoneSoCompareByLastname(){
        User userA = new User("Barthet", "Angeline", "Locust", "Locust1681@example.com", "+33000555017");
        User userB = new User("Aarthet", "Angeline", "Locust", "Locust1681@example.com", "+33000555017");

        assertEquals(1, userA.compareTo(userB));
    }

    @Test
    public void compareUsersIsTheSame(){
        User userA = new User("Barthet", "Angeline", "Locust", "Locust1681@example.com", "+33000555017");
        User userB = new User("Barthet", "Angeline", "Locust", "Locust1681@example.com", "+33000555017");

        assertEquals(0, userA.compareTo(userB));
    }
}
