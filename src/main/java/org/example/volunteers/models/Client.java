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

    public void setFirstname(String firstname) {this.firstname = firstname;}

    public String getLastname() {return lastname;}

    public void setLastname(String lastname) {this.lastname = lastname;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPhone() {return phone;}

    public void setPhone(String phone) {this.phone = phone;}

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
