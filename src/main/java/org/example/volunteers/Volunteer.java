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
    
    @Override
    public String toString() {
        return Arrays.stream(new String[]{firstName,lastName,nickName, email,phone})
            .map(attribute -> String.format("\"%s\"", attribute))
            .collect(joining(";"));
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public Boolean hasName() {
        return !this.firstName.isBlank() || !this.lastName.isBlank();
    }

    public boolean hasSameName(Volunteer volunteerToCompare) {
        return (
            this.firstName.equals(volunteerToCompare.firstName) && this.lastName.equals(volunteerToCompare.lastName)
                || this.firstName.equals(volunteerToCompare.lastName) && this.lastName.equals(volunteerToCompare.firstName)
        );
    }

    public boolean hasSameCredentials(Volunteer volunteerToCompare) {
        return (
            this.nickName.equals(volunteerToCompare.nickName)
                || this.email.equals(volunteerToCompare.email)
                || this.phone.equals(volunteerToCompare.phone)
        );
    }
}
