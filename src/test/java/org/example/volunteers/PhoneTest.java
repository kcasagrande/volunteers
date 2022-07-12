package org.example.volunteers;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneTest {
    @Test
    public void shouldIsValidPhoneNumberWithOneNumberCountryCode() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "+3612345678");

        // Act
        Boolean isValidNumber = Cleaner.isValidPhoneNumber(volunteer.phone);

        // Assert
        Boolean expectedResult = true;
        assertEquals(expectedResult, isValidNumber, "Le telephone devrait etre valide");
    }

    @Test
    public void shouldIsValidPhoneNumberWithTwoNumbersCountryCode() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "+33612345678");

        // Act
        Boolean isValidNumber = Cleaner.isValidPhoneNumber(volunteer.phone);

        // Assert
        Boolean expectedResult = true;
        assertEquals(expectedResult, isValidNumber, "Le telephone devrait etre valide");
    }

    @Test
    public void shouldIsValidPhoneNumberWithThreeNumbersCountryCode() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "+333612345678");

        // Act
        Boolean isValidNumber = Cleaner.isValidPhoneNumber(volunteer.phone);

        // Assert
        Boolean expectedResult = true;
        assertEquals(expectedResult, isValidNumber, "Le telephone devrait etre valide");
    }

    @Test
    public void shouldIsNotValidPhoneNumberWithCountryCodeAndNotEnoughNumber() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "+336123456");

        // Act
        Boolean isValidNumber = Cleaner.isValidPhoneNumber(volunteer.phone);

        // Assert
        Boolean expectedResult = false;
        assertEquals(expectedResult, isValidNumber, "Le telephone ne devrait pas etre valide");
    }

    @Test
    public void shouldIsNotValidPhoneNumberWithCountryCodeStartByZero() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "+0612345678");

        // Act
        Boolean isValidNumber = Cleaner.isValidPhoneNumber(volunteer.phone);

        // Assert
        Boolean expectedResult = false;
        assertEquals(expectedResult, isValidNumber, "Le telephone ne devrait pas etre valide");
    }

    @Test
    public void shouldIsNotValidPhoneNumberWithNotCountryCode() {
        // Arrange
        Volunteer volunteer = new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "0612345678");

        // Act
        Boolean isValidNumber = Cleaner.isValidPhoneNumber(volunteer.phone);

        // Assert
        Boolean expectedResult = false;
        assertEquals(expectedResult, isValidNumber, "Le telephone ne devrait pas etre valide");
    }

    @Test
    public void shouldRemoveDashesFromPhoneNumber() {
        // Arrange
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "06-12-34-56-78"));

        // Act
        volunteers = Cleaner.cleanupPhoneNumber(volunteers);

        // Assert
        String expectedResult = "+33612345678";
        assertEquals(expectedResult, volunteers.get(0).phone, "Le telephone devrait etre +33612345678");
    }

    @Test
    public void shouldRemoveDotsFromPhoneNumber() {
        // Arrange
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "06.12.34.56.78"));

        // Act
        volunteers = Cleaner.cleanupPhoneNumber(volunteers);

        // Assert
        String expectedResult = "+33612345678";
        assertEquals(expectedResult, volunteers.get(0).phone, "Le telephone devrait etre +33612345678");
    }

    @Test
    public void shouldRemoveSpacesFromPhoneNumber() {
        // Arrange
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "06 12 34 56 78"));

        // Act
        volunteers = Cleaner.cleanupPhoneNumber(volunteers);

        // Assert
        String expectedResult = "+33612345678";
        assertEquals(expectedResult, volunteers.get(0).phone, "Le telephone devrait etre +33612345678");
    }

    @Test
    public void shouldRemoveParenthesisFromPhoneNumber() {
        // Arrange
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "(0)612345678"));

        // Act
        volunteers = Cleaner.cleanupPhoneNumber(volunteers);

        // Assert
        String expectedResult = "+33612345678";
        assertEquals(expectedResult, volunteers.get(0).phone, "Le telephone devrait etre +33612345678");
    }

    @Test
    public void shouldRemoveParenthesisFromPhoneNumberWhitCountryCode() {
        // Arrange
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "+33(0)612345678"));

        // Act
        volunteers = Cleaner.cleanupPhoneNumber(volunteers);

        // Assert
        String expectedResult = "+33612345678";
        assertEquals(expectedResult, volunteers.get(0).phone, "Le telephone devrait etre +33612345678");
    }

    @Test
    public void shouldReplaceBeginningPhoneNumberWhenHasTenNumbers() {
        // Arrange
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "0612345678"));

        // Act
        volunteers = Cleaner.cleanupPhoneNumber(volunteers);

        // Assert
        String expectedResult = "+33612345678";
        assertEquals(expectedResult, volunteers.get(0).phone, "Le telephone devrait etre +33612345678");
    }

    @Test
    public void shouldReplaceBeginningPhoneNumberWhenHasNineNumbers() {
        // Arrange
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Prénom", "Nom", "Pseudo", "email@email.com", "612345678"));

        // Act
        volunteers = Cleaner.cleanupPhoneNumber(volunteers);

        // Assert
        String expectedResult = "+33612345678";
        assertEquals(expectedResult, volunteers.get(0).phone, "Le telephone devrait etre +33612345678");
    }
}
