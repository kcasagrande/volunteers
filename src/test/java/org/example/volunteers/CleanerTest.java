package org.example.volunteers;

import org.example.volunteers.models.Volunteer;
import org.example.volunteers.services.Cleaner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

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
        Volunteer vol2 = new Volunteer("first2", "last2" , "nick2" ,"email1@email" , "0652675155");
        Volunteer vol3 = new Volunteer("first3", "last3" , "nick3" ,"email3" , "+33752678164");
        Volunteer vol4 = new Volunteer("first4", "last4" , "nick4" ,"email4@email.com" , "+33(0)7.52.67.81.64");
        Volunteer vol5 = new Volunteer("first5", "last5" , "nick5" , null , "00-52-53-44-90");
        outputVolunteers.addAll(new ArrayList<>(Arrays.asList(vol1,vol3,vol4,vol2,vol5)));

        Cleaner c = new Cleaner(outputVolunteers);
        c.checkPhoneNumbers();

        HashMap<String,List<Volunteer>> duplicatePhoneNumbers = c.phoneNumberValidator.duplicatePhoneNumber;
        assertEquals(duplicatePhoneNumbers.size() , 1);
        List<Volunteer> phoneNumberData1 = duplicatePhoneNumbers.get("0652675155");
        assertEquals(phoneNumberData1.size() , 2);
        assertTrue(phoneNumberData1.stream().anyMatch(x->x.getNickName() == "nick1" && x.getFirstName() == "first1" && x.getLastName() == "last1"));
        assertTrue(phoneNumberData1.stream().anyMatch(x->x.getNickName() == "nick2" && x.getFirstName() == "first2" && x.getLastName() == "last2"));

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

        Cleaner c = new Cleaner(outputVolunteers);
        c.checkPhoneNumbers();

        assertEquals(c.phoneNumberValidator.noPhoneNumber.size() , 2);
        assertTrue(c.phoneNumberValidator.noPhoneNumber.stream().anyMatch(x-> x.getNickName() == "nick3" && x.getFirstName()=="first3" && x.getLastName() == "last3"));
        assertTrue(c.phoneNumberValidator.noPhoneNumber.stream().anyMatch(x-> x.getNickName() == "nick5" && x.getFirstName()=="first5" && x.getLastName() == "last5"));

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

        Cleaner c = new Cleaner(outputVolunteers);
        c.checkPhoneNumbers();

        assertEquals(c.phoneNumberValidator.badFormatPhoneNumber.size() , 2);
        assertTrue(c.phoneNumberValidator.badFormatPhoneNumber.stream().anyMatch(x-> x.getNickName() == "nick2" && x.getFirstName()=="first2" && x.getLastName() == "last2"));
        assertTrue(c.phoneNumberValidator.badFormatPhoneNumber.stream().anyMatch(x-> x.getNickName() == "nick4" && x.getFirstName()=="first4" && x.getLastName() == "last4"));
    }

    @Test
    public void testNoEmail(){
        List<Volunteer> outputVolunteers = new ArrayList<>();
        Volunteer vol1 = new Volunteer("first1", "last1" , "nick1" ,null , "phone1");
        Volunteer vol2 = new Volunteer("first2", "last2" , "nick2" ,"email2" , "phone2");
        Volunteer vol3 = new Volunteer("first3", "last3" , "nick3" ,"email3" , "phone3");
        Volunteer vol4 = new Volunteer("first4", "last4" , "nick4" ,null , "phone4");
        outputVolunteers.addAll(new ArrayList<>(Arrays.asList(vol1,vol3,vol4,vol2)));
        Cleaner c = new Cleaner(outputVolunteers);
        c.checkEmails();

        assertEquals( c.emailValidator.noEmail.size(), 2);
        assertTrue(c.emailValidator.noEmail.stream().anyMatch(x-> x.getNickName() == "nick1" && x.getFirstName()=="first1" && x.getLastName() == "last1"));
        assertTrue(c.emailValidator.noEmail.stream().anyMatch(x-> x.getNickName() == "nick4" && x.getFirstName()=="first4" && x.getLastName() == "last4"));
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

        Cleaner c = new Cleaner(outputVolunteers);
        c.checkEmails();

       HashMap<String,List<Volunteer>> duplicateEmails =c.emailValidator.duplicateEmail;
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
        Cleaner c = new Cleaner(outputVolunteers);
        c.checkEmails();

        assertEquals(c.emailValidator.badFormatEmail.size() , 2);
        assertTrue(c.emailValidator.badFormatEmail.stream().anyMatch(x->x.getNickName() == "nick3" && x.getFirstName() == "first3" && x.getLastName() == "last3"));
        assertTrue(c.emailValidator.badFormatEmail.stream().anyMatch(x->x.getNickName() == "nick2" && x.getFirstName() == "first2" && x.getLastName() == "last2"));
    }

    @Test
    public void testMergeSameEmail(){
        List<Volunteer> outputVolunteers = new ArrayList<>();
        Volunteer vol1 = new Volunteer("first1", "last1" , "nick1" ,"email1@email.com" , "0606060606");
        Volunteer vol2 = new Volunteer("first1", "last1" , "nick1" ,"email1@email.com" , "0606060606");
        Volunteer vol3 = new Volunteer("FIRST1", "LAST1" , "NICK1" ,"email1@email.com" , "0606060606");
        Volunteer vol4 = new Volunteer("first4", "last4" , "nick4" ,"email4" , "phone4");
        Volunteer vol5 = new Volunteer("first5", "last5" , "nick5" , "dsdsdds" , "phone5");
        Volunteer vol6 = new Volunteer("first6", "last6" , "nick6" , "email6@email.com" , "0606060608");
        Volunteer vol7 = new Volunteer("first7", "last7" , "nick7" , "email6@email.com" , "0606060607");
        outputVolunteers.addAll(new ArrayList<>(Arrays.asList(vol1,vol3,vol4,vol2,vol5,vol6,vol7)));
        Cleaner c = new Cleaner(outputVolunteers);
        c.cleanUp();
        List<Volunteer> badEmail = c.emailValidator.badFormatEmail;
        HashMap<Boolean, Set<Volunteer>> emailDuplicated = c.cleanAndMergeDuplicate();
        Set<Volunteer> emailDuplicatedClean = emailDuplicated.get(true);
        Set<Volunteer> emailDuplicatedNotClean = emailDuplicated.get(false);

        assertEquals(badEmail.size() , 2);
        assertEquals(emailDuplicatedNotClean.size() , 5);
        assertEquals(emailDuplicatedClean.size() , 2);
        assertTrue(emailDuplicatedClean.stream().anyMatch(x->x.equals(vol1)));
        assertTrue(emailDuplicatedClean.stream().anyMatch(x->x.getEmail().equals("email6@email.com")
                && x.getPhone().contains("0606060607")
                && x.getPhone().contains("0606060608")
                && x.getPhone().contains(";")
                && x.getNickName().contains("nick6")
                && x.getNickName().contains("nick7")
                && x.getNickName().contains(";")));
        assertTrue(badEmail.stream().anyMatch(x->x.equals(vol4)));
        assertTrue(badEmail.stream().anyMatch(x->x.equals(vol5)));
        assertTrue(emailDuplicatedNotClean.stream().anyMatch(x->x.equals(vol6)));
        assertTrue(emailDuplicatedNotClean.stream().anyMatch(x->x.equals(vol7)));
        assertTrue(emailDuplicatedNotClean.stream().anyMatch(x->x.equals(vol1)));
    }
}
