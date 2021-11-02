import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistreTest {
    private Registre registre;

    @BeforeEach
    public void setUp() {
        registre = new Registre(Header.LASTNAME);
    }

    @Test
    public void testToStringRegistre() {
        String expected = "Type: LASTNAME\n" +
                "'COUCOU' : 1 2 3 4 \n" +
                "'SALUT' : 5 \n";

        registre.put("Coucou", 1);
        registre.put("Coucou", 2);
        registre.put("Coucou", 3);
        registre.put("Coucou", 4);
        registre.put("Salut", 5);

        String actual = registre.toString();

        assertEquals(expected, actual);

    }


    @Test
    public void testToStringRegistreUpper() {
        String expected = "Type: LASTNAME\n" +
                "'COUCOU' : 1 2 3 4 \n" +
                "'SALUT' : 5 \n";

        registre.put("Coucou", 1);
        registre.put("coucou", 2);
        registre.put("Coucou", 3);
        registre.put("Coucou", 4);
        registre.put("Salut", 5);

        String actual = registre.toString();

        assertEquals(expected, actual);

    }


    @Test
    public void test_fusesIfSameNames() {
        registre.put("Doe", 1);
        registre.put("Doe", 2);

        HashMap<Integer, User> dict = new HashMap<>();
        HashMap<Integer, User> newDict = new HashMap<>();
        dict.put(1, new User("DOE","JOHN","","","0612345678"));
        dict.put(2, new User("DOE","JOHN","UNKNOWN","JOHN.DOE@EXAMPLE.COM",""));

    }

}