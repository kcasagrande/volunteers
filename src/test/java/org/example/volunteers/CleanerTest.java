package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CleanerTest {




    @Test
    public void testRemovedDuplicateVerifyFirstNameLastNameNicknamePseudoMailPhone() {

        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("doe", "john", "jojo2", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("doe", "john", "jojo", "john@mail.com", "+33698675434"));
        volunteers.add(new Volunteer("doe", "john", "jojo", "john@mail.com", "+33698675434"));

        List<Volunteer> result = Cleaner.removeDuplicateFirstNameLastNamePseudoMailPhone(volunteers);

        assertEquals(2, result.size());
    }



}
