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
}
