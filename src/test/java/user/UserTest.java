package user;

import org.example.volunteers.Demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        User userTest = new User(" Bruneau"," Viviane"," Viviane"," Spookworm7637@example.com"," +33000555132" );
        userTest.trimAll();
        boolean result = false;

        if(userTest.checkValidComboLastNameFirstNameOfUser(testListUser) &&
                userTest.checkValidPhoneNumberOfUser(testListUser) &&
                userTest.checkValidEmailOfUser(testListUser) &&
                userTest.checkValidUsernameOfUser(testListUser)) {
            result = true;
        }

        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testCheckTrimSuccesfull() {
        User userTest = new User(" Bruneau"," Viviane"," Viviane"," Spookworm7637@example.com"," +33000555132" );
        userTest.trimAll();
        boolean result = false;

        if(userTest.checkValidComboLastNameFirstNameOfUser(testListUser) &&
            userTest.checkValidPhoneNumberOfUser(testListUser) &&
            userTest.checkValidEmailOfUser(testListUser) &&
            userTest.checkValidUsernameOfUser(testListUser)) {
            result = true;
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

    public void testNullDataLastNameOfUser() {
        User userTest = new User(null,"Andre", null,null,null );
        boolean result = userTest.checkNullLastNameOfUser(testListUser);
        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testIsNotNullDataLastNameOfUser() {
        User userTest = new User(null, null, null, null, null);
        boolean result = userTest.checkNullLastNameOfUser(testListUser);
        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testNullDataFirsNameOfUser() {
        User userTest = new User("Thibaut",null, null,null,null );
        boolean result = userTest.checkNullFirstNameOfUser(testListUser);
        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testIsNotNullDataFirsNameOfUser() {
        User userTest = new User(null,null, null,null,null );
        boolean result = userTest.checkNullFirstNameOfUser(testListUser);
        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testNullDataUserNameOfUser() {
        User userTest = new User(null,null, "tandre",null,null );
        boolean result = userTest.checkNullUserNameOfUser(testListUser);
        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testIsNotNullDataUserNameOfUser() {
        User userTest = new User(null,null, null,null,null );
        boolean result = userTest.checkNullUserNameOfUser(testListUser);
        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testNullDataEmailOfUser() {
        User userTest = new User(null,null, null,"azerty@gmail.com",null );
        boolean result = userTest.checkNullEmailOfUser(testListUser);
        assertTrue(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testIsNotNullDataEmailOfUser() {
        User userTest = new User(null,null, null,null,null );
        boolean result = userTest.checkNullEmailOfUser(testListUser);
        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }

    @Test
    public void testNullDataPhoneOfUser() {
        User userTest = new User(null,null, null,null,"0600000000" );
        boolean result = userTest.checkNullPhoneOfUser(testListUser);
        assertTrue(result, "Ce message s'affiche si le test échoue.");
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

    public void testIsNotNullDataPhoneOfUser() {
        User userTest = new User(null,null, null,null,null );
        boolean result = userTest.checkNullPhoneOfUser(testListUser);
        assertFalse(result, "Ce message s'affiche si le test échoue.");
    }
}