import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactTest {

    private Function<String, Integer> dependency;
    List<Contact> contacts_test = new ArrayList<Contact>();

    @BeforeEach
    public void setUp() throws IOException {
        dependency = (String string) -> 1;
        contacts_test = App.CreateContactList(Files.readAllLines(Paths.get("src/main/resources/data.csv"))
               .stream().map(string -> string.split(";"))
               .collect(toList()));
    }

    @Test
    public void checkFirstPerson() {
        // Arrange
        String lastNameToCheck = contacts_test.get(0).firstName;

        // Assert
        assertEquals("Rébecca", lastNameToCheck, "Ce message s'affiche si le test échoue");
    }

    @Test
    public void checkIfEmailIsCorrect() {
        // Arrange
        String emailToCheck = contacts_test.get(0).email;

        // Assert
        assertEquals(true, Contact.isValidEmail(emailToCheck), "Ce message s'affiche si le test échoue");
    }

    @Test
    public void checkIsNameInEmail(){
        // Arrange
        String email = contacts_test.get(0).email;
        String firstName = contacts_test.get(0).firstName;

        assertEquals(true, email.contains(Contact.removeDiacriticalMarks(firstName.toLowerCase(Locale.ROOT))), "Le firstName n'est pas dans le email");
    }

    @Test
    public void checkIsLastNameInEmail(){
        // Arrange
        String email = contacts_test.get(0).email;
        String lastName = contacts_test.get(0).lastName;

        assertEquals(true, email.contains(Contact.removeDiacriticalMarks(lastName.toLowerCase(Locale.ROOT))), "Le lastName n'est pas dans le email");
    }

    @Test
    public void checkIsNickNameInEmail(){
        // Arrange
        String email = contacts_test.get(0).email;
        String nickName = contacts_test.get(0).nickName;

        assertEquals(true, email.contains(Contact.removeDiacriticalMarks(nickName.toLowerCase(Locale.ROOT))), "Le lastName n'est pas dans le email");
    }

    @Test
    public void checkIfPhoneIsCorrect() {
        // Arrange
        String phoneToCheck = contacts_test.get(0).phone;

        // Assert
        assertEquals(true, Contact.checkIsValidNumberPhone(phoneToCheck), "Ce message s'affiche si le test échoue");
    }

}
