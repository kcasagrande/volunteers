package org.example.volunteers;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import static java.util.stream.Collectors.joining;
import java.util.regex.*;

public final class Group {
    public final Integer id;
    public List<Volunteer> volunteers;

    public Group(
            Integer id,
            List<Volunteer> volunteers
    ) {
        this.id = id;
        this.volunteers = volunteers;
    }
}
