package org.example.volunteers;

import java.util.Arrays;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public final class Volunteer {
    public final String firstName;
    public final String lastName;
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

    public boolean sameEmail(String email) {
        return Objects.equals(this.eMail, email);
    }
    public boolean sameFirstName(String firstName) {
        return Objects.equals(this.firstName, firstName);
    }
    public boolean sameLastName(String lastName) {
        return Objects.equals(this.lastName, lastName);
    }
    public boolean samePhone(String phone) {
        return Objects.equals(this.phone, phone);
    }
    public boolean sameNickname(String nickName) {
        return Objects.equals(this.nickName, nickName);
    }
}
