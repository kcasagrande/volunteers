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
    public void countUncompletedForm(){
        Volunteer vol1 = new Volunteer("Florian", "Mousset", "FloursWrap", "azertyuio@aa.com", "123456890");
        Volunteer vol2 = new Volunteer("Gerard", "", "nrgix", "ui@aa.com", "123456890");
        Volunteer vol3 = new Volunteer("Antoine", "Mousset", "", "uaai@aa.com", "123456890");
        List<Volunteer> volunteers = Arrays.asList(vol1, vol2, vol3);
        int nbrUncompletedForm = App.getUncompletedForms(volunteers);
        assertEquals(2, nbrUncompletedForm);
    }

    @Test
    public void ensurePhoneNumberIsValid(){
        String phone = "+33(0)000555196";
        String expectedPhoneNumber = "+330000555196";
        String actualPhoneNumber = Cleaner.formatPhoneNumber(phone);
        assertEquals(expectedPhoneNumber, actualPhoneNumber, "Le numéro de téléphone doit etre valide");
    }

    @Test
    public void ensurePerfectDuplicatesAreRemoved(){
        Volunteer vol1 = new Volunteer("Florian", "Mousset", "FloursWrap", "azertyuio@aa.com", "123456890");
        Volunteer vol2 = new Volunteer("Florian", "Mousset", "FloursWrap", "azertyuio@aa.com", "123456890");
        Volunteer vol3 = new Volunteer("Antoine", "Mousset", "", "uaai@aa.com", "123456890");
        List<Volunteer> volunteers = Arrays.asList(vol1, vol2, vol3);

        List<Volunteer> volunteerListWithoutDuplicates = App.deleteDuplicates(volunteers);
        System.out.println(volunteerListWithoutDuplicates);
    }

    @Test
    public void countVolunteerContactPseudoOnly(){
        Volunteer vol1 = new Volunteer("Florian", "Mousset", "", "", "");
        Volunteer vol2 = new Volunteer("Gerard", "", "nrgix", "", "");
        Volunteer vol3 = new Volunteer("Antoine", "Mousset", "test", "uaai@aa.com", "123456890");
        List<Volunteer> volunteers = Arrays.asList(vol1, vol2, vol3);
        int nbrUncompletedForm = App.getVolunteerContactPseudoOnly(volunteers);
        assertEquals(1, nbrUncompletedForm);
    }

    @Test
    public void getInvalidAddresses(){
        Volunteer vol1 = new Volunteer("Florian", "Mousset", "", "aa.com", "");
        Volunteer vol2 = new Volunteer("Gerard", "", "nrgix", "aa.com", "");
        Volunteer vol3 = new Volunteer("Antoine", "Mousset", "test", "uaai@aa.com", "123456890");
        List<Volunteer> volunteers = Arrays.asList(vol1, vol2, vol3);
        List<Volunteer> expectedVolunteer = Arrays.asList(vol1, vol2);
        List<Volunteer> nbrInvalidAddresses = App.getInvalidEmailAddresses(volunteers);
        assertEquals(expectedVolunteer, nbrInvalidAddresses);
    }

    @Test
    public void countVolunteerQuickContact(){
        Volunteer vol1 = new Volunteer("Florian", "Mousset", "kik", "test@test.com", "123456890");
        Volunteer vol2 = new Volunteer("", "", "", "test1@test.com", "");
        Volunteer vol3 = new Volunteer("", "", "", "", "123456890");
        Volunteer vol4 = new Volunteer("Antoine", "", "test", "uaai@aa.com", "123456890");
        List<Volunteer> volunteers = Arrays.asList(vol1, vol2, vol3, vol4);
        int nbrVolunteerQuickContact = App.getVolunteerQuickContact(volunteers);
        assertEquals(3, nbrVolunteerQuickContact);
    }

    @Test
    public void ensureRemoveDuplicateByNameAndNickName() {
        Volunteer vol1 = new Volunteer("Florian", "Mousset", "kik", "test12@test.com", "123456890");
        Volunteer vol2 = new Volunteer("Florian", "Mousset", "kik", "test15@test.com", "0987654321");
        Volunteer vol3 = new Volunteer("flo", "berro", "kik", "test15@test.com", "0987654321");
        Volunteer expectedResult = new Volunteer("Florian", "Mousset", "kik", "test12@test.com,test15@test.com", "123456890,0987654321");
        List<Volunteer> volunteers = Arrays.asList(vol1, vol2, vol3);
        List<Volunteer> expectedList = List.of(expectedResult);
        List<Volunteer> actualResult = App.removeDuplicateByNameAndNickName(volunteers);
        assertEquals(expectedList, actualResult);
    }
}
