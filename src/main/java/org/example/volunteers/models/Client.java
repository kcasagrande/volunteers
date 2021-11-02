package org.example.volunteers.models;

public class Client {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phone;

    public Client(
        String firstname,
        String lastname,
        String username,
        String email,
        String phone
    ) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.phone = phone;

        normalizePhoneNumber();
    }

    public String getFirstname() {return firstname;}

    public String getLastname() {return lastname;}

    public String getUsername() {return username;}

    public String getEmail() {return email;}

    public String getPhone() {return phone;}

    private void normalizePhoneNumber() {
        String number = this.phone;

        number = number.replaceAll("^\\+33", "");
        number = number.replaceAll("[^\\+\\d]", "");

        if (number.length() == 9) {
            number = "0" + number;
        }

        this.phone = number;
    }
}
