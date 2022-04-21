package org.example.volunteers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Utils {
    public static void assertArraysVolunteers(List<Volunteer> volunteersExpected, List<Volunteer> volunteers) {
        System.out.println(volunteersExpected.toString());
        System.out.println(volunteers.toString());
        assertEquals(volunteersExpected.toString(), volunteers.toString());
    }
}
