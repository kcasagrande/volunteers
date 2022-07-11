package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemovingDuplicates {

    @Test
    public void removePerfectDuplicates(){
        Volunteer v1 = new Volunteer("Jean", "Dupond", "JDupond", "jean.dupond@gmail.com", "+33012345678");
        Volunteer v2 = new Volunteer("Jean", "Dupond", "JDupond", "jean.dupond@gmail.com", "+33012345678");
        List<Volunteer> testList = Arrays.asList(new Volunteer[]{v1, v2});

        List<Volunteer> expectedResult = Arrays.asList(new Volunteer[]{v1});

        List<Volunteer> actualResult = Cleaner.removeDuplicates(testList);

        assertEquals(expectedResult, actualResult, "Les doubles parfait doivent être supprimés");
    }
}
