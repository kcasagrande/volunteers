package org.example.volunteers;

import org.junit.jupiter.api.*;
import org.volunteers.app.App;

import java.util.ArrayList;
import java.util.List;

public class VolunteerTest {
    @Test
    public void sameVolunteerInsertedOnlyOneTime() {
        // Arrange
        List<Volunteer> volunteersExpected = new ArrayList<>();
        volunteersExpected.add(new Volunteer("Logan", "LE SAUX", "LoganLS", "logan.lesaux@gmail.com", "+33123456789"));

        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Logan", "LE SAUX", "LoganLS", "logan.lesaux@gmail.com", "0123456789"));
        volunteers.add(new Volunteer("Logan", "LE SAUX", "LoganLS", "logan.lesaux@gmail.com", "0123456789"));

        // Act
        volunteers = App.cleanUp(volunteers);

        // Assert
        Utils.assertArraysVolunteers(volunteersExpected, volunteers);
    }

    @Test
    public void sameVolunteerWithInvertedFirstNameAndLastName() {
        // Arrange
        List<Volunteer> volunteersExpected = new ArrayList<>();
        volunteersExpected.add(new Volunteer("Logan", "LE SAUX", "LoganLS", "logan.lesaux@gmail.com", "+33123456789"));

        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Logan", "LE SAUX", "LoganLS", "logan.lesaux@gmail.com", "+33123456789"));
        volunteers.add(new Volunteer("LE SAUX", "Logan", "LoganLS", "logan.lesaux@gmail.com", "0123456789"));

        // Act
        volunteers = App.cleanUp(volunteers);

        // Assert
        Utils.assertArraysVolunteers(volunteersExpected, volunteers);
    }

    @Test
    public void sameVolunteerWithSameFirstNameLastNameAndEmail() {
        // Arrange
        List<Volunteer> volunteersExpected = new ArrayList<>();
        volunteersExpected.add(new Volunteer("Logan", "LE SAUX", "", "logan.lesaux@gmail.com", ""));

        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Logan", "LE SAUX", "", "logan.lesaux@gmail.com", ""));
        volunteers.add(new Volunteer("Logan", "LE SAUX", "", "logan.lesaux@gmail.com", ""));

        // Act
        volunteers = App.cleanUp(volunteers);

        // Assert
        Utils.assertArraysVolunteers(volunteersExpected, volunteers);
    }

    @Test
    public void sameVolunteerWithoutSamePhoneFormat() {
        // Arrange
        List<Volunteer> volunteersExpected = new ArrayList<>();
        volunteersExpected.add(new Volunteer("Logan", "LE SAUX", "", "logan.lesaux@gmail.com", "+33123456789"));

        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Logan", "LE SAUX", "", "logan.lesaux@gmail.com", "0123456789"));
        volunteers.add(new Volunteer("Logan", "LE SAUX", "", "logan.lesaux@gmail.com", "+33123456789"));

        // Act
        volunteers = App.cleanUp(volunteers);

        // Assert
        Utils.assertArraysVolunteers(volunteersExpected, volunteers);
    }

    @Test
    public void sameVolunteerWithoutSamePhoneFormatAndSpaces() {
        // Arrange
        List<Volunteer> volunteersExpected = new ArrayList<>();
        volunteersExpected.add(new Volunteer("Logan", "LE SAUX", "", "logan.lesaux@gmail.com", "+33123456789"));

        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Logan", "LE SAUX", "", "logan.lesaux@gmail.com", "01 23 45 67 89"));
        volunteers.add(new Volunteer("Logan", "LE SAUX", "", "logan.lesaux@gmail.com", "+33 1 23 45 67 89"));

        // Act
        volunteers = App.cleanUp(volunteers);

        // Assert
        Utils.assertArraysVolunteers(volunteersExpected, volunteers);
    }

    @Test
    public void sameVolunteerWithoutSamePhoneFormatAndDots() {
        // Arrange
        List<Volunteer> volunteersExpected = new ArrayList<>();
        volunteersExpected.add(new Volunteer("Logan", "LE SAUX", "", "logan.lesaux@gmail.com", "+33123456789"));

        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Logan", "LE SAUX", "", "logan.lesaux@gmail.com", "01.23.45.67.89"));
        volunteers.add(new Volunteer("Logan", "LE SAUX", "", "logan.lesaux@gmail.com", "+33.1.23.45.67.89"));

        // Act
        volunteers = App.cleanUp(volunteers);

        // Assert
        Utils.assertArraysVolunteers(volunteersExpected, volunteers);
    }

