package org.example.volunteers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CleanerTest {

    /* Useless because we save empty information too
    @Test
    public void cleanEmptyRow(){
        // Given
        List<Volunteer> listVolunteersInput = new ArrayList<Volunteer>();

        listVolunteersInput.add(new Volunteer("","","","",""));
        listVolunteersInput.add(new Volunteer("","","","",""));
        listVolunteersInput.add(new Volunteer("","","","",""));

        // When
        List<Volunteer> listVolunteersOutput = Cleaner.cleanUp(listVolunteersInput);

        // Then
        System.out.println(listVolunteersOutput);
        assertEquals(true, listVolunteersOutput.isEmpty());
    }

    @Test
    public void cleanEmptyPhoneAndEmail(){
        // Given
        List<Volunteer> listVolunteersInput = new ArrayList<Volunteer>();

        listVolunteersInput.add(new Volunteer("Pierre","Pocheron","PiPo","",""));
        listVolunteersInput.add(new Volunteer("Dylan","Antoniotti","DyAn","",""));
        listVolunteersInput.add(new Volunteer("Remy","Potus","RePo","",""));

        // When
        List<Volunteer> listVolunteersOutput = Cleaner.cleanUp(listVolunteersInput);

        // Then
        System.out.println(listVolunteersOutput);
        assertEquals(true, listVolunteersOutput.isEmpty());
    }*/

    @Test
    public void completeEmptyInformationWithSameEmail() {
        // Given
        Volunteer volunteerGood = new Volunteer("Pierre","","Pedro","pedro@gmail.com","+33707080901");
        Volunteer volunteerBad = new Volunteer("Piere","POCHERON","","pedro@gmail.com","");

        List<Volunteer> listVolunteersInput = new ArrayList<>();
        List<Volunteer> listVolunteersOutput = new ArrayList<>();

        listVolunteersInput.add(volunteerGood);
        listVolunteersInput.add(volunteerBad);

        // When
        listVolunteersOutput = Cleaner.cleanUp(listVolunteersInput);

        // Then
        assertEquals(1, listVolunteersOutput.size());
        assertEquals(listVolunteersOutput.get(0).lastName, volunteerBad.lastName);
        assertEquals(listVolunteersOutput.get(0).nickName, volunteerGood.nickName);
        assertEquals(listVolunteersOutput.get(0).phone, volunteerGood.phone);
    }

    @Test
    public void completeEmptyInformationWithSamePhone() {
        // Given
        Volunteer volunteerGood = new Volunteer("Pierre","POCHERON","Pedro","","+33707080901");
        Volunteer volunteerBad = new Volunteer("Piere","P0CH3R0N","","pierre.pocheron@gmail.com","+33707080901");

        List<Volunteer> listVolunteersInput = new ArrayList<>();
        List<Volunteer> listVolunteersOutput = new ArrayList<>();

        listVolunteersInput.add(volunteerGood);
        listVolunteersInput.add(volunteerBad);

        // When
        listVolunteersOutput = Cleaner.cleanUp(listVolunteersInput);

        // Then
        assertEquals(1, listVolunteersOutput.size());
        assertEquals(listVolunteersOutput.get(0).nickName, volunteerGood.nickName);
        assertEquals(listVolunteersOutput.get(0).eMail, volunteerBad.eMail);
        assertEquals(listVolunteersOutput.get(0).phone, volunteerGood.phone);
    }

    @Test
    public void testCleaner2RowFrom3() {
        // Given
        Volunteer volunteerGood = new Volunteer("Pierre","POCHERON","Pedro","pedro@gmail.com","+33707080901");
        Volunteer volunteerBad = new Volunteer("Piere","P0CH3R0N","","pedro@gmail.com","+33707080901");
        Volunteer volunteerGood2 = new Volunteer("Remy","POTUS","","remy@gmail.com","00 00 00 00 00");

        List<Volunteer> listVolunteersInput = new ArrayList<>();
        List<Volunteer> listVolunteersOutput = new ArrayList<>();

        listVolunteersInput.add(volunteerGood);
        listVolunteersInput.add(volunteerBad);
        listVolunteersInput.add(volunteerGood2);

        // When
        listVolunteersOutput = Cleaner.cleanUp(listVolunteersInput);

        // Then
        assertEquals(2, listVolunteersOutput.size());
    }

    @Test
    public void sameEmailButDifferentPhoneMeansSameVolunteers(){
        // Given
        Volunteer volunteerGood = new Volunteer("Pierre","POCHERON","Pedro","pedro@gmail.com","+33707080901");
        Volunteer volunteerBad = new Volunteer("Pierre","POCHERON","Pedro","pedro@gmail.com","");

        List<Volunteer> listVolunteersInput = new ArrayList<>();
        List<Volunteer> listVolunteersOutput = new ArrayList<>();

        listVolunteersInput.add(volunteerGood);
        listVolunteersInput.add(volunteerBad);

        // When
        listVolunteersOutput = Cleaner.cleanUp(listVolunteersInput);

        // Then
        assertEquals(1, listVolunteersOutput.size());
        assertEquals(volunteerGood, listVolunteersOutput.get(0));
    }

    @Test
    public void samePhoneButDifferentMailMeansSameVolunteers() {
        // Given
        Volunteer volunteerGood = new Volunteer("Pierre", "POCHERON", "Pedro", "pedro@gmail.com", "+33707080901");
        Volunteer volunteerBad = new Volunteer("Pierre", "POCHERON", "Pedro", "", "+33707080901");

        List<Volunteer> listVolunteersInput = new ArrayList<>();
        List<Volunteer> listVolunteersOutput = new ArrayList<>();

        listVolunteersInput.add(volunteerGood);
        listVolunteersInput.add(volunteerBad);

        // When
        listVolunteersOutput = Cleaner.cleanUp(listVolunteersInput);

        // Then
        assertEquals(1, listVolunteersOutput.size());
        assertEquals(volunteerGood, listVolunteersOutput.get(0));
    }
}
