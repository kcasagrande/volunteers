package org.example.volunteers;

import org.example.volunteers.models.Volunteer;
import org.example.volunteers.services.Cleaner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        List<Volunteer> outputVolunteers = new ArrayList<Volunteer>();
        Volunteer vol1 = new Volunteer("first1", "last1" , "nick1" ,"email1@email.com" , "+33652675155");
        Volunteer vol2 = new Volunteer("first2", "last2" , "nick2" ,"email1@email" , "06 52 67 51 55");
        Volunteer vol3 = new Volunteer("first3", "last3" , "nick3" ,"email3" , "+33752678164");
        Volunteer vol4 = new Volunteer("first4", "last4" , "nick4" ,"email4@email.com" , "phone4");
        Volunteer vol5 = new Volunteer("first5", "last5" , "nick5" , null , "phone5");
        outputVolunteers.addAll(new ArrayList<>(Arrays.asList(vol1,vol3,vol4,vol2,vol5)));

        boolean result = Cleaner.hasDuplicatePhoneNumber((ArrayList<Volunteer>) outputVolunteers);
        Assertions.assertTrue(result == true);
    }

}
