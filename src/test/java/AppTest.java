import org.example.volunteers.user.User;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void checkKeyIsMail() {
        List<User> users = new ArrayList<>();
        users.add(new User("", "", "", "test@test.com", ""));
        users.add(new User("", "", "", "zeidbfuyzb@izybf.com", ""));

        HashMap<String, List<User>> map = App.createMapMail(users);
        map.forEach(
                (key, value) -> assertTrue(key.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))
        );
    }

    @Test
    public void checkPhoneLength() {
        List<User> users = new ArrayList<>();
        users.add(new User("", "", "", "", "0674886367"));
        users.add(new User("", "", "", "", "0786367"));

        users = App.filterPhone(users);

        assertEquals(1, users.size());
    }

    @Test
    public void checkNamesIntegrity() {
        List<User> users = new ArrayList<>();
        users.add(new User("", "sdufbsbfg", "", "", "0674886367"));
        users.add(new User("skbfZYYébf-zdfb", "JJEY hf-zdfb", "", "", "674886367"));
        users.add(new User("skbfZYYébf-zdfb", "fjzbufzbfiub", "", "", "674886367"));

        users = App.filterNameAndSurname(users);

        assertEquals(1, users.size());
    }

    @Test
    public void checkOneUserPerLine() {
        List<User> users = new ArrayList<>();
        users.add(new User("", "", "", "test@test.com", ""));
        users.add(new User("", "", "", "zeidbfuyzb@izybf.com", ""));
        users.add(new User("dfafe", "", "", "test@test.com", ""));
        users.add(new User("", "xdcvV", "", "test@test.com", "6914979"));
        users.add(new User("feaz", "", "efafe", "zeidbfuyzb@izybf.com", "54956"));

        HashMap<String, List<User>> map = App.aggregateMail(users);
        map.forEach(
                (key, value) -> {
                    System.out.println(key + ":" + value);
                    assertEquals(1, value.size());
                }

        );
    }

    @Test
    public void checkUsersTelNotTheSame() {
        List<User> users = new ArrayList<>();
        users.add(new User("", "", "", "test@test.com", ""));
        users.add(new User("feaz", "", "", "zeidbfuyzb@izybf.com", "6914979"));
        users.add(new User("dfafe", "", "", "test@test.com", "6914979"));
        users.add(new User("", "xdcvV", "", "test@test.com", "6914979"));
        users.add(new User("feaz", "", "efafe", "zeidbfuyzb@izybf.com", "54956"));
        HashMap<String, List<User>> map = App.aggregateMail(users);
        map = App.aggregateTel(map);
        map.forEach(
                (key, value) -> {
                    System.out.println(key + ":" + value);
                    assertEquals(1, value.size());
                }
        );
    }

    @Test
    public void checkPhoneFormatInternational() {
        String phone = "+33698979468";
        phone = App.formatNumbers(phone);
        assertEquals("0698979468", phone);
    }

    @Test
    public void checkPhoneFormatPoint() {
        String phone = "06.26.36.89.45";
        phone = App.formatNumbers(phone);
        assertEquals("0626368945", phone);
    }

    @Test
    public void checkPhoneFormatSpace() {
        String phone = "06 98 97 94 68";
        phone = App.formatNumbers(phone);
        assertEquals("0698979468", phone);
    }

    @Test
    public void checkPhoneFormatDash() {
        String phone = "06-98-97-94-68";
        phone = App.formatNumbers(phone);
        assertEquals("0698979468", phone);
    }

    @Test
    public void checkPhoneFormatZero() {
        String phone = "+33(0)698979468";
        phone = App.formatNumbers(phone);
        assertEquals("0698979468", phone);
    }

    @Test
    public void checkListUser() {
        List<String[]> lines = new ArrayList<>();
        lines.add(new String[]{"Fabien", "Fab", "", "test@test.com", ""});
        lines.add(new String[]{"Theo", "theo", "", "theo@test.com", ""});
        lines.sort(Comparator.comparing(strings -> strings[0]));
        List<User> users = App.createUserListFromCSV(lines);

        assertEquals(2, users.size());
        assertEquals("Fabien", users.get(0).surname);
        assertEquals("Theo", users.get(1).surname);
        assertEquals("Fab", users.get(0).name);
        assertEquals("theo", users.get(1).name);
        assertEquals("test@test.com", users.get(0).mail);
        assertEquals("theo@test.com", users.get(1).mail);
    }

    @Test
    public void checkFilterPseudo() {
        List<User> users = new ArrayList<>();
        users.add(new User("", "", "fab", "test@test.com", ""));
        users.add(new User("feaz", "", "tata", "zeidbfuyzb@izybf.com", "6914979"));
        users.add(new User("", "fea", "toto", "test@test.com", "6914979"));
        users.add(new User("faf", "fea", "titi", "test@test.com", "6914979"));
        users = App.filterPseudo(users);
        users.forEach(
                user -> {
                    assertTrue(!user.pseudo.equals("") && (user.name.equals("") || user.surname.equals("")));
                }
        );
        assertEquals(3, users.size());
    }
}
