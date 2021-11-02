import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactTest {

    private Function<String, Integer> dependency;
    ArrayList<Contact> contacts_test = new ArrayList<Contact>();

    @BeforeEach
    public void setUp() throws IOException {
        dependency = (String string) -> 1;
        List<String[]> personnes = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
                .stream().map(string -> string.split(";"))
                .collect(toList());

        ArrayList<Contact> contacts = App.CreateContactList(personnes);
        contacts_test = new ArrayList<Contact>(App.removeDuplicates(contacts));
    }

    @Test
    public void checkFirstPerson() {
        // Arrange
        Contact person = contacts_test.get(0);
        System.out.println(person.firstName.concat(" " + person.lastName).concat(" " + person.email));

        // Assert
        assertEquals("rao", person.firstName.toLowerCase(Locale.ROOT), "Ce message s'affiche si le test échoue");
    }

    public Contact getContactWithEmailNotEmpty(){
        int i = 0;
        Contact person = contacts_test.get(0);
        boolean emailEmpty = person.email.isEmpty();
        while(!emailEmpty){
            if(!contacts_test.get(i).email.isEmpty()){
                person = contacts_test.get(i);
            }else{
                i++;
            }
        }
        return person;
    }

    @Test
    public void checkIfEmailIsCorrect() {
        // Arrange
        Contact person = getContactWithEmailNotEmpty();

        // Assert
        assertEquals(true, Contact.isValidEmail(person.email), "Ce message s'affiche si le test échoue");
    }

    @Test
    public void checkIsNameInEmail(){
        // Arrange
        String email = getContactWithEmailNotEmpty().email;
        System.out.println(email);

        String firstName = getContactWithEmailNotEmpty().firstName;
        System.out.println(firstName);

        assertEquals(true, email.contains(Contact.removeDiacriticalMarks(firstName.toLowerCase(Locale.ROOT))), "Le firstName n'est pas dans le email");
    }

    @Test
    public void checkIsLastNameInEmail(){
        // Arrange
        String email = getContactWithEmailNotEmpty().email;
        System.out.println(email);

        String lastName = getContactWithEmailNotEmpty().lastName;
        System.out.println(lastName);

        assertEquals(true, email.contains(Contact.removeDiacriticalMarks(lastName.toLowerCase(Locale.ROOT))), "Le lastName n'est pas dans le email");
    }

    @Test
    public void checkIsNickNameInEmail(){
        // Arrange
        String email = getContactWithEmailNotEmpty().email;
        System.out.println(email);

        String nickName = getContactWithEmailNotEmpty().nickName;
        System.out.println("nickname: " + nickName);


        assertEquals(true, email.contains(Contact.removeDiacriticalMarks(nickName.toLowerCase(Locale.ROOT))), "Le lastName n'est pas dans le email");
    }

    @Test
    public void checkIfPhoneIsCorrect() {
        // Arrange
        String phoneToCheck = contacts_test.get(0).phone;

        // Assert
        System.out.println(phoneToCheck);
        assertEquals(true, Contact.checkIsValidNumberPhone(phoneToCheck), "Ce message s'affiche si le test échoue");
    }

}
