package org.example.volunteers;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VolunteerCleaner {
    private final Volunteer volunteer;

    public VolunteerCleaner(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public int countUsers(@NotNull List<String> usersList) {
        return usersList.size();
    }


}
