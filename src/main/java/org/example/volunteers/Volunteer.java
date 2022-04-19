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
        String lastName,
        String firstName,
        String nickName,
        String eMail,
        String phone
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.eMail = eMail;
        this.phone = this.formatPhone(phone);
    }

    @Override
    public String toString() {
        return Arrays.stream(new String[]{firstName,lastName,nickName,eMail,phone})
            .map(attribute -> String.format("\"%s\"", attribute))
            .collect(joining(";"));
    }

    public boolean sameEmail(String email) {
        return this.eMail.equals(email);
    }
    public boolean sameFirstName(String firstName) {
        return this.firstName.equals(firstName);
    }
    public boolean sameLastName(String lastName) {
        return this.lastName.equals(lastName);
    }
    public boolean samePhone(String phone) {
        return Objects.equals(formatPhone(this.phone), formatPhone(phone));
    }
    public boolean sameNickname(String nickName) {
        return this.nickName.equals(nickName);
    }

    public String formatPhone(String phone){
        return phone
                .replace(" ", "")
                .replace(".", "")
                .replace("-", "")
                .replace("+33(0)", "0")
                .replace("+33", "0");
    }

    public boolean compare(Volunteer user) {
        return this.sameNickname(user.nickName) || (this.sameFirstName(user.firstName) && this.sameLastName(user.lastName)) || this.samePhone(user.phone) || this.sameEmail(user.eMail);
    }
}
