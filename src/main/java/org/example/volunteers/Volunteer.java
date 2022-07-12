package org.example.volunteers;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public final class Volunteer {
    public Number id;
    public String firstName;
    public String lastName;
    public String nickName;
    public String eMail;
    public String phone;

    public int score = 0;

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

    public Volunteer(
            Number id,
            String firstName,
            String lastName,
            String nickName,
            String eMail,
            String phone
    ) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setNickName(nickName);
        setEMail(eMail);
        setPhone(phone);
    }

    public String getFullName () {
        return getFirstName().toLowerCase(Locale.ROOT).trim() + getLastName().toLowerCase(Locale.ROOT).trim();
    }

    @Override
    public String toString() {
        return Arrays.stream(new String[]{firstName,lastName,nickName,eMail,phone})
            .map(attribute -> String.format("\"%s\"", attribute))
            .collect(joining(";"));
    }

    public int fiabilityScore() {
        int newScore = 0;

        if (this.firstName != "") newScore++;
        if (this.lastName != "") newScore++;
        if (this.nickName != "") newScore++;
        if (this.eMail != "") newScore++;
        if (this.phone != "") newScore++;

        this.score = newScore;

        return this.score;
    }

    public Number getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getEMail() {
        return this.eMail;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setId(Number id){
        this.id = id;
    }

    public void setFirstName(String firstname){
        this.firstName = firstname;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public void setEMail(String email){
        this.eMail = email;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
}
