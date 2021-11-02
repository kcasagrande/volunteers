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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactTest {

    private Function<String, Integer> dependency;
    ArrayList<Contact> contacts_test = new ArrayList<Contact>();

    @BeforeEach
    public void setUp() throws IOException {
        dependency = (String string) -> 1;
        List<String[]> persons = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
                .stream().map(string -> string.split(";"))
                .collect(toList());

        ArrayList<Contact> contacts = App.CreateContactList(persons);
        contacts_test = new ArrayList<Contact>(App.removeDuplicates(contacts));
    }

    @Test
    public void checkFirstPerson() {
        for (Contact contact: contacts_test) {
            Contact person = getContactWithEmailNotEmpty(contact);
            if(person != null){
                System.out.println(person.getFirstName().concat(" " + person.getLastName()).concat(" " + person.getEmail()));
                System.out.println("-------------------------------------");
                // Assert
                assertEquals(person.getFirstName().toLowerCase(Locale.ROOT).toString(), person.getFirstName().toLowerCase(Locale.ROOT), "The firstName is ok.");
            }
        }
    }

    @Test
    public void checkIfEmailIsCorrect() {
        for (Contact contact: contacts_test) {
            Contact person = getContactWithEmailNotEmpty(contact);
            if(person != null) {
                System.out.println(String.format("the firstName is: %s", person.getFirstName()));
                System.out.println(String.format("the lastName is: %s", person.getLastName()));
                System.out.println(String.format("the nickName is: %s", person.getNickName()));
                System.out.println(String.format("The email is valid: %s", person.getEmail().toString()));
                System.out.println("-------------------------------------");
                // Assert
                assertEquals(true, Contact.isValidEmail(person.getEmail()), String.format("Email is: %s", person.getEmail().toString()));
            }
        }
    }

    @Test
    public void checkIsNameInEmail(){
        for (Contact contact: contacts_test) {
            Contact person = getContactWithEmailNotEmpty(contact);
            if(person != null) {
                String email = person.getEmail();
                if(!email.isEmpty()){
                    System.out.println(String.format("The email is: %s", email));
                    if(!person.getFirstName().isEmpty()){
                        System.out.println(String.format("The firstName is ok in email: %s", person.getFirstName()));
                        System.out.println("-------------------------------------");
                        // Assert
                        assertEquals(true, email.contains(Contact.removeDiacriticalMarks(person.getFirstName().toLowerCase(Locale.ROOT))), "Le firstName n'est pas dans le email");
                    }
                }
            }
        }
    }

    @Test
    public void checkIsLastNameInEmail(){
        for (Contact contact: contacts_test) {
            Contact person = getContactWithEmailNotEmpty(contact);
            if(person != null) {
                String email = person.getEmail();
                if(!email.isEmpty()){
                    System.out.println(String.format("The email is: %s", email));

                    String lastName = person.getLastName();
                    System.out.println(String.format("The lastName is ok in email: %s", lastName));
                    System.out.println("-------------------------------------");
                    // Assert
                    assertEquals(true, email.contains(Contact.removeDiacriticalMarks(lastName.toLowerCase(Locale.ROOT))), "Le lastName n'est pas dans le email");
                }
            }
        }
    }

    @Test
    public void checkIsNickNameInEmail(){
        for (Contact contact: contacts_test) {
            Contact person = getContactWithEmailNotEmpty(contact);
            if(person != null) {
                String email = person.getEmail();
                String nickName = person.getNickName();
                if(!nickName.isEmpty()){
                    System.out.println(String.format("The nickName is ok : %s", nickName));
                    System.out.println("-------------------------------------");
                    // Assert
                    assertTrue(email.toLowerCase(Locale.ROOT).contains(Contact.removeDiacriticalMarks(nickName.toLowerCase(Locale.ROOT))), "Le nickName n'est pas dans l'email");
                }
            }
        }
    }

    @Test
    public void checkIsFirstNameThenLastNameInEmail(){
        for (Contact contact: contacts_test) {
            Contact person = getContactWithEmailNotEmpty(contact);
            if(person != null) {
                String email = person.getEmail();
                if(!email.isEmpty()){
                    System.out.println(String.format("The email is: %s", email));
                    System.out.println(String.format("The firstName is: %s", person.getFirstName()));
                    System.out.println(String.format("The lastName is: %s", person.getLastName()));
                    System.out.println("-------------------------------------");
                    // Assert
                    assertEquals(true, Contact.IsFirstNameLastName(person), "Le format <<firstName.lastName>> d'email n'est pas valid.");
                }
            }
        }
    }



    @Test
    public void checkIfPhoneIsCorrect() {
        for (Contact contact: contacts_test) {
            Contact person = getContactWithEmailNotEmpty(contact);
            if(person != null) {
                String phoneToCheck = person.getPhone();
                System.out.println(String.format("The phone is: %s", phoneToCheck));
                System.out.println("-------------------------------------");
                // Assert
                assertEquals(true, Contact.checkIsValidNumberPhone(phoneToCheck), String.format("The phone is ok, %s", phoneToCheck.toString()));
            }
        }
    }

    private Contact getContactWithEmailNotEmpty(Contact contact){
        Contact person = contact;
        if(!person.getEmail().isEmpty()){
            return person;
        }
        return null;
	}

}
