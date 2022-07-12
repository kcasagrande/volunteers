package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuplicatesCleanerTest {


    /*SI nom et prenom identique mais inversé
    Distance de levenshtein
    */

    @Test
    public void doesRemoveAllDuplicateIfThereTwoSameVolunteer() {
        List<Volunteer> duplicatesVolunteers = new ArrayList<Volunteer>();
        duplicatesVolunteers.add(new Volunteer("Firstname1", "Lastname1", "Nickname1", "email@test.com", "+33000000000"));
        duplicatesVolunteers.add(new Volunteer("Firstname1", "Lastname1", "Nickname1", "email@test.com", "+33000000000"));

        List<Volunteer> expectedVolunteers = new ArrayList<Volunteer>();
        expectedVolunteers.add(new Volunteer("Firstname1", "Lastname1", "Nickname1", "email@test.com", "+33000000000"));

        givenVolunteers(duplicatesVolunteers);
        whenCleaningUpVolunteers();
        thenVolunteersAre(expectedVolunteers);
    }

    @Test
    public void doesNotRemoveVolunteersWhenOnlyFirstAndLastNameAreDuplicates() {
        List<Volunteer> duplicatesVolunteers = new ArrayList<Volunteer>();
        duplicatesVolunteers.add(new Volunteer("Firstname1", "Lastname1", "Nickname1", "email1@test.com", "+33000000000"));
        duplicatesVolunteers.add(new Volunteer("Firstname1", "Lastname1", "Nickname1", "email2@test.com", "+33000000000"));

        List<Volunteer> expectedVolunteers = new ArrayList<Volunteer>();
        expectedVolunteers.add(new Volunteer("Firstname1", "Lastname1", "Nickname1", "email1@test.com", "+33000000000"));
        expectedVolunteers.add(new Volunteer("Firstname1", "Lastname1", "Nickname1", "email2@test.com", "+33000000000"));

        givenVolunteers(duplicatesVolunteers);
        whenCleaningUpVolunteers();
        thenVolunteersAre(expectedVolunteers);
    }

    private void givenVolunteers(List<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }

    private void whenCleaningUpVolunteers() {
        cleanedVolunteers = Cleaner.cleanUp(volunteers);
    }

    private void thenVolunteersAre(List<Volunteer> expectedVolunteers) {
        assertEquals(expectedVolunteers.toString(), cleanedVolunteers.toString());
    }

    private List<Volunteer> volunteers = new ArrayList<Volunteer>();
    private List<Volunteer> cleanedVolunteers = new ArrayList<Volunteer>();
}
