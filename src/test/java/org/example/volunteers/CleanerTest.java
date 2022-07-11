package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CleanerTest {

    @Test
    public void shouldRemoveDuplicateWhenAllDataAreTheSame() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Nom", "Prenom", "pseudo", "email@gmail.com", "+33600000000"));
        volunteers.add(new Volunteer("Nom", "Prenom", "pseudo", "email@gmail.com", "+33600000000"));
        volunteers.add(new Volunteer("Nom2", "Prenom2", "pseudo2", "email2@gmail.com", "+33600000002"));
        volunteers.add(new Volunteer("Nom3", "Prenom3", "pseudo3", "email3@gmail.com", "+33600000003"));
        volunteers.add(new Volunteer("Nom2", "Prenom2", "pseudo2", "email2@gmail.com", "+33600000002"));

        List<Volunteer> cleanedVolunteers = Cleaner.removeDuplicate(volunteers);

        assertEquals(3, cleanedVolunteers.size(), "La liste nettoyée doit être de taille 3");
    }

//    @Test
//    public void testRemovedDuplicateVerifyFirstNameLastNameNicknamePseudoMailPhone() {
//
//        List<Volunteer> volunteers = new ArrayList<>();
//        volunteers.add(new Volunteer("doe", "john", "jojo2", "john@mail.com", "+33698675434"));
//        volunteers.add(new Volunteer("doe", "john", "jojo", "john@mail.com", "+33698675434"));
//        volunteers.add(new Volunteer("doe", "john", "jojo", "john@mail.com", "+33698675434"));
//
//        List<Volunteer> result = Cleaner.removeDuplicateFirstNameLastNamePseudoMailPhone(volunteers);
//
//        assertEquals(2, result.size());
//    }

}
