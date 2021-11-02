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
    private ArrayList<User> testListUser;

    @BeforeEach
    public void setUp() {
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
        boolean result = userTest.checkValidFirstNameOfUser(testListUser);

        assertTrue(result, "Le firstname a été trouvé après le TRIM alors qu'il ne devrait pas !");
    }

    @Test
    public void testCheckTrimOnFirstNameSuccesfull() {
        User userTest = new User(" Bruneau",null,null,null,null );
        userTest.setFirstName(userTest.getFirstName().trim());
        boolean result = userTest.checkValidFirstNameOfUser(testListUser);

        assertFalse(result, "Le firstname n'a pas été trouvé après le TRIM !");
    }

    @Test
    public void testCheckTrimOnLastNameFailed() {
        User userTest = new User(null," Viviane",null,null,null );
        boolean result = userTest.checkValidLastNameOfUser(testListUser);

        assertTrue(result, "Le lastname a été trouvé après le TRIM alors qu'il ne devrait pas !");
    }

    @Test
    public void testCheckTrimOnLastNameSuccesfull() {
        User userTest = new User(null," Viviane",null,null,null );
        userTest.setLastName(userTest.getLastName().trim());
        boolean result = userTest.checkValidLastNameOfUser(testListUser);

        assertFalse(result, "Le lastname n'a pas été trouvé après le TRIM !");
    }

    @Test
    public void testCheckTrimOnMailFailed() {
        User userTest = new User(null,null,null," Spookworm7637@example.com",null );
        boolean result = userTest.checkValidEmailOfUser(testListUser);

        assertTrue(result, "Le mail a été trouvé après le TRIM alors qu'il ne devrait pas !");
    }

    @Test
    public void testCheckTrimOnMailSuccesfull() {
        User userTest = new User(null,null,null," Spookworm7637@example.com",null );
        userTest.setEmail(userTest.getEmail().trim());
        boolean result = userTest.checkValidEmailOfUser(testListUser);

        assertFalse(result, "Le mail n'a pas été trouvé après le TRIM !");
    }

    @Test
    public void testCheckTrimOnUsernameFailed() {
        User userTest = new User(null,null," Viviane",null,null );
        boolean result = true;

        if(userTest.checkValidUsernameOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "Le username a été trouvé après le TRIM alors qu'il ne devrait pas !");
    }

    @Test
    public void testCheckTrimOnUsernameSuccesfull() {
        User userTest = new User(null,null," Viviane",null,null );
        userTest.setUserName(userTest.getUserName().trim());
        boolean result = userTest.checkValidUsernameOfUser(testListUser);

        assertFalse(result, "Le username n'a pas été trouvé après le TRIM !");
    }

    @Test
    public void testCheckTrimOnPhoneFailed() {
        User userTest = new User(null,null,null,null," +33000555132" );
        boolean result = userTest.checkValidPhoneNumberOfUser(testListUser);

        assertTrue(result, "Le phone a été trouvé après le TRIM alors qu'il ne devrait pas !");
    }

    @Test
    public void testCheckTrimOnPhoneSuccesfull() {
        User userTest = new User(null,null,null,null," +33000555132" );
        userTest.setPhone(userTest.getPhone().trim());
        boolean result = userTest.checkValidPhoneNumberOfUser(testListUser);

        assertFalse(result, "Le phone n'a pas été trouvé après le TRIM !");
    }

    @Test
    public void testIsNotNullDataLastNameOfUser() {
        User userTest = new User(null,"Andre", null,null,null );
        boolean result = userTest.checkNullLastNameOfUser();
        assertTrue(result, "Le lastname est null !");
    }

    @Test
    public void testNullDataLastNameOfUser() {
        User userTest = new User(null, null, null, null, null);
        boolean result = userTest.checkNullLastNameOfUser();
        assertFalse(result, "Le lastname n'est pas null !");
    }

    @Test
    public void testIsNotNullDataFirsNameOfUser() {
        User userTest = new User("Thibaut",null, null,null,null );
        boolean result = userTest.checkNullFirstNameOfUser();
        assertTrue(result, "Le firstname est null !");
    }

    @Test
    public void testNullDataFirsNameOfUser() {
        User userTest = new User(null,null, null,null,null );
        boolean result = userTest.checkNullFirstNameOfUser();
        assertFalse(result, "Le firstname n'est pas null !");
    }

    @Test
    public void testIsNotNullDataUserNameOfUser() {
        User userTest = new User(null,null, "tandre",null,null );
        boolean result = userTest.checkNullUserNameOfUser();
        assertTrue(result, "Le username est null !");
    }

    @Test
    public void testNullDataUserNameOfUser() {
        User userTest = new User(null,null, null,null,null );
        boolean result = userTest.checkNullUserNameOfUser();
        assertFalse(result, "Le username n'est pas null !");
    }

    @Test
    public void testIsNotNullDataEmailOfUser() {
        User userTest = new User(null,null, null,"azerty@gmail.com",null );
        boolean result = userTest.checkNullEmailOfUser();
        assertTrue(result, "Le mail est null !");
    }

    @Test
    public void testNullDataEmailOfUser() {
        User userTest = new User(null,null, null,null,null );
        boolean result = userTest.checkNullEmailOfUser();
        assertFalse(result, "Le mail n'est pas null !");
    }

    @Test
    public void testIsNotNullDataPhoneOfUser() {
        User userTest = new User(null,null, null,null,"0600000000" );
        boolean result = userTest.checkNullPhoneOfUser();
        assertTrue(result, "Le phone est null !");
    }

    @Test
    public void testNullDataPhoneOfUser() {
        User userTest = new User(null,null, null,null,null );
        boolean result = userTest.checkNullPhoneOfUser();
        assertFalse(result, "Le phone n'est pas null !");
    }

    @Test
    public void testCheckUpperFirstNameFailed() {
        User userTest = new User("LASUL",null,null,null,null );
        boolean result = true;

        if(userTest.checkValidFirstNameOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "Le firstname a été trouvé en UPPERCASE alors qu'il ne devrait pas !");
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

        assertTrue(result, "Le firstname n'a pas été trouvé en UPPERCASE !");
    }

    @Test
    public void testCheckUpperLastNameFailed() {
        User userTest = new User(null,"VIVIANE",null,null,null );
        boolean result = true;

        if(userTest.checkValidLastNameOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "Le lastname a été trouvé en UPPERCASE alors qu'il ne devrait pas !");
    }

    @Test
    public void testCheckUpperLastNameSuccesfull() {
        User userTest = new User(null,"VIVIANE",null,null,null );
        userTest.setLastName(userTest.getLastName().toLowerCase());
        boolean result = true;

        if(userTest.checkValidLastNameOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "Le lastname n'a pas été trouvé en UPPERCASE !");
    }

    @Test
    public void testCheckUpperMailFailed() {
        User userTest = new User(null,null,null,"TEST@EXAMPLE.COM",null );
        boolean result = true;

        if(userTest.checkValidEmailOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "Le mail a été trouvé en UPPERCASE alors qu'il ne devrait pas !");
    }

    @Test
    public void testCheckUpperMailSuccesfull() {
        User userTest = new User(null,null,null,"TEST@EXAMPLE.COM",null );
        userTest.setEmail(userTest.getEmail().toLowerCase());
        boolean result = true;

        if(userTest.checkValidEmailOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "Le mail n'a pas été trouvé en UPPERCASE !");
    }

    @Test
    public void testCheckUpperUserNameFailed() {
        User userTest = new User(null,null,"VIVIANE",null,null );
        boolean result = true;

        if(userTest.checkValidUsernameOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result,  "Le username a été trouvé en UPPERCASE alors qu'il ne devrait pas !");
    }

    @Test
    public void testCheckUpperUserNameSuccesfull() {
        User userTest = new User(null,null,"VIVIANE",null,null );
        userTest.setUserName(userTest.getUserName().toLowerCase());
        boolean result = true;

        if(userTest.checkValidUsernameOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "Le username n'a pas été trouvé en UPPERCASE !");
    }

    @Test
    public void testCheckAccentFirstNameFailed() {
        User userTest = new User("Lépal",null,null,null,null );
        boolean result = true;

        if(userTest.checkValidFirstNameOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "L'accent a été trouvé sur le firstname alors qu'il ne devrait pas !");
    }

    @Test
    public void testCheckAccentFirstNameSuccesfull() {
        User userTest = new User("Lépal",null,null,null,null );
        userTest.stripAccentFirstName();
        boolean result = true;

        if(userTest.checkValidFirstNameOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "L'accent n'a pas été trouvé !");
    }

    @Test
    public void testCheckAccentLastNameFailed() {
        User userTest = new User(null,"Léo",null,null,null );
        boolean result = true;

        if(userTest.checkValidLastNameOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "L'accent a été trouvé sur le lastname alors qu'il ne devrait pas !");
    }

    @Test
    public void testCheckAccentLastNameSuccesfull() {
        User userTest = new User(null,"Léo",null,null,null );
        userTest.stripAccentLastname();
        boolean result = true;

        if(userTest.checkValidLastNameOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "L'accent n'a pas été trouvé sur le lastname !");
    }

    @Test
    public void testCheckAccentMailFailed() {
        User userTest = new User(null,null,null,"té@example.com",null );
        boolean result = true;

        if(userTest.checkValidEmailOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "L'accent a été trouvé sur le mail alors qu'il ne devrait pas !");
    }

    @Test
    public void testCheckAccentMailSuccesfull() {
        User userTest = new User(null,null,null,"té@example.com",null );
        userTest.stripAccentEmail();
        boolean result = true;

        if(userTest.checkValidEmailOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "L'accent n'a pas été trouvé sur le mail !");
    }

    @Test
    public void testCheckAccenUsernameFailed() {
        User userTest = new User(null,null,"Bétias",null,null );
        boolean result = true;

        if(userTest.checkValidUsernameOfUser(testListUser)) {
            result = false;
        }

        assertFalse(result, "L'accent a été trouvé sur l'username alors qu'il ne devrait pas !");
    }

    @Test
    public void testCheckAccenUsernameSuccesfull() {
        User userTest = new User(null,null,"Bétias",null,null );
        userTest.stripAccentUsername();
        boolean result = true;

        if(userTest.checkValidUsernameOfUser(testListUser)) {
            result = false;
        }

        assertTrue(result, "L'accent n'a pas été trouvé sur l'username !");
    }
}