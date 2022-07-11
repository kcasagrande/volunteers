package org.example.volunteers;

import java.util.Arrays;
import java.util.regex.Matcher;

import static java.util.stream.Collectors.joining;
import java.util.regex.*;

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

    public boolean hasValidPhoneNumber() {
        if ( this.phone == null || this.phone == "" ) return false;
        return this.phone.matches("^\\+[1-9]\\d{1,14}$");
    }

    public boolean hasValidMail() {
        if ( this.eMail == null || this.eMail == "" ) return false;
        return this.eMail.matches("(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))");
    }

    public boolean hasFullName() {
        return this.firstName != null  && this.lastName != null && this.firstName != ""  && this.lastName != "";
    }
}
