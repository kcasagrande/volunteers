package org.example.volunteers;

import java.util.Arrays;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public final class Volunteer {
    public final String firstName;
    public String lastName;
    public final String nickName;
    public final String eMail;
    public final String phone;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volunteer volunteer = (Volunteer) o;
        return Objects.equals(firstName, volunteer.firstName) && Objects.equals(lastName, volunteer.lastName) && Objects.equals(nickName, volunteer.nickName) && Objects.equals(eMail, volunteer.eMail) && Objects.equals(phone, volunteer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, nickName, eMail, phone);
    }
}
