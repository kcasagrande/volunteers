package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DuplicateTest  {

    @Test
    public void trieByName() {
        List<Volunteer> listVolunteers = new ArrayList<>();
        listVolunteers.add(new Volunteer("Miler", "Julie", "", "", "0600000000"));
        listVolunteers.add(new Volunteer("MILER", "Julie", "Juju", "juju@gmail.com", ""));

        listVolunteers.add(new Volunteer("Blanc", "Jean", "", "jean@gmail.com", ""));
        listVolunteers.add(new Volunteer("BLANC", "Marie", "marie", "", ""));

        listVolunteers.add(new Volunteer("", "", "", "test@gmail.com", ""));
        listVolunteers.add(new Volunteer("", "", "", "essai@gmail.com", ""));

        List<List<Volunteer>> finalListVolunteers = Duplicate.triByName(listVolunteers);

        List<Volunteer> listResultat = new ArrayList<>();
        List<List<Volunteer>> listOfResultat = new ArrayList<>();

        listResultat.add(new Volunteer("Miler", "Julie", "", "", "0600000000"));
        listResultat.add(new Volunteer("MILER", "Julie", "Juju", "juju@gmail.com", ""));
        listOfResultat.add(listResultat);
        listResultat = new ArrayList<>();
        listResultat.add(new Volunteer("Blanc", "Jean", "", "jean@gmail.com", ""));
        listOfResultat.add(listResultat);

        listResultat = new ArrayList<>();
        listResultat.add(new Volunteer("BLANC", "Marie", "marie", "", ""));
        listOfResultat.add(listResultat);

        listResultat = new ArrayList<>();
        listResultat.add(new Volunteer("", "", "", "test@gmail.com", ""));
        listOfResultat.add(listResultat);

        listResultat = new ArrayList<>();
        listResultat.add(new Volunteer("", "", "", "essai@gmail.com", ""));
        listOfResultat.add(listResultat);


        assertArrayEquals(finalListVolunteers.toArray(), listOfResultat.toArray());
    }

    @Test
    public void testDuplicateByName() {
        List<Volunteer> listVolunteers = new ArrayList<>();
        listVolunteers.add(new Volunteer("Miler", "Julie", "Juju", "juju02@gmail.com", "0600000000"));
        listVolunteers.add(new Volunteer("MILER", "Julie", "ju", "juju@gmail.com", ""));

        listVolunteers.add(new Volunteer("Blanc", "Jean", "", "jean@gmail.com", ""));
        listVolunteers.add(new Volunteer("BLANC", "Marie", "marie", "", ""));

        listVolunteers.add(new Volunteer("", "", "", "test@gmail.com", ""));
        listVolunteers.add(new Volunteer("", "", "", "essai@gmail.com", ""));

        List<Volunteer> finalListVolunteers = Duplicate.regroupByName(listVolunteers);

        List<Volunteer> listResultat = new ArrayList<>();
        listResultat.add(new Volunteer("Miler", "Julie", "Juju ju", "juju02@gmail.com juju@gmail.com", "0600000000"));
        listResultat.add(new Volunteer("Blanc", "Jean", "", "jean@gmail.com", ""));
        listResultat.add(new Volunteer("BLANC", "Marie", "marie", "", ""));
        listResultat.add(new Volunteer("", "", "", "test@gmail.com", ""));
        listResultat.add(new Volunteer("", "", "", "essai@gmail.com", ""));

        assertArrayEquals(finalListVolunteers.toArray(), listResultat.toArray());
    }

    @Test
    public void testDuplicateByLevenshtein() {
        List<Volunteer> listVolunteers = new ArrayList<>();
        listVolunteers.add(new Volunteer("Miler", "Julie", "Juju", "", "0600000000"));
        listVolunteers.add(new Volunteer("MILIR", "Julie", "", "juju@gmail.com", "0600000000"));
        listVolunteers.add(new Volunteer("Blanc", "Jean", "", "jean@gmail.com", "0601000000"));
        listVolunteers.add(new Volunteer("BLANC", "Jeann", "Jean", "jean02@gmail.com", "0601000000"));
        List<Volunteer> finalListVolunteers = Duplicate.duplicateByLevenshtein(listVolunteers);

        List<Volunteer> listResultat = new ArrayList<>();
        listResultat.add(new Volunteer("Miler", "Julie", "Juju", "juju@gmail.com", "0600000000"));
        listResultat.add(new Volunteer("Blanc", "Jean", "Jean", "jean@gmail.com jean02@gmail.com", "0601000000"));

        assertArrayEquals(finalListVolunteers.toArray(), listResultat.toArray());
    }
}