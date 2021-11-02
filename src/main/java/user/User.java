package user;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class User {

    private String firstName = null;
    private String lastName = null;
    private String userName = null;
    private String email = null;
    private String phone = null;

    public User(String firstName, String lastName, String userName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean checkValidComboLastNameFirstNameOfUser(List<User> userList) {
        for (User user : userList) {
            if ((this.firstName.equals(user.getFirstName()) && this.lastName.equals(user.getLastName()))
                || (this.firstName.equals(user.getLastName()) && this.lastName.equals(user.getFirstName()))) {
                return false;
            }
        }
        return true;
    }

    public boolean checkValidUsernameOfUser(List<User> userList) {
        for (User user : userList) {
            if ((this.userName.equals(user.getUserName()))) {
                return false;
            }
        }
        return true;
    }

    public boolean checkValidEmailOfUser(List<User> userList) {
        for (User user : userList) {
            if ((this.email.equals(user.getEmail()))) {
                return false;
            }
        }
        return true;
    }

    public boolean checkValidFirstNameOfUser(List<User> userList) {
        for (User user : userList) {
            if ((this.firstName.equals(user.getFirstName()))) {
                return false;
            }
        }
        return true;
    }

    public boolean checkValidLastNameOfUser(List<User> userList) {
        for (User user : userList) {
            if ((this.lastName.equals(user.getLastName()))) {
                return false;
            }
        }
        return true;
    }

    public boolean checkValidPhoneNumberOfUser(List<User> userList) {
        for (User user : userList) {
            if ((this.phone.equals(user.getPhone()))) {
                return false;
            }
        }
        return true;
    }

    public boolean checkNullLastNameOfUser() {
        if (this.lastName == null) {
            return false;
        }
        return true;
    }

    public boolean checkNullFirstNameOfUser() {
        if (this.firstName == null) {
            return false;
        }
        return true;
    }

    public boolean checkNullUserNameOfUser() {
        if (this.userName == null) {
            return false;
        }
        return true;
    }

    public boolean checkNullEmailOfUser() {
        if (this.email == null) {
            return false;
        }
        return true;
    }

    public boolean checkNullPhoneOfUser() {
        if (this.phone == null) {
            return false;
        }
        return true;
    }

    public boolean checkNullDataUser() {
        if (this.checkNullFirstNameOfUser()|| this.checkNullLastNameOfUser() || this.checkNullUserNameOfUser() || this.checkNullPhoneOfUser()) {
            return false;
        }
        return true;
    }
    public void trimAll() {
        firstName = firstName.trim();
        lastName = lastName.trim();
        email = email.trim();
        phone = phone.trim();
        userName = userName.trim();
    }
    public void toLowerCase() {
        firstName = firstName.toLowerCase();
        lastName = lastName.toLowerCase();
        email = email.toLowerCase();
        userName = userName.toLowerCase();
    }
    public void stripAccent() {
        stripAccentFirstName();
        stripAccentLastname();
        stripAccentEmail();
        stripAccentUsername();
    }

    public void stripAccentFirstName()
    {
        firstName = Normalizer.normalize(firstName, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public void stripAccentLastname()
    {
        lastName = Normalizer.normalize(lastName, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public void stripAccentEmail()
    {
        email = Normalizer.normalize(email, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public void stripAccentUsername()
    {
        userName = Normalizer.normalize(userName, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
