package org.example.volunteers.user;

public class User {
    public String surname, name, pseudo, mail, tel;

    public User(String surname, String name, String pseudo, String mail, String tel) {
        this.surname = surname;
        this.name = name;
        this.pseudo = pseudo;
        this.mail = mail;
        this.tel = tel;
    }



    @Override
    public String toString() {
        return "User{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", mail='" + mail + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
