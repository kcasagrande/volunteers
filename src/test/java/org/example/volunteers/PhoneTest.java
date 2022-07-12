package org.example.volunteers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PhoneTest {
    @Test
    public void shouldIsValidPhoneNumberWithOneNumberCountryCode() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "+3612345678");

        // Act
        Boolean isValidNumber = Phone.isValidPhoneNumber(volunteer.phone);

        // Assert
        Boolean expectedResult = true;
        assertEquals(expectedResult, isValidNumber, "Le telephone devrait etre valide");
    }

    @Test
    public void shouldIsValidPhoneNumberWithTwoNumbersCountryCode() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "+33612345678");

        // Act
        Boolean isValidNumber = Phone.isValidPhoneNumber(volunteer.phone);

        // Assert
        Boolean expectedResult = true;
        assertEquals(expectedResult, isValidNumber, "Le telephone devrait etre valide");
    }

    @Test
    public void shouldIsValidPhoneNumberWithThreeNumbersCountryCode() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "+333612345678");

        // Act
        Boolean isValidNumber = Phone.isValidPhoneNumber(volunteer.phone);

        // Assert
        Boolean expectedResult = true;
        assertEquals(expectedResult, isValidNumber, "Le telephone devrait etre valide");
    }

    @Test
    public void shouldIsNotValidPhoneNumberWithCountryCodeAndNotEnoughNumber() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "+336123456");

        // Act
        Boolean isValidNumber = Phone.isValidPhoneNumber(volunteer.phone);

        // Assert
        Boolean expectedResult = false;
        assertEquals(expectedResult, isValidNumber, "Le telephone ne devrait pas etre valide");
    }

    @Test
    public void shouldIsNotValidPhoneNumberWithCountryCodeStartByZero() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "+0612345678");

        // Act
        Boolean isValidNumber = Phone.isValidPhoneNumber(volunteer.phone);

        // Assert
        Boolean expectedResult = false;
        assertEquals(expectedResult, isValidNumber, "Le telephone ne devrait pas etre valide");
    }

    @Test
    public void shouldIsNotValidPhoneNumberWithNotCountryCode() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "0612345678");

        // Act
        Boolean isValidNumber = Phone.isValidPhoneNumber(volunteer.phone);

        // Assert
        Boolean expectedResult = false;
        assertEquals(expectedResult, isValidNumber, "Le telephone ne devrait pas etre valide");
    }

    @Test
    public void shouldRemoveDashesFromPhoneNumber() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "06-12-34-56-78");

        // Act
        Volunteer cleanVolunteer = Phone.convertDashesFromPhoneNumber(volunteer);

        // Assert
        String expectedResult = "0612345678";
        assertEquals(expectedResult, cleanVolunteer.phone, "Le telephone devrait etre 0612345678");
    }

    @Test
    public void shouldRemoveDotsFromPhoneNumber() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "06.12.34.56.78");

        // Act
        Volunteer cleanVolunteer = Phone.convertDotsFromPhoneNumber(volunteer);

        // Assert
        String expectedResult = "0612345678";
        assertEquals(expectedResult, cleanVolunteer.phone, "Le telephone devrait etre 0612345678");
    }

    @Test
    public void shouldRemoveSpacesFromPhoneNumber() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "06 12 34 56 78");

        // Act
        Volunteer cleanVolunteer = Phone.convertSpacesFromPhoneNumber(volunteer);

        // Assert
        String expectedResult = "0612345678";
        assertEquals(expectedResult, cleanVolunteer.phone, "Le telephone devrait etre 0612345678");
    }

    @Test
    public void shouldRemoveParenthesisFromPhoneNumber() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "(0)612345678");

        // Act
        Volunteer cleanVolunteer = Phone.convertParenthesisFromPhoneNumber(volunteer);

        // Assert
        String expectedResult = "0612345678";
        assertEquals(expectedResult, cleanVolunteer.phone, "Le telephone devrait etre 0612345678");
    }

    @Test
    public void shouldReplaceBeginningPhoneNumberWhenHasTenNumbers() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "0612345678");

        // Act
        Volunteer cleanVolunteer = Phone.replaceBeginningPhoneNumber(volunteer);

        // Assert
        String expectedResult = "+33612345678";
        assertEquals(expectedResult, cleanVolunteer.phone, "Le telephone devrait etre +33612345678");
    }

    @Test
    public void shouldReplaceBeginningPhoneNumberWhenHasNineNumbers() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "612345678");

        // Act
        Volunteer cleanVolunteer = Phone.replaceBeginningPhoneNumber(volunteer);

        // Assert
        String expectedResult = "+33612345678";
        assertEquals(expectedResult, cleanVolunteer.phone, "Le telephone devrait etre +33612345678");
    }
}
