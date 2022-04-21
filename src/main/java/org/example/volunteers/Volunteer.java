package org.example.volunteers;

import org.example.utils.Levenshtein;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public final class Volunteer
{
    public static final int SIMILARITY_ACCEPTANCE = 1;

    public String firstName;
    public String lastName;
    public String nickName;
    public String eMail;
    public String phone;

    public Volunteer(
            String firstName,
            String lastName,
            String nickName,
            String eMail,
            String phone
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.eMail = eMail;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return Arrays.stream(new String[]{firstName,lastName,nickName,eMail,phone})
                .map(attribute -> String.format("\"%s\"", attribute))
                .collect(joining(";"));
    }

    public static List<Volunteer> transformList(String path) throws IOException {
        Pattern quotes = Pattern.compile("^\"([^\"]*)\"$");

        List<Volunteer> inputVolunteers = Files.readAllLines(Paths.get(path)).stream()
                .map(string -> Arrays.stream(string.split(";", -1))
                        .map(token -> quotes.matcher(token).replaceAll("$1"))
                        .collect(toList()))
                .map(tokens -> new Volunteer(tokens.get(0).toUpperCase(Locale.ROOT), tokens.get(1).toUpperCase(Locale.ROOT), tokens.get(2), tokens.get(3), tokens.get(4)))
                .collect(toList());
        return inputVolunteers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volunteer volunteer = (Volunteer) o;
        return Objects.equals(firstName, volunteer.firstName) && Objects.equals(lastName, volunteer.lastName) && Objects.equals(nickName, volunteer.nickName) && Objects.equals(eMail, volunteer.eMail) && Objects.equals(phone, volunteer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    public String parsedPhoneNumber()
    {
        return this.phone
                .replace(".", "")
                .replace("-", "")
                .replace(" ", "")
                .replace("+33(0)", "0")
                .replace("+33", "0");
    }
}

