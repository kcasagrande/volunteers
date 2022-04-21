package org.example.volunteers;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public class Volunteer {
    private String firstName;
    private String lastName;
    private String nickName;
    private String eMail;
    private String phone;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Volunteer(String firstName, String lastName, String nickName, String eMail, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.eMail = eMail;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return Arrays.stream(new String[]{firstName, lastName, nickName, eMail, phone}).map(attribute -> String.format("\"%s\"", attribute)).collect(joining(";"));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (obj.getClass() != this.getClass()) return false;

        final Volunteer other = (Volunteer) obj;
        final String thisFirstName = this.firstName.toLowerCase(Locale.FRANCE);
        final String otherFirstName = other.firstName.toLowerCase(Locale.FRANCE);
        final String thisLastName = this.lastName.toLowerCase(Locale.FRANCE);
        final String otherLastName = other.lastName.toLowerCase(Locale.FRANCE);
        final String thisNickName = this.nickName.toLowerCase(Locale.FRANCE);
        final String otherNickName = other.nickName.toLowerCase(Locale.FRANCE);
        final String thisEmail = this.formatEmail(this.eMail);
        final String otherEmail = this.formatEmail(other.eMail);
        final String thisPhone = this.formatPhone(this.phone);
        final String otherPhone = this.formatPhone(other.phone);

        if (Objects.equals(otherFirstName, thisFirstName) && Objects.equals(otherLastName, thisLastName) && Objects.equals(otherNickName, thisNickName) && Objects.equals(otherEmail, thisEmail) && Objects.equals(otherPhone, thisPhone)) {
            return true;
        }

        if (Objects.equals(otherFirstName, thisLastName) && Objects.equals(otherLastName, thisFirstName) && Objects.equals(otherNickName, thisNickName) && Objects.equals(otherEmail, thisEmail) && Objects.equals(otherPhone, thisPhone)) {
            return true;
        }

        if (Objects.equals(otherFirstName, thisFirstName) && Objects.equals(otherLastName, thisLastName) && Objects.equals(otherNickName, thisNickName) && Objects.equals(otherEmail, thisEmail)) {
            return true;
        }

        return false;
    }

    public String formatPhone(String phone) {
        if (phone.length() > 0 && phone.charAt(0) == '0')
            phone = "+33" + phone.substring(1);

        return phone.replaceAll("([.]|-| |\\(|\\))", "");
    }

    public String formatEmail(String email) {
        email = email.replaceAll("(.fr|.org|.net|.com)$", "");
        return email.replaceAll("([.]|_| )", "");
    }
}
