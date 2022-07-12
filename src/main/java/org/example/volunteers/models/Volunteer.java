package org.example.volunteers.models;

import java.util.Arrays;

import static java.util.stream.Collectors.joining;

public final class Volunteer {
    private final String firstName;
    private final String lastName;
    private final String nickName;
    private final String email;
    private final String phone;

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
        this.email = eMail;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return Arrays.stream(new String[]{getFirstName(), getLastName(), getNickName(), getEmail(), getPhone()})
            .map(attribute -> String.format("\"%s\"", attribute))
            .collect(joining(";"));
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

}