    @Test
    public void sameVolunteerWithoutSameCharactersUppercaseFistNameAndLastName() {
        // Arrange
        List<Volunteer> volunteersExpected = new ArrayList<>();
        volunteersExpected.add(new Volunteer("Logan", "LE SAUX", "", "logan.lesaux@gmail.com", ""));

        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Logan", "LE SAUX", "", "logan.lesaux@gmail.com", ""));
        volunteers.add(new Volunteer("logan", "le saux", "", "logan.lesaux@gmail.com", ""));

        // Act
        volunteers = App.cleanUp(volunteers);

        // Assert
        Utils.assertArraysVolunteers(volunteersExpected, volunteers);
    }

    @Test
    public void sameVolunteerWithoutSameCharactersUppercaseNickName() {
        // Arrange
        List<Volunteer> volunteersExpected = new ArrayList<>();
        volunteersExpected.add(new Volunteer("Logan", "LE SAUX", "LoganLS", "logan.lesaux@gmail.com", ""));

        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Logan", "LE SAUX", "LoganLS", "logan.lesaux@gmail.com", ""));
        volunteers.add(new Volunteer("Logan", "LE SAUX", "loganls", "logan.lesaux@gmail.com", ""));

        // Act
        volunteers = App.cleanUp(volunteers);

        // Assert
        Utils.assertArraysVolunteers(volunteersExpected, volunteers);
    }

    @Test
    public void sameVolunteerWithAlmostSameEmail() {
        // Arrange
        List<Volunteer> volunteersExpected = new ArrayList<>();
        volunteersExpected.add(new Volunteer("Logan", "LE SAUX", "LoganLS", "logan.lesaux@gmail.com", ""));

        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Logan", "LE SAUX", "LoganLS", "logan.lesaux@gmail.com", ""));
        volunteers.add(new Volunteer("Logan", "LE SAUX", "loganls", "loganlesaux@gmail.com", ""));
        volunteers.add(new Volunteer("Logan", "LE SAUX", "loganls", "logan_lesaux@gmail.com", ""));

        // Act
        volunteers = App.cleanUp(volunteers);

        // Assert
        Utils.assertArraysVolunteers(volunteersExpected, volunteers);
    }

    @Test
    public void twoDifferentVolunteers() {
        // Arrange
        List<Volunteer> volunteersExpected = new ArrayList<>();
        volunteersExpected.add(new Volunteer("Logan", "LE SAUX", "LoganLS", "logan.lesaux@gmail.com", ""));
        volunteersExpected.add(new Volunteer("Anaël", "BONNAFOUS", "anaelB", "anael.bonnafous@gmail.com", "+33121212121"));


        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Logan", "LE SAUX", "LoganLS", "logan.lesaux@gmail.com", ""));
        volunteers.add(new Volunteer("Anaël", "BONNAFOUS", "anaelB", "anael.bonnafous@gmail.com", "0121212121"));

        // Act
        volunteers = App.cleanUp(volunteers);

        // Assert
        Utils.assertArraysVolunteers(volunteersExpected, volunteers);
    }

    @Test
    public void twoDifferentVolunteersWithOneIsDupplicated() {
        // Arrange
        List<Volunteer> volunteersExpected = new ArrayList<>();
        volunteersExpected.add(new Volunteer("Logan", "LE SAUX", "LoganLS", "logan.lesaux@gmail.com", ""));
        volunteersExpected.add(new Volunteer("Anaël", "BONNAFOUS", "anaelB", "anael.bonnafous@gmail.com", "+33121212121"));


        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Logan", "LE SAUX", "LoganLS", "logan.lesaux@gmail.com", ""));
        volunteers.add(new Volunteer("Logan", "LE SAUX", "LoganLS", "logan.lesaux@gmail.com", ""));
        volunteers.add(new Volunteer("Anaël", "BONNAFOUS", "anaelB", "anael.bonnafous@gmail.com", "0121212121"));

        // Act
        volunteers = App.cleanUp(volunteers);

        // Assert
        Utils.assertArraysVolunteers(volunteersExpected, volunteers);
    }

    @Test
    public void sameVolunteerWithNotExactlySameEmail() {
        // Arrange
        List<Volunteer> volunteersExpected = new ArrayList<>();
        volunteersExpected.add(new Volunteer("Camille", "Thévenet", "", "camille_thevenet@example.net", "+33007709351"));

        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Camille", "Thévenet", "", "camille_thevenet@example.net", "+33007709351"));
        volunteers.add(new Volunteer("CAMILLE", "THÉVENET", "", "camille_thevenet@example.org", "+33055555878"));
        volunteers.add(new Volunteer("Camille", "Thévenet", "", "camille_thevenet@example.fr", ""));
        volunteers.add(new Volunteer("Camille", "Thévenet", "", "camille_thevenet@example.com", "+33007709351"));

        // Act
        volunteers = App.cleanUp(volunteers);

        // Assert
        Utils.assertArraysVolunteers(volunteersExpected, volunteers);
    }
}
