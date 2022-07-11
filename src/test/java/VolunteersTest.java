import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}
