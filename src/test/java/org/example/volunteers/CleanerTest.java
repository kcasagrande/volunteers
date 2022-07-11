package org.example.volunteers;

import org.example.volunteers.models.Volunteer;
import org.example.volunteers.services.Cleaner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CleanerTest {

    @Test
    public void replacePhoneNumberFrenchCountryCode() {
        String phoneNumber = "+33652675155";

        String result = Cleaner.formatPhoneNumber(phoneNumber);
        Assertions.assertTrue(result.equals("0652675155"));
    }

    @Test
    public void replacePhoneNumberBelgianCountryCode() {
        String phoneNumber = "+32652675155";

        String result = Cleaner.formatPhoneNumber(phoneNumber);
        Assertions.assertTrue(result.equals("0652675155"));
    }

    @Test
    public void replacePhoneNumberSwissCountryCode() {
        String phoneNumber = "+41652675155";

        String result = Cleaner.formatPhoneNumber(phoneNumber);
        Assertions.assertTrue(result.equals("0652675155"));
    }

    @Test
    public void removePhoneNumberParentheses() {
        String phoneNumber = "+33(0)652675155";

        String result = Cleaner.formatPhoneNumber(phoneNumber);
        Assertions.assertTrue(result.equals("0652675155"));
    }

    @Test
    public void removePhoneNumberDots() {
        String phoneNumber = "+33(0)6.52.67.51.55";

        String result = Cleaner.formatPhoneNumber(phoneNumber);
        Assertions.assertTrue(result.equals("0652675155"));
    }

    @Test
    public void removePhoneNumberDashes() {
        String phoneNumber = "06-52-67-51-55";

        String result = Cleaner.formatPhoneNumber(phoneNumber);
        Assertions.assertTrue(result.equals("0652675155"));
    }

    @Test
    public void removePhoneNumberSpaces() {
        String phoneNumber = "+336 52 67 51 55";

        String result = Cleaner.formatPhoneNumber(phoneNumber);
        Assertions.assertTrue(result.equals("0652675155"));
    }

    @Test
    public void checkHasDuplicatePhoneNumber() {
        List<Volunteer> outputVolunteers = new ArrayList<>();
        Volunteer vol1 = new Volunteer("first1", "last1" , "nick1" ,"email1@email.com" , "+33652675155");
        Volunteer vol2 = new Volunteer("first2", "last2" , "nick2" ,"email1@email" , "06 52 67 51 55");
        Volunteer vol3 = new Volunteer("first3", "last3" , "nick3" ,"email3" , "+33752678164");
        Volunteer vol4 = new Volunteer("first4", "last4" , "nick4" ,"email4@email.com" , "+33(0)7.52.67.81.64");
        Volunteer vol5 = new Volunteer("first5", "last5" , "nick5" , null , "00-52-53-44-90");
        outputVolunteers.addAll(new ArrayList<>(Arrays.asList(vol1,vol3,vol4,vol2,vol5)));
        HashMap duplicatePhoneNumbers = Cleaner.checkDuplicatePhoneNumbers(outputVolunteers);
        assertEquals(duplicatePhoneNumbers.size() , 2);
    }

    @Test
    public void testNoPhoneNumber(){
        List<Volunteer> outputVolunteers = new ArrayList<>();
        Volunteer vol1 = new Volunteer("first1", "last1" , "nick1" ,"email1@email.com" , "+33652675155");
        Volunteer vol2 = new Volunteer("first2", "last2" , "nick2" ,"email1@email" , "06 52 67 51 55");
        Volunteer vol3 = new Volunteer("first3", "last3" , "nick3" ,"email3" , null);
        Volunteer vol4 = new Volunteer("first4", "last4" , "nick4" ,"email4@email.com" , "+33(0)7.52.67.81.64");
        Volunteer vol5 = new Volunteer("first5", "last5" , "nick5" , null , null);
        outputVolunteers.addAll(new ArrayList<>(Arrays.asList(vol1,vol3,vol4,vol2,vol5)));

        List<Volunteer> nullPhoneNumbers = Cleaner.checkVolunteersWithNoPhoneNumber(outputVolunteers);
        assertEquals( nullPhoneNumbers.size(), 2);
    }

    @Test
    public void testBadPhoneNumbers(){
        List<Volunteer> outputVolunteers = new ArrayList<>();
        Volunteer vol1 = new Volunteer("first1", "last1" , "nick1" ,"email1@email.com" , "+33652675155");
        Volunteer vol2 = new Volunteer("first2", "last2" , "nick2" ,"email1@email" , "06 52 67 51 55");
        Volunteer vol3 = new Volunteer("first3", "last3" , "nick3" ,"email3" , "+33752678164");
        Volunteer vol4 = new Volunteer("first4", "last4" , "nick4" ,"email4@email.com" , "+33(0)7.52.67.81.64");
        Volunteer vol5 = new Volunteer("first5", "last5" , "nick5" , null , "0052534490");
        outputVolunteers.addAll(new ArrayList<>(Arrays.asList(vol1,vol3,vol4,vol2,vol5)));

        List<Volunteer> badPhoneNumbers = Cleaner.checkBadPhoneNumber(outputVolunteers);
        assertEquals(badPhoneNumbers.size() , 2);
    }

    @Test
    public void testNoEmail(){
        List<Volunteer> outputVolunteers = new ArrayList<>();
        Volunteer vol1 = new Volunteer("first1", "last1" , "nick1" ,null , "phone1");
        Volunteer vol2 = new Volunteer("first2", "last2" , "nick2" ,"email2" , "phone2");
        Volunteer vol3 = new Volunteer("first3", "last3" , "nick3" ,"email3" , "phone3");
        Volunteer vol4 = new Volunteer("first4", "last4" , "nick4" ,null , "phone4");
        outputVolunteers.addAll(new ArrayList<>(Arrays.asList(vol1,vol3,vol4,vol2)));

        List<Volunteer> nullEmails = Cleaner.checkVolunteersWithNoEmail(outputVolunteers);
        assertEquals( nullEmails.size(), 2);
        assertTrue(nullEmails.stream().anyMatch(x-> x.getNickName() == "nick1" && x.getFirstName()=="first1" && x.getLastName() == "last1"));
        assertTrue(nullEmails.stream().anyMatch(x-> x.getNickName() == "nick4" && x.getFirstName()=="first4" && x.getLastName() == "last4"));
    }

    @Test
    public void testDuplicateEmail() {
        List<Volunteer> outputVolunteers = new ArrayList<>();
        Volunteer vol1 = new Volunteer("first1", "last1" , "nick1" ,"email1@email.com" , "phone1");
        Volunteer vol2 = new Volunteer("first2", "last2" , "nick2" ,"email1@email.com" , "phone2");
        Volunteer vol3 = new Volunteer("first3", "last3" , "nick3" ,"email3@email.com" , "phone3");
        Volunteer vol4 = new Volunteer("first4", "last4" , "nick4" ,"email3@email.com" , "phone4");
        Volunteer vol5 = new Volunteer("first5", "last5" , "nick5" , null , "phone5");
        outputVolunteers.addAll(new ArrayList<>(Arrays.asList(vol1,vol3,vol4,vol2,vol5)));

       HashMap<String,List<Volunteer>> duplicateEmails = Cleaner.checkDuplicateEmail(outputVolunteers);
       assertEquals(duplicateEmails.size() , 2);
       List<Volunteer> email1Data = duplicateEmails.get("email1@email.com");
       List<Volunteer> email3Data = duplicateEmails.get("email3@email.com");
       assertEquals(email1Data.size() , 2);
       assertTrue(email1Data.stream().anyMatch(x->x.getNickName() == "nick1" && x.getFirstName() == "first1" && x.getLastName() == "last1"));
       assertTrue(email1Data.stream().anyMatch(x->x.getNickName() == "nick2" && x.getFirstName() == "first2" && x.getLastName() == "last2"));
       assertEquals(email3Data.size() , 2);
       assertTrue(email3Data.stream().anyMatch(x->x.getNickName() == "nick3" && x.getFirstName() == "first3" && x.getLastName() == "last3"));
       assertTrue(email3Data.stream().anyMatch(x->x.getNickName() == "nick4" && x.getFirstName() == "first4" && x.getLastName() == "last4"));

    }

    @Test
    public void testBadEmails(){
        List<Volunteer> outputVolunteers = new ArrayList<>();
        Volunteer vol1 = new Volunteer("first1", "last1" , "nick1" ,"email1@email.com" , "phone1");
        Volunteer vol2 = new Volunteer("first2", "last2" , "nick2" ,"email1@email" , "phone2");
        Volunteer vol3 = new Volunteer("first3", "last3" , "nick3" ,"email3" , "phone3");
        Volunteer vol4 = new Volunteer("first4", "last4" , "nick4" ,"email4@email.com" , "phone4");
        Volunteer vol5 = new Volunteer("first5", "last5" , "nick5" , null , "phone5");
        outputVolunteers.addAll(new ArrayList<>(Arrays.asList(vol1,vol3,vol4,vol2,vol5)));

        List<Volunteer> badEmails = Cleaner.checkBadEmail(outputVolunteers);
        assertEquals(badEmails.size() , 2);
        assertTrue(badEmails.stream().anyMatch(x->x.getNickName() == "nick3" && x.getFirstName() == "first3" && x.getLastName() == "last3"));
        assertTrue(badEmails.stream().anyMatch(x->x.getNickName() == "nick2" && x.getFirstName() == "first2" && x.getLastName() == "last2"));
    }

}
