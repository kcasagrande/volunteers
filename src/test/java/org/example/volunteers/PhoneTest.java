package org.example.volunteers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PhoneTest {
    @Test
    public void shouldRemoveDashesFromPhoneNumber() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "06-12-34-56-78");

        // Act
        Volunteer cleanVolunteer = Cleaner.convertDashesFromPhoneNumber(volunteer);

        // Assert
        String expectedResult = "0612345678";
        assertEquals(expectedResult, cleanVolunteer.phone, "Le telephone devrait etre 0612345678");
    }

    @Test
    public void shouldRemoveDotsFromPhoneNumber() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "06.12.34.56.78");

        // Act
        Volunteer cleanVolunteer = Cleaner.convertDotsFromPhoneNumber(volunteer);

        // Assert
        String expectedResult = "0612345678";
        assertEquals(expectedResult, cleanVolunteer.phone, "Le telephone devrait etre 0612345678");
    }

    @Test
    public void shouldRemoveSpacesFromPhoneNumber() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "06 12 34 56 78");

        // Act
        Volunteer cleanVolunteer = Cleaner.convertSpacesFromPhoneNumber(volunteer);

        // Assert
        String expectedResult = "0612345678";
        assertEquals(expectedResult, cleanVolunteer.phone, "Le telephone devrait etre 0612345678");
    }

    @Test
    public void shouldRemoveParenthesisFromPhoneNumber() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "(0)612345678");

        // Act
        Volunteer cleanVolunteer = Cleaner.convertParenthesisFromPhoneNumber(volunteer);

        // Assert
        String expectedResult = "0612345678";
        assertEquals(expectedResult, cleanVolunteer.phone, "Le telephone devrait etre 0612345678");
    }

    @Test
    public void shouldReplaceBeginningPhoneNumberWhenHasTenNumbers() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "0612345678");

        // Act
        Volunteer cleanVolunteer = Cleaner.replaceBeginningPhoneNumber(volunteer);

        // Assert
        String expectedResult = "+33612345678";
        assertEquals(expectedResult, cleanVolunteer.phone, "Le telephone devrait etre +33612345678");
    }

    @Test
    public void shouldReplaceBeginningPhoneNumberWhenHasNineNumbers() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "612345678");

        // Act
        Volunteer cleanVolunteer = Cleaner.replaceBeginningPhoneNumber(volunteer);

        // Assert
        String expectedResult = "+33612345678";
        assertEquals(expectedResult, cleanVolunteer.phone, "Le telephone devrait etre +33612345678");
    }
}
