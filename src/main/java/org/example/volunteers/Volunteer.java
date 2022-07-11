package org.example.volunteers;

import java.util.Arrays;

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

    @Override
    public int hashCode() {
        return (this.firstName.hashCode() + this.lastName.hashCode() + this.nickName.hashCode() + this.eMail.hashCode() + this.phone.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Volunteer)
        {
            Volunteer temp = (Volunteer) obj;
            if(this.firstName.equals(temp.firstName) && this.lastName.equals(temp.lastName) && this.nickName.equals(temp.nickName) && this.eMail.equals(temp.eMail) && this.phone.equals(temp.phone))
                return true;
        }
        return false;
    }
}
