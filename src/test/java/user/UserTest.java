package user;

import org.example.volunteers.Demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private Function<String, Integer> dependency;
    private List<User> testListUser;

    @BeforeEach
    public void setUp() {
        dependency = (String string) -> 1;
        testListUser.add(new User("Thibaut", "Andre", null, null,null));
        testListUser.add(new User("Theo", "Segard", null, null,null));
        testListUser.add(new User("Bruneau","Viviane","Viviane","Spookworm7637@example.com","+33000555132"));
    }

    @Test
    public void testcheckComboLastNameFirstNameOfUser() {
        User user = new User();
        String firstname = "Thibaut";
        String lastName = "Andre";
        boolean result = user.checkComboLastNameFirstNameOfUser(firstname,lastName,testListUser);
        assertTrue(result, "Ce message s'affiche si le test échoue");
    }

    @Test
    public void testcheckComboLastNameFirstNameOfUserNotExist() {
        User user = new User();
        String firstname = "Thibaut";
        String lastName = "Madrières";
        boolean result = user.checkComboLastNameFirstNameOfUser(firstname,lastName,testListUser);
        assertFalse(result, "Ce message s'affiche si le test échoue");
    }
}