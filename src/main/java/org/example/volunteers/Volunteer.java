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
        this.firstName = this.formatString(firstName);
        this.lastName = this.formatString(lastName);
        this.nickName = this.formatString(nickName);
        this.eMail = this.formatString(eMail);
        this.phone = this.formatPhone(phone);
    }

    @Override
    public String toString() {
        return Arrays.stream(new String[]{firstName,lastName,nickName,eMail,phone})
            .map(attribute -> String.format("\"%s\"", attribute))
            .collect(joining(";"));
    }

    public boolean sameEmail(String email) {
        return Objects.equals(formatString(this.eMail), formatString(email));
    }
    public boolean sameFirstName(String firstName) {
        return Objects.equals(formatString(this.firstName), formatString(firstName));
    }
    public boolean sameLastName(String lastName) {
        return Objects.equals(formatString(this.lastName), formatString(lastName));
    }
    public boolean samePhone(String phone) {
        return Objects.equals(formatPhone(this.phone), formatPhone(phone));
    }
    public boolean sameNickname(String nickName) {
        return Objects.equals(formatString(this.nickName), formatString(nickName));
    }

    public String formatPhone(String phone){
        return phone
                .replace(" ", "")
                .replace(".", "")
                .replace("-", "")
                .replace("+33(0)", "0")
                .replace("+33", "0");
    }

    public String formatString(String string){
        return string
                .toLowerCase();
    }

    public boolean compare(Volunteer user) {
        return this.sameNickname(user.nickName) || (this.sameFirstName(user.firstName) && this.sameLastName(user.lastName)) || this.samePhone(user.phone) || this.sameEmail(user.eMail);
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if(!(obj instanceof Volunteer)){
            return false;
        }
        Volunteer user = (Volunteer) obj;
        return lastName.equals(user.lastName) &&
                firstName.equals(user.firstName) &&
                nickName.equals(user.nickName) &&
                eMail.equals(user.eMail) &&
                phone.equals(user.phone);
    }
}
