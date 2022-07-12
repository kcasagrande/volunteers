package org.example.volunteers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatingEmail {

    @Test
    public void checkFormatEmailCase(){
        String textEmail = "ToTo@yAhoo.coM";

        String expectedResult = "toto@yahoo.com";

        String actualResult = Cleaner.formatEmail(textEmail);

        assertEquals(expectedResult, actualResult, "Une adresse mail doit etre en minuscule");
    }

    @Test
    public void checkFormatEmailInvalid(){
        String textEmail = "toto";

        String expectedResult = "";

        String actualResult = Cleaner.formatEmail(textEmail);

        assertEquals(expectedResult, actualResult, "Si une adresse n'est pas formaté correctement, on la supprime");
    }
    @Test
    public void checkFormatValid(){
        String textEmail = "to.to@yahoo";

        String expectedResult = "to.to@yahoo";

        String actualResult = Cleaner.formatEmail(textEmail);

        assertEquals(expectedResult, actualResult, "Une adresse mail n'as pas forcément de points après l'arobase");
    }
    @Test
    public void checkFormatEmailCharacters1(){
        String textEmail = "to-to@yahoo.com";

        String expectedResult = "to-to@yahoo.com";

        String actualResult = Cleaner.formatEmail(textEmail);

        assertEquals(expectedResult, actualResult, "Une adresse mail peut avoir des tirets");
    }
    @Test
    public void checkFormatEmailCharacters2(){
        String textEmail = "to_to@yahoo.com";

        String expectedResult = "to_to@yahoo.com";

        String actualResult = Cleaner.formatEmail(textEmail);

        assertEquals(expectedResult, actualResult, "Une adresse mail peut avoir des underscore");
    }
    @Test
    public void checkFormatEmailCharacters3(){
        String textEmail = "to.to@yahoo.com";

        String expectedResult = "to.to@yahoo.com";

        String actualResult = Cleaner.formatEmail(textEmail);

        assertEquals(expectedResult, actualResult, "Une adresse mail peut avoir des points");
    }
}
