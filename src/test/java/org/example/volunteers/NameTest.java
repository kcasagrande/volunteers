package org.example.volunteers;

import org.example.volunteers.models.Volunteer;
import org.example.volunteers.services.Cleaner;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
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

        Volunteer malformedVolunteerFName = new Volunteer("", "Dupont", "MD", "marine.dupont@test.fr", "+33600000000");
        Volunteer normal = new Volunteer("Marine", "Dupont", "MDP", "marine.dupont2@test.fr", "+33670000000");
        List<Volunteer> volunteers = Arrays.asList(malformedVolunteerFName, normal);

        Cleaner c = new Cleaner(volunteers);
        c.checkNames();

        Assertions.assertEquals(1, c.nameValidator.malformedNames.size(), "un prénom malformé a été trouvé");

    }

    @Test
    public void shouldReturnListWithEmptyLName(){

        Volunteer malformedVolunteerLName = new Volunteer("Marine", "", "MD", "marine.dupont@test.fr", "+33600000000");
        Volunteer normal = new Volunteer("Marine", "Dupont", "MDP", "marine.dupont2@test.fr", "+33670000000");
        List<Volunteer> volunteers = Arrays.asList(malformedVolunteerLName, normal);

        Cleaner c = new Cleaner(volunteers);
        c.checkNames();

        Assertions.assertEquals(1, c.nameValidator.malformedNames.size(), "un nom malformé a été trouvé");

    }

    @Test
    public void shouldReturnListWithMalformedFName(){

        Volunteer malformedVolunteerFName = new Volunteer("Mar^in$e", "Dupont", "MD", "marine.dupont@test.fr", "+33600000000");
        Volunteer normal = new Volunteer("Marine", "Dupont", "MDP", "marine.dupont2@test.fr", "+33670000000");
        List<Volunteer> volunteers = Arrays.asList(malformedVolunteerFName, normal);

        Cleaner c = new Cleaner(volunteers);
        c.checkNames();

        Assertions.assertEquals(1, c.nameValidator.malformedNames.size(), "un ");

    }

    @Test
    public void shouldReturnListWithMalformedLName(){

        Volunteer malformedVolunteerLName = new Volunteer("Marine", "D$up#ont", "MD", "marine.dupont@test.fr", "+33600000000");
        Volunteer normal = new Volunteer("Marine", "Dupont", "MDP", "marine.dupont2@test.fr", "+33670000000");
        List<Volunteer> volunteers = Arrays.asList(malformedVolunteerLName, normal);

        Cleaner c = new Cleaner(volunteers);
        c.checkNames();

        Assertions.assertEquals(1, c.nameValidator.malformedNames.size(), "un ");

    }

    @Test
    public void shouldReturnListWithDuplicatedNames(){

        List<Volunteer> volunteers = new ArrayList<>();
        Volunteer malformedVolunteerFName = new Volunteer("Marine", "Dupont", "MD", "marine.dupont@test.fr", "+33600000000");
        volunteers.add(new Volunteer("Marine", "Dupont", "MDP", "marine.dupont@test.fr", "+33670000000"));
        volunteers.add(malformedVolunteerFName);

        Cleaner c = new Cleaner(volunteers);
        c.checkNames();
        HashMap<String, List<Volunteer>> result = c.nameValidator.duplicateName;


        Assertions.assertEquals(1, result.size(), "Un doublon a été trouvé");

    }

    @Test
    public void shouldNotReturnMapWithDuplicateName() {

        List<Volunteer> volunteers = new ArrayList<>();
        Volunteer malformedVolunteerFName = new Volunteer("Marine", "Dupont", "MD", "marine.dupont@test.fr", "+33600000000");
        volunteers.add(new Volunteer("Marine", "Dupont", "MDP", "mdp@test.fr", "+33670000000"));
        volunteers.add(malformedVolunteerFName);

        Cleaner c = new Cleaner(volunteers);
        c.checkNames();
        HashMap<String, List<Volunteer>> result = c.nameValidator.duplicateName;


        Assertions.assertEquals(0, result.size(), "Aucun doublon n'a été trouvé");

    }
}
