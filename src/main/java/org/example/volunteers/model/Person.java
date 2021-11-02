package org.example.volunteers.model;

/**
 * Person POJO
 */
public class Person {
    String name;
    String surname;
    String nickname;
    String email;
    String phoneNumber;

    /**
     * Person constructor
     * @param name Person's name
     * @param surname Person's surname
     * @param nickname Person's nickname
     * @param email Person's mail address
     * @param phoneNumber Person's phone number
     */
    public Person(String name, String surname, String nickname, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Overrided toString method to print person in console
     * @return Structured string to print fields value
     */
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

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Person setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
