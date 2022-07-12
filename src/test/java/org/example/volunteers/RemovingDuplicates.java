package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemovingDuplicates {

    @Test
    public void doNotRemoveDifferentVolunteers(){
        Volunteer v1 = new Volunteer("Don Dada", "Toto", "Bandito", "jean.dupond@gmail.com", "+00374746373");
        Volunteer v2 = new Volunteer("Bango", "BIngo", "ElBandito", "ratus@city.ch", "+00374951373");
        List<Volunteer> testList = Arrays.asList(new Volunteer[]{v1, v2});

        List<Volunteer> expectedResult = Arrays.asList(new Volunteer[]{v1, v2});

        List<Volunteer> actualResult = Cleaner.removeDuplicates(testList);

        assertEquals(expectedResult, actualResult, "Les volunteers ne doivent pas etre supprimés");
    }
    @Test
    public void removePerfectDuplicates(){
        Volunteer v1 = new Volunteer("Jean", "Dupond", "JDupond", "jean.dupond@gmail.com", "+33012345678");
        Volunteer v2 = new Volunteer("Jean", "Dupond", "JDupond", "jean.dupond@gmail.com", "+33012345678");
        List<Volunteer> testList = Arrays.asList(new Volunteer[]{v1, v2});

        List<Volunteer> expectedResult = Arrays.asList(new Volunteer[]{v1});

        List<Volunteer> actualResult = Cleaner.removeDuplicates(testList);

        assertEquals(expectedResult, actualResult, "Les doubles parfait doivent être supprimés");
    }

    @Test
    public void removeDuplicatesByNameAndPseudo(){
        Volunteer v1 = new Volunteer("Jean", "Dupond", "JDupond", "jean.dupadzadzond@gmail.com", "+330987654345");
        Volunteer v2 = new Volunteer("Jean", "Dupond", "JDupond", "jean.dupond@gmail.com", "+33012345678");
        Volunteer v3 = new Volunteer("Jean", "Dupond", "JDupond", "jean.dupond@gmail.com, jean.dupadzadzond@gmail.com", "+33012345678, +33098765434");
        List<Volunteer> testList = Arrays.asList(new Volunteer[]{v1, v2});

        List<Volunteer> expectedResult = Arrays.asList(new Volunteer[]{v3});

        List<Volunteer> actualResult = Cleaner.removeDuplicates(testList);

        assertEquals(expectedResult, actualResult, "Les doubles par Nom, Prénom et Pseudo doivent être supprimés. Les champs Email et Phone doivent être incrémenté par les doublons");
    }

    @Test
    public void removeDuplicatesByNameAndMail(){
        Volunteer v1 = new Volunteer("Jean", "Dupond", "JDupond", "jean.dupond@gmail.com", "+3301276579");
        Volunteer v2 = new Volunteer("Jean", "Dupond", "Toto", "jean.dupond@gmail.com", "+33012345678");
        Volunteer v3 = new Volunteer("Jean", "Dupond", "JDupond, Toto", "jean.dupond@gmail.com", "+33012345678, +3301276579");
        List<Volunteer> testList = Arrays.asList(new Volunteer[]{v1, v2});

        List<Volunteer> expectedResult = Arrays.asList(new Volunteer[]{v3});

        List<Volunteer> actualResult = Cleaner.removeDuplicates(testList);

        assertEquals(expectedResult, actualResult, "Les doubles par Nom, Prénom et eMail doivent être supprimés. Les champs Pseudo et Phone doivent être incrémenté par les doublons");
    }

    @Test
    public void removeDuplicatesByNameAndMail2(){
        Volunteer v1 = new Volunteer("Jean", "Dupond", "JDupond", "jean.dupond@gmail.com", "+00374746373");
        Volunteer v2 = new Volunteer("Jean", "Dupond", "", "jean.dupond@gmail.com", "");
        Volunteer v3 = new Volunteer("Jean", "Dupond", "JDupond", "jean.dupond@gmail.com", "+00374746373");
        List<Volunteer> testList = Arrays.asList(new Volunteer[]{v1, v2});

        List<Volunteer> expectedResult = Arrays.asList(new Volunteer[]{v3});

        List<Volunteer> actualResult = Cleaner.removeDuplicates(testList);

        assertEquals(expectedResult, actualResult, "Les doubles par Nom, Prénom et Email doivent être supprimés. Les champs Pseudo et Phone doivent être incrémenté par les doublons");
    }

    @Test
    public void removeDuplicatesByMailAndPhone(){
        Volunteer v1 = new Volunteer("Don Dada", "Toto", "Bandito", "jean.dupond@gmail.com", "+00374746373");
        Volunteer v2 = new Volunteer("Bango", "BIngo", "ElBandito", "jean.dupond@gmail.com", "+00374746373");
        List<Volunteer> testList = Arrays.asList(new Volunteer[]{v1, v2});

        List<Volunteer> expectedResult = Arrays.asList(new Volunteer[]{v1, v2});

        List<Volunteer> actualResult = Cleaner.removeDuplicates(testList);

        assertEquals(expectedResult, actualResult, "Les doublons par eMail et Phone ne doivent pas être modifiées.");
    }

    @Test
    public void removeDuplicatesBy(){
        Volunteer v1 = new Volunteer("Don Dada", "Toto", "Bandito", "jean.dupond@gmail.com", "+00374746373");
        Volunteer v2 = new Volunteer("Bango", "BIngo", "ElBandito", "jean.dupond@gmail.com", "+00374746373");
        List<Volunteer> testList = Arrays.asList(new Volunteer[]{v1, v2});

        List<Volunteer> expectedResult = Arrays.asList(new Volunteer[]{v1, v2});

        List<Volunteer> actualResult = Cleaner.removeDuplicates(testList);

        assertEquals(expectedResult, actualResult, "Les doublons par eMail et Phone ne doivent pas être modifiées.");
    }
}
