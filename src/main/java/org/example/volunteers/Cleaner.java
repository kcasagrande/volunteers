package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cleaner {
    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.

        for (Volunteer volunteer : volunteers) {
            if (Objects.equals(volunteer.nickName, "")) {
                volunteer.nickName = volunteer.firstName;
            }
        }
        return new ArrayList<>(volunteers);
    }
}
