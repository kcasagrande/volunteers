import org.example.volunteers.user.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

    @Test
    public void checkKeyIsMail() {
        List<User> users = new ArrayList<>();
        users.add(new User("", "", "", "test@test.com", ""));
        users.add(new User("", "", "", "zeidbfuyzb@izybf.com", ""));

        HashMap<String, List<User>> map = App.createMap(users);
        map.forEach(
                (key, value) -> assertTrue(key.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))
        );
    }

    @Test
    public void checkPhoneLength() {
        List<User> users = new ArrayList<>();
        users.add(new User("", "", "", "", "0674886367"));
        users.add(new User("", "", "", "", "674886367"));

        users = App.filterPhone(users);

        assertEquals(1, users.size());
    }
}
