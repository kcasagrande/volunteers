package org.example.volunteers;


import java.text.Normalizer;
import java.util.Arrays;

import static java.util.stream.Collectors.joining;

public final class Volunteer {
    public final String firstName;
    public final String lastName;
    public final String nickName;
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
        this.phone = phone;
    }

    @Override
    public String toString() {
        return Arrays.stream(new String[]{firstName, lastName, nickName, eMail, phone})
                .map(attribute -> String.format("\"%s\"", attribute))
                .collect(joining(";"));
    }

    public String getEmail() {
        return eMail;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstName() {
        String first = Normalizer.normalize(firstName, Normalizer.Form.NFD);
        first = first.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toUpperCase();
        return first;
//        return firstName.toUpperCase();
    }

    public String getLastName() {
        String last = Normalizer.normalize(lastName, Normalizer.Form.NFD);
        last = last.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toUpperCase();
        return last;
//        return lastName.toUpperCase();
    }

    public String getFullName() {
        String first = Normalizer.normalize(firstName, Normalizer.Form.NFD);
        first = first.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toUpperCase();

        String last = Normalizer.normalize(lastName, Normalizer.Form.NFD);
        last = last.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toUpperCase();

        return first + " " + last;

//        return firstName.toUpperCase() + " " + lastName.toUpperCase();
    }
    public String getReverseFullName() {
        String first = Normalizer.normalize(firstName, Normalizer.Form.NFD);
        first = first.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toUpperCase();

        String last = Normalizer.normalize(lastName, Normalizer.Form.NFD);
        last = last.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toUpperCase();

        return last + " " + first;

//        return firstName.toUpperCase() + " " + lastName.toUpperCase();
    }

    public String getNickName() {
        String nick = Normalizer.normalize(nickName, Normalizer.Form.NFD);
        nick = nick.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toUpperCase();
        return nick;
//        return nickName.toUpperCase();
    }

}
