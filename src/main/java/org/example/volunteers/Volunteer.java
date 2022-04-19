package org.example.volunteers;

import java.util.Arrays;
import java.util.List;

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return Arrays.stream(new String[]{firstName,lastName,nickName,eMail,phone})
            .map(attribute -> String.format("\"%s\"", attribute))
            .collect(joining(";"));
    }

    public Boolean compare(Volunteer volunteer) {
        return (
            Compare.compareMails(this.eMail, volunteer.eMail) &&
            Compare.comparePhones(this.phone, volunteer.phone) &&
            Compare.compareStrings(this.firstName, volunteer.firstName) &&
            Compare.compareStrings(this.lastName, volunteer.lastName) &&
            Compare.compareStrings(this.nickName, volunteer.nickName)
        );
    }
}
