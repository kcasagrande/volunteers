package org.example.volunteers;

import java.util.Arrays;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public final class Volunteer {
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
        this.phone = Utils.convertPhone(phone);
    }

    public boolean isSameThan(Volunteer volunteerToCompare) {
        if (!this.phone.isEmpty() && this.phone.equalsIgnoreCase(volunteerToCompare.phone)) {
            return true;
        }

        if (!this.eMail.isEmpty() && this.eMail.equalsIgnoreCase(volunteerToCompare.eMail)) {
            return true;
        }
        return false;
    }

    public void fillInformations(Volunteer volunteerForFill) {
        if(this.phone == null || this.phone.equalsIgnoreCase("")) {
            this.phone = volunteerForFill.phone;
        }
        if(this.eMail == null || this.eMail.equalsIgnoreCase("")) {
            this.eMail = volunteerForFill.eMail;
        }
        if(this.nickName == null || this.nickName.equalsIgnoreCase("")) {
            this.nickName = volunteerForFill.nickName;
        }
        if(this.firstName == null || this.firstName.equalsIgnoreCase("")) {
            this.firstName = volunteerForFill.firstName;
        }
        if(this.lastName == null || this.lastName.equalsIgnoreCase("")) {
            this.lastName = volunteerForFill.lastName;
        }
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
