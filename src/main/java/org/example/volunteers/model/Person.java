package org.example.volunteers.model;

public class Person {
    String name;
    String surname;
    String nickname;
    String email;
    String phoneNumber;

    public Person(String name, String surname, String nickname, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {

        return name +
                " " +
                surname +
                " " +
                nickname +
                " " +
                email +
                " " +
                phoneNumber;
    }
}
