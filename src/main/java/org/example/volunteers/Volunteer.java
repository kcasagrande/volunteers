package org.example.volunteers;

import java.util.Arrays;

import static java.util.stream.Collectors.joining;

public final class Volunteer {
    public String firstName;
    public String lastName;
    public String nickName;
    public String email;
    public String phone;

    public Volunteer(
        String firstName,
        String lastName,
        String nickName,
        String email,
        String phone
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.email = email;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return Arrays.stream(new String[]{firstName,lastName,nickName, email,phone})
            .map(attribute -> String.format("\"%s\"", attribute))
            .collect(joining(";"));
    }
}
