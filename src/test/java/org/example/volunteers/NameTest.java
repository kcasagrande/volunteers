package org.example.volunteers;

import org.example.volunteers.models.Volunteer;
import org.example.volunteers.services.Cleaner;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NameTest {
    @BeforeAll
    public static void globalSetUp() {
        //System.out.println("Ce code est exécuté une seule fois avant l'ensemble des tests");

    }

    @BeforeEach
    public void setUp() {
        //System.out.println("Ce code est exécuté avant chaque test");
    }

    @Test
    public void shouldReturnListWithEmptyFName(){

        Volunteer normalVolunteer = new Volunteer("Marine", "Dupont", "MDP", "marine.dupont2@test.fr", "+33670000000");
        Volunteer malformedVolunteerFName = new Volunteer("", "Dupont", "MD", "marine.dupont@test.fr", "+33600000000");
        List<Volunteer> volunteers = Arrays.asList(malformedVolunteerFName, normalVolunteer);

        Cleaner c = new Cleaner(volunteers);
        c.checkNames();

        Assertions.assertEquals(1, c.nameValidator.noNames.size(), "un prénom null ou vide a été trouvé");

    }

    @Test
    public void shouldReturnListWithEmptyLName(){

        Volunteer normalVolunteer = new Volunteer("Marine", "Dupont", "MDP", "marine.dupont2@test.fr", "+33670000000");
        Volunteer malformedVolunteerLName = new Volunteer("Marine", "", "MD", "marine.dupont@test.fr", "+33600000000");
        List<Volunteer> volunteers = Arrays.asList(malformedVolunteerLName, normalVolunteer);

        Cleaner c = new Cleaner(volunteers);
        c.checkNames();

        Assertions.assertEquals(1, c.nameValidator.noNames.size(), "un nom null ou vide a été trouvé");
    }

    @Test
    public void shouldReturnListWithMalformedFName(){

        Volunteer normalVolunteer = new Volunteer("Marine", "Dupont", "MDP", "marine.dupont2@test.fr", "+33670000000");
        Volunteer malformedVolunteerFName = new Volunteer("Mar^in$e", "Dupont", "MD", "marine.dupont@test.fr", "+33600000000");
        List<Volunteer> volunteers = Arrays.asList(malformedVolunteerFName, normalVolunteer);

        Cleaner c = new Cleaner(volunteers);
        c.checkNames();

        Assertions.assertEquals(1, c.nameValidator.malformedNames.size(), "un prénom malformé a été trouvé");
    }

    @Test
    public void shouldReturnListWithMalformedLName(){

        Volunteer normalVolunteer = new Volunteer("Marine", "Dupont", "MDP", "marine.dupont2@test.fr", "+33670000000");
        Volunteer malformedVolunteerLName = new Volunteer("Marine", "D$up#ont", "MD", "marine.dupont@test.fr", "+33600000000");
        List<Volunteer> volunteers = Arrays.asList(malformedVolunteerLName, normalVolunteer);

        Cleaner c = new Cleaner(volunteers);
        c.checkNames();

        Assertions.assertEquals(1, c.nameValidator.malformedNames.size(), "un nom malformé a été trouvé");
    }

    @Test
    public void shouldReturnListWithDuplicatedNames(){

        Volunteer normalVolunteer = new Volunteer("Marine", "Dupont", "MDP", "marine.dupont@test.fr", "+33670000000");
        Volunteer duplicateVolunteer = new Volunteer("Marine", "Dupont", "MD", "marine.dupont@test.fr", "+33600000000");
        List<Volunteer> volunteers = Arrays.asList(duplicateVolunteer, normalVolunteer);

        Cleaner c = new Cleaner(volunteers);
        c.checkNames();
        HashMap<String, List<Volunteer>> result = c.nameValidator.duplicateName;

        Assertions.assertEquals(1, result.size(), "Un doublon a été trouvé");
    }

    @Test
    public void shouldNotReturnMapWithDuplicateName() {

        Volunteer normalVolunteer = new Volunteer("Marine", "Dupont", "MDP", "mdp@test.fr", "+33670000000");
        Volunteer duplicateVolunteerWIthDifferentEmail = new Volunteer("Marine", "Dupont", "MD", "marine.dupont@test.fr", "+33600000000");
        Volunteer volunteerWithFNameAndLNameReversed = new Volunteer("Dupont", "Marine", "MD", "marine.dupont@test.fr", "+33600000000");
        List<Volunteer> volunteers = Arrays.asList(duplicateVolunteerWIthDifferentEmail, normalVolunteer, volunteerWithFNameAndLNameReversed);

        Cleaner c = new Cleaner(volunteers);
        c.checkNames();
        List<Volunteer> result = c.nameValidator.duplicateName.get("Marine.Dupont");

        Assertions.assertEquals(2, result.size(), "Des doublons ont été trouvés");
    }
}
