import org.example.volunteers.Cleaner;
import org.example.volunteers.Volunteer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class VolunteersTest {

    @BeforeAll
    public static void globalSetUp() {
    }

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void fieldShouldNotBeEmpty() {
        String field = "field";
        Boolean isFieldIsEmpty = App.ensureFieldIsNotEmpty(field);
        Assertions.assertFalse(isFieldIsEmpty);
    }

    @Test
    public void formatEmailIsValid() {
        String emailAddress = "luca.s@dalvin.fr";
        Boolean emailFormatIsValid= App.ensureMailIsValidFormat(emailAddress);
        assertTrue(emailFormatIsValid, "Le format de l'email n'est pas valide.");
    }

    @Test
    public void isThereDuplicatesVolunteerByFirstname() {
        Volunteer vol1 = new Volunteer("Florian", "Sardellitti", "FloursWrap", "azertyuio@aa.com", "123456890");
        Volunteer vol2 = new Volunteer("Florian", "Mousset", "nrgix", "ui@aa.com", "545555555");
        List<Volunteer> volunteers = Arrays.asList(vol1, vol2);
        List<Volunteer> duplicates = App.findDuplicatesByFirstname(volunteers);
        Assertions.assertFalse(duplicates.isEmpty());
    }

    @Test
    public void isThereDuplicatesVolunteerByLastname() {
        Volunteer vol1 = new Volunteer("Florian", "Mousset", "FloursWrap", "azertyuio@aa.com", "123456890");
        Volunteer vol2 = new Volunteer("Gerard", "Mousset", "nrgix", "ui@aa.com", "545555555");
        List<Volunteer> volunteers = Arrays.asList(vol1, vol2);
        List<Volunteer> duplicates = App.findDuplicatesByLastname(volunteers);
        Assertions.assertFalse(duplicates.isEmpty());
    }

    @Test
    public void isThereDuplicatesVolunteerByPhoneNumber() {
        Volunteer vol1 = new Volunteer("Florian", "Mousset", "FloursWrap", "azertyuio@aa.com", "123456890");
        Volunteer vol2 = new Volunteer("Gerard", "eeee", "nrgix", "ui@aa.com", "123456890");
        List<Volunteer> volunteers = Arrays.asList(vol1, vol2);
        List<Volunteer> duplicates = App.findDuplicatesByPhone(volunteers);
        Assertions.assertFalse(duplicates.isEmpty());
    }

    @Test
    public void isThereDuplicatesVolunteerByEmail() {
        Volunteer vol1 = new Volunteer("Florian", "Mousset", "FloursWrap", "azertyuio@aa.com", "123456890");
        Volunteer vol2 = new Volunteer("Gerard", "eeee", "nrgix", "azertyuio@aa.com", "123456890");
        List<Volunteer> volunteers = Arrays.asList(vol1, vol2);
        List<Volunteer> duplicates = App.findDuplicatesByEmail(volunteers);
        Assertions.assertFalse(duplicates.isEmpty());
    }

    @Test
    public void isThereDuplicatesVolunteerByNickname() {
        Volunteer vol1 = new Volunteer("Florian", "Mousset", "nrgix", "azertyuio@aa.com", "121321");
        Volunteer vol2 = new Volunteer("Gerard", "eeee", "nrgix", "azertyuio@aa.com", "123456890");
        List<Volunteer> volunteers = Arrays.asList(vol1, vol2);
        List<Volunteer> duplicates = App.findDuplicatesByNickname(volunteers);
        Assertions.assertFalse(duplicates.isEmpty());
    }

    @Test
    public void isUpperCaseAndLowerCase() {
        String name = "qfsdGGDFgee";
        Boolean toTitleCase = App.toTitleCase(name);
        assertTrue(toTitleCase, "Non valide");
    }

    @Test
    public void countCompletedForm(){
        Volunteer vol1 = new Volunteer("Florian", "Mousset", "FloursWrap", "azertyuio@aa.com", "123456890");
        Volunteer vol2 = new Volunteer("Gerard", "", "nrgix", "ui@aa.com", "123456890");
        List<Volunteer> volunteers = Arrays.asList(vol1, vol2);
        int nbrCompletedForm = App.getCompletedForms(volunteers);
        assertEquals(1, nbrCompletedForm);
    }

    @Test
    public void ensurePhoneNumberIsValid(){
        String phone = "+33(0)000555196";
        String expectedPhoneNumber = "+330000555196";
        String actualPhoneNumber = Cleaner.formatPhoneNumber(phone);
        assertEquals(expectedPhoneNumber, actualPhoneNumber, "Le numéro de téléphone doit etre valide");
    }
}
