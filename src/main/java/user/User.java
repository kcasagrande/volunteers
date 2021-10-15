package user;

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

    public boolean cleanUserList(List<User> userList){
        if (checkValidComboLastNameFirstNameOfUser(userList) && checkValidEmailOfUser(userList)){
            return true;
        }
        return false;
    }

    protected boolean checkValidComboLastNameFirstNameOfUser(List<User> userList) {
        for (User user : userList) {
            if ((this.firstName.equals(user.getFirstName()) && this.lastName.equals(user.getLastName()))
                || (this.firstName.equals(user.getLastName()) && this.lastName.equals(user.getFirstName()))) {
                return false;
            }
        }
        return true;
    }

    protected boolean checkValidUsernameOfUser(List<User> userList) {
        for (User user : userList) {
            if ((this.userName.equals(user.getUserName()))) {
                return false;
            }
        }
        return true;
    }

    protected boolean checkValidEmailOfUser(List<User> userList) {
        for (User user : userList) {
            if ((this.email.equals(user.getEmail()))) {
                return false;
            }
        }
        return true;
    }

    protected boolean checkValidPhoneNumberOfUser(List<User> userList) {
        for (User user : userList) {
            if ((this.phone.equals(user.getPhone()))) {
                return false;
            }
        }
        return true;
    }

}
