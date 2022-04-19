package org.example.volunteers.utils;

import org.example.volunteers.Volunteer;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class VolunteerParser {

  public static List<Volunteer> listFromString(String volunteerString) {
    Pattern quotes = Pattern.compile("^\"([^\"]*)\"$");
    return volunteerString.lines()
        .map(string -> Arrays.stream(string.split(";", -1))
            .map(token -> quotes.matcher(token).replaceAll("$1"))
            .collect(toList())
        )
        .map(tokens -> new Volunteer(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4)))
        .collect(toList());
  }
}
