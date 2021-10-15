package user;

import org.example.volunteers.Demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private Function<String, Integer> dependency;

    @BeforeEach
    public void setUp() {
        dependency = (String string) -> 1;
    }

    @Test
    public void testInsertFirstName() {
        // init
        User user = new User("thibaut","andre","tandre","tandre@ynov.com", "0600000000");
        String firstname = "thibaut";
        // exec


        // Assert
        assertEquals(firstname, user.getFirstName(), "Ce message s'affiche si le test échoue");
    }
}