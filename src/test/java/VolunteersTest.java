import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class VolunteersTest {

    @BeforeAll
    public static void globalSetUp() {
          String field = "";
    }

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void fieldShouldNotBeEmpty() {
        String field = "field";
        Boolean isFieldIsEmpty = App.ensureFieldIsNotEmpty(field);
        Assertions.assertFalse(isFieldIsEmpty);
    }

    @Test
    public void formatEmailIsValid() {
        String emailAddress = "luca.s@dalvin.fr";
        Boolean emailFormatIsValid= App.ensureMailIsValidFormat(emailAddress);
        System.out.println(emailFormatIsValid);
        assertTrue(emailFormatIsValid, "Le format de l'email n'est pas valide.");
    }
}
