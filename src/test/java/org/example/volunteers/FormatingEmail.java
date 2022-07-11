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
}
