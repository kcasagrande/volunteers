package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuplicateTest {
    @Test
    public void removeDuplicateVerifyFirstNameLastNameNicknamePseudoMailPhone() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("john", "doe", "jojo2", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675434"));

        List<Volunteer> result = Duplicate.removeDuplicateFirstNameLastNamePseudoMailPhone(volunteers);

        assertEquals(2, result.size(), "La liste ne doit pas garder le doublon avec le nickName jojo car ils ont des données exactement similaires");
    }

    @Test
    public void removeDuplicateVerifyFirstNameInsteadOfLastName() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("john", "doe", "jojo2", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("doe", "john", "jojo", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675434"));

        List<Volunteer> result = Duplicate.removeDuplicateFirstNameLastNamePseudoMailPhone(volunteers);

        assertEquals(2, result.size(), "La liste ne doit pas garder le doublon avec le nickName jojo car ils ont des données similaires avec leur nom/prénom inversés");
    }

    @Test
    public void removeDuplicateVerifyMailPhone() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("john", "doe", "jojo2", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675439"));

        List<Volunteer> result = Duplicate.removeDuplicateMailPhone(volunteers);

        assertEquals(2, result.size(), "La liste ne doit pas garder le doublon sur le téléphone +33698675434 car le numéro de téléphone est similaire");
    }

    @Test
    public void concatDuplicateMail() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john1@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john2@mail.com", "+33698675434"));

        List<Volunteer> volunteersResult = Duplicate.concatDuplicateMailPhone(volunteers);

        assertEquals(1, volunteersResult.size(), "La taille du tableau devrait etre 1");
        assertEquals("john@mail.com,john1@mail.com,john2@mail.com", volunteersResult.get(0).eMail, "Les emails devraient etre concatenes en 'john@mail.com,john1@mail.com,john2@mail.com'");
    }

    @Test
    public void concatDuplicatePhone() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675431"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675432"));
        volunteers.add(new Volunteer("john", "doe", "jojo", "john@mail.com", "+33698675433"));

        List<Volunteer> volunteersResult = Duplicate.concatDuplicateMailPhone(volunteers);

        assertEquals(1, volunteersResult.size(), "La taille du tableau devrait etre 1");
        assertEquals("+33698675431,+33698675432,+33698675433", volunteersResult.get(0).phone, "Les numéros de téléphone devraient etre concatenes en '+33698675431,+33698675432,+33698675433'");
    }
}
