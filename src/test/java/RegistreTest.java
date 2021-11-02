import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistreTest {


    @Test
    public void testToStringRegistre() {
        String expected = "Type: LASTNAME\n" +
                "'COUCOU' : 1 2 3 4 \n" +
                "'SALUT' : 5 \n";

        Registre registre = new Registre(Header.LASTNAME);
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

        Registre registre = new Registre(Header.LASTNAME);
        registre.put("Coucou", 1);
        registre.put("coucou", 2);
        registre.put("Coucou", 3);
        registre.put("Coucou", 4);
        registre.put("Salut", 5);

        String actual = registre.toString();

        assertEquals(expected, actual);

    }


}