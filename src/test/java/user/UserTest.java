package user;

import org.example.volunteers.Demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {
    private Function<String, Integer> dependency;
    private ArrayList<User> testListUser;

    @BeforeEach
    public void setUp() {
        dependency = (String string) -> 1;
        testListUser = new ArrayList<User>();
        testListUser.add(new User("Thibaut", "Andre", "tandre", "tandre@ynov.com","0647000000"));
        testListUser.add(new User("Theo", "Segard", "tseguard", "tseguard@ynov.com",null));
        testListUser.add(new User("Bruneau","Viviane","Viviane","Spookworm7637@example.com","+33000555132"));
        testListUser.add(new User("lasul","viviane","viviane","test@example.com","+33000555132"));
        testListUser.add(new User("Lepal","Leo","Betias","te@example.com","+33000555132"));
    }

    @Test
    public void testCheckValidComboLastNameFirstNameOfUser() {
        User userTest = new User("Thibaut","Andre", null,null,null );
        boolean result = userTest.checkValidComboLastNameFirstNameOfUser(testListUser);
        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckValidComboLastNameFirstNameOfUserNotExist() {
        User userTest = new User("Thibaut","Madrières", null,null,null );
        boolean result = userTest.checkValidComboLastNameFirstNameOfUser(testListUser);
        assertTrue(result, "Ce message s'affiche si le test échoue");
    }

    @Test
    public void testCheckValidUsernameOfUser() {
        User userTest = new User(null,null, "tandre",null,null );
        boolean result = userTest.checkValidUsernameOfUser(testListUser);
        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckValidUsernameOfUserNotExist() {
        User userTest = new User(null,null, "blopblop",null,null );
        boolean result = userTest.checkValidUsernameOfUser(testListUser);
        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckValidEmailOfUser() {
        User userTest = new User(null,null, null,"tandre@ynov.com",null );
        boolean result = userTest.checkValidEmailOfUser(testListUser);
        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckValidEmailOfUserNotExist() {
        User userTest = new User(null,null, null,"azerty@gmail.com",null );
        boolean result = userTest.checkValidEmailOfUser(testListUser);
        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckValidPhoneNumberOfUser() {
        User userTest = new User(null,null, null,"tandre@ynov.com","0647000000" );
        boolean result = userTest.checkValidPhoneNumberOfUser(testListUser);
        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckValidPhoneNumberOfUserNotExist() {
        User userTest = new User(null,null, null,"azerty@gmail.com","054245457687343dk" );
        boolean result = userTest.checkValidPhoneNumberOfUser(testListUser);
        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckTrimOnFirstNameFailed() {
        User userTest = new User(" Bruneau",null,null,null,null );
        boolean result = true;

        if(userTest.checkValidFirstNameOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckTrimOnFirstNameSuccesfull() {
        User userTest = new User(" Bruneau",null,null,null,null );
        userTest.setFirstName(userTest.getFirstName().trim());
        boolean result = true;

        if(userTest.checkValidFirstNameOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckTrimOnLastNameFailed() {
        User userTest = new User(null," Viviane",null,null,null );
        boolean result = true;

        if(userTest.checkValidLastNameOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckTrimOnLastNameSuccesfull() {
        User userTest = new User(null," Viviane",null,null,null );
        userTest.setLastName(userTest.getLastName().trim());
        boolean result = true;

        if(userTest.checkValidLastNameOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckTrimOnMailFailed() {
        User userTest = new User(null,null,null," Spookworm7637@example.com",null );
        boolean result = true;

        if(userTest.checkValidEmailOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckTrimOnMailSuccesfull() {
        User userTest = new User(null,null,null," Spookworm7637@example.com",null );
        userTest.setEmail(userTest.getEmail().trim());
        boolean result = true;

        if(userTest.checkValidEmailOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckTrimOnUsernameFailed() {
        User userTest = new User(null,null," Viviane",null,null );
        boolean result = true;

        if(userTest.checkValidUsernameOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckTrimOnUsernameSuccesfull() {
        User userTest = new User(null,null," Viviane",null,null );
        userTest.setUserName(userTest.getUserName().trim());
        boolean result = true;

        if(userTest.checkValidUsernameOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckTrimOnPhoneFailed() {
        User userTest = new User(null,null,null,null," +33000555132" );
        boolean result = true;

        if(userTest.checkValidPhoneNumberOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckTrimOnPhoneSuccesfull() {
        User userTest = new User(null,null,null,null," +33000555132" );
        userTest.setPhone(userTest.getPhone().trim());
        boolean result = true;

        if(userTest.checkValidPhoneNumberOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testNullDataLastNameOfUser() {
        User userTest = new User(null,"Andre", null,null,null );
        boolean result = userTest.checkNullLastNameOfUser();
        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testIsNotNullDataLastNameOfUser() {
        User userTest = new User(null, null, null, null, null);
        boolean result = userTest.checkNullLastNameOfUser();
        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testNullDataFirsNameOfUser() {
        User userTest = new User("Thibaut",null, null,null,null );
        boolean result = userTest.checkNullFirstNameOfUser();
        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testIsNotNullDataFirsNameOfUser() {
        User userTest = new User(null,null, null,null,null );
        boolean result = userTest.checkNullFirstNameOfUser();
        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testNullDataUserNameOfUser() {
        User userTest = new User(null,null, "tandre",null,null );
        boolean result = userTest.checkNullUserNameOfUser();
        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testIsNotNullDataUserNameOfUser() {
        User userTest = new User(null,null, null,null,null );
        boolean result = userTest.checkNullUserNameOfUser();
        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testNullDataEmailOfUser() {
        User userTest = new User(null,null, null,"azerty@gmail.com",null );
        boolean result = userTest.checkNullEmailOfUser();
        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testIsNotNullDataEmailOfUser() {
        User userTest = new User(null,null, null,null,null );
        boolean result = userTest.checkNullEmailOfUser();
        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testNullDataPhoneOfUser() {
        User userTest = new User(null,null, null,null,"0600000000" );
        boolean result = userTest.checkNullPhoneOfUser();
        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testIsNotNullDataPhoneOfUser() {
        User userTest = new User(null,null, null,null,null );
        boolean result = userTest.checkNullPhoneOfUser();
        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckUpperFirstNameFailed() {
        User userTest = new User("LASUL",null,null,null,null );
        boolean result = true;

        if(userTest.checkValidFirstNameOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckUpperFirstNameSuccesfull() {
        User userTest = new User("LASUL",null,null,null,null );
        userTest.setFirstName(userTest.getFirstName().toLowerCase());
        boolean result = true;

        System.out.println(userTest.getFirstName());
        System.out.println(userTest.checkValidFirstNameOfUser(testListUser));
        if(userTest.checkValidFirstNameOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckUpperLastNameFailed() {
        User userTest = new User(null,"VIVIANE",null,null,null );
        boolean result = true;

        if(userTest.checkValidLastNameOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckUpperLastNameSuccesfull() {
        User userTest = new User(null,"VIVIANE",null,null,null );
        userTest.setLastName(userTest.getLastName().toLowerCase());
        boolean result = true;

        if(userTest.checkValidLastNameOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckUpperMailFailed() {
        User userTest = new User(null,null,null,"TEST@EXAMPLE.COM",null );
        boolean result = true;

        if(userTest.checkValidEmailOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckUpperMailSuccesfull() {
        User userTest = new User(null,null,null,"TEST@EXAMPLE.COM",null );
        userTest.setEmail(userTest.getEmail().toLowerCase());
        boolean result = true;

        if(userTest.checkValidEmailOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckUpperUserNameFailed() {
        User userTest = new User(null,null,"VIVIANE",null,null );
        boolean result = true;

        if(userTest.checkValidUsernameOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckUpperUserNameSuccesfull() {
        User userTest = new User(null,null,"VIVIANE",null,null );
        userTest.setUserName(userTest.getUserName().toLowerCase());
        boolean result = true;

        if(userTest.checkValidUsernameOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckAccentFirstNameFailed() {
        User userTest = new User("Lépal",null,null,null,null );
        boolean result = true;

        if(userTest.checkValidFirstNameOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckAccentFirstNameSuccesfull() {
        User userTest = new User("Lépal",null,null,null,null );
        String firstNameFormat = Normalizer.normalize(userTest.getFirstName(), Normalizer.Form.NFD);
        firstNameFormat = firstNameFormat.replaceAll("[^\\p{ASCII}]", "");
        userTest.setFirstName(firstNameFormat);
        boolean result = true;

        if(userTest.checkValidFirstNameOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckAccentLastNameFailed() {
        User userTest = new User(null,"Léo",null,null,null );
        boolean result = true;

        if(userTest.checkValidLastNameOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckAccentLastNameSuccesfull() {
        User userTest = new User(null,"Léo",null,null,null );
        String lastNameFormat = Normalizer.normalize(userTest.getLastName(), Normalizer.Form.NFD);
        lastNameFormat = lastNameFormat.replaceAll("[^\\p{ASCII}]", "");
        userTest.setLastName(lastNameFormat);
        boolean result = true;

        if(userTest.checkValidLastNameOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckAccentMailFailed() {
        User userTest = new User(null,null,null,"té@example.com",null );
        boolean result = true;

        if(userTest.checkValidEmailOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckAccentMailSuccesfull() {
        User userTest = new User(null,null,null,"té@example.com",null );
        String emailFormat = Normalizer.normalize(userTest.getEmail(), Normalizer.Form.NFD);
        emailFormat = emailFormat.replaceAll("[^\\p{ASCII}]", "");
        userTest.setEmail(emailFormat);
        boolean result = true;

        if(userTest.checkValidEmailOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckAccenUsernameFailed() {
        User userTest = new User(null,null,"Bétias",null,null );
        boolean result = true;

        if(userTest.checkValidUsernameOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckAccenUsernameSuccesfull() {
        User userTest = new User(null,null,"Bétias",null,null );
        String usernameFormat = Normalizer.normalize(userTest.getUserName(), Normalizer.Form.NFD);
        usernameFormat = usernameFormat.replaceAll("[^\\p{ASCII}]", "");
        userTest.setUserName(usernameFormat);
        boolean result = true;

        if(userTest.checkValidUsernameOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }
}