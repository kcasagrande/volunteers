import org.example.volunteers.Demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoTest {

    private Function<String, Integer> dependency;
    private List<String[]> personnes;
    List<Contact> contacts = new ArrayList<Contact>();

    @BeforeEach
    public void setUp() throws IOException {
        dependency = (String string) -> 1;
        personnes = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
                .stream().map(string -> string.split(";"))
                .collect(toList());

        for (String[] _contact : personnes) {
            Contact newContact = new Contact(){{
                if(!_contact[0].isEmpty()) firstName = _contact[0]; else firstName = null;
                if(!_contact[1].isEmpty()) lastName = _contact[1]; else lastName = null;
                if(!_contact[2].isEmpty()) nickName = _contact[2]; else nickName = null;
                if(!_contact[3].isEmpty()) email = _contact[3]; else email = null;
                if(_contact.length < 5){
                    phone = null;
                }else{
                    if(!_contact[4].isEmpty()) phone = _contact[4]; else phone = null;
                }
            }};
            contacts.add(newContact);

        }
    }

    @Test
    public void shouldAlwaysPass() {
        assertTrue(true);
    }

    @Test
    public void shouldReturnTheResultOfACallToTheDependency() {
        // Arrange
        Demo demo = new Demo(dependency);
        String input = "tdd";
        int expected = 1;

        // Act
        int actual = demo.run(input);

        // Assert
        assertEquals(expected, actual, "Ce message s'affiche si le test échoue");
    }

    @Test
    public void checkFirstPerson() {
        // Arrange
        String lastNameToCheck = contacts.get(0).firstName;
        // Act


        // Assert
        assertEquals("Rébecca", lastNameToCheck, "Ce message s'affiche si le test échoue");
    }

    @Test
    public void checkIfEmailIsCorrect() {
        // Arrange
        String emailToCheck = contacts.get(0).email;
        // Act


        // Assert
        assertEquals(true, Contact.checkIsValidEmail(emailToCheck), "Ce message s'affiche si le test échoue");
    }

    @Test
    public void checkIfPhoneIsCorrect() {
        // Arrange
        String phoneToCheck = contacts.get(0).phone;
        // Act


        // Assert
        assertEquals(true, Contact.checkIsValidNumberPhone(phoneToCheck), "Ce message s'affiche si le test échoue");
    }

}
