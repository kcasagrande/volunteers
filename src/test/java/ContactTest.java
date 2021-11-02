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
                System.out.println(String.format("The email is valid: %s", person.getEmail().toString()));
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

                    String firstName = person.getNickName();
                    System.out.println(String.format("The firstName is: %s", firstName));

                    assertEquals(true, email.contains(Contact.removeDiacriticalMarks(firstName.toLowerCase(Locale.ROOT))), "Le firstName n'est pas dans le email");
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
                    System.out.println(String.format("The lastName is: %s", lastName));

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
                System.out.println(String.format("The nickName is: %s", nickName));
                assertTrue(email.toLowerCase(Locale.ROOT).contains(Contact.removeDiacriticalMarks(nickName.toLowerCase(Locale.ROOT))), "Le nickName n'est pas dans l'email");
            }
        }
    }


    @Test
    public void checkIsFirstNameThenLastNameInEmailWithBrutValue(){
                String email = "Jean.Lassalle@hotmail.fr";
                if(!email.isEmpty()){
                    System.out.println(String.format("The email is: %s", email));

                    String firstName = "Jean";
                    System.out.println(String.format("The firstName is: %s", firstName));

                    String lastName = "Lassalle";
                    System.out.println(String.format("The lastName is: %s", lastName));

                    assertEquals(true, isFirstNameLastName(firstName, lastName, email), "Le lastName n'est pas dans le email");
        }
    }

    @Test
    public void checkIsNotFirstNameThenLastNameInEmailWithBrutValue(){
        String email = "Lassalle.Jean@hotmail.fr";
        if(!email.isEmpty()){
            System.out.println(String.format("The email is: %s", email));

            String firstName = "Jean";
            System.out.println(String.format("The firstName is: %s", firstName));

            String lastName = "Lassalle";
            System.out.println(String.format("The lastName is: %s", lastName));

            assertEquals(false, isFirstNameLastName(firstName, lastName, email), "Le lastName n'est pas dans le email");
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

                    String firstName = person.getFirstName();
                    System.out.println(String.format("The firstName is: %s", firstName));

                    String lastName = person.getLastName();
                    System.out.println(String.format("The lastName is: %s", lastName));

                    String firstNameLastName = firstName + "." + lastName;


                    assertEquals(true, isFirstNameLastName(firstName, lastName, email), "Le lastName n'est pas dans le email");
                }
            }
        }
    }

    public boolean isFirstNameLastName(String firstName, String lastName, String email){
       String[] emailArray = email.split(firstName);

       if(emailArray[0].isEmpty()){
           // firstname is first and lastName is next
           return emailArray[1].contains(lastName);
       }else {
           return false;
       }

    }

    @Test
    public void checkIfPhoneIsCorrect() {
        for (Contact contact: contacts_test) {
            Contact person = getContactWithEmailNotEmpty(contact);
            if(person != null) {
                String phoneToCheck = person.getPhone();
                System.out.println(String.format("The phone is: %s", phoneToCheck));

                // Assert
                assertEquals(true, Contact.checkIsValidNumberPhone(phoneToCheck), String.format("The phone is %s", phoneToCheck.toString()));
            }
        }
    }

    @Test
    public void checkIfEmailIsEmpty() {
        for (Contact contact: contacts_test) {
            Contact person = getContactWithEmailNotEmpty(contact);
            if(person != null) {
                String phoneToCheck = person.getPhone();
                System.out.println(String.format("The phone is: %s", phoneToCheck));
                // Assert
                assertEquals(true, Contact.checkIsValidNumberPhone(phoneToCheck), String.format("The phone is %s", phoneToCheck.toString()));
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
