package user;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class User {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phone;

    public User(String firstName, String lastName, String userName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
    }

    public User() {
        this.firstName = null;
        this.lastName = null;
        this.userName = null;
        this.email = null;
        this.phone = null;
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

    public boolean checkUserData(ArrayList user) {

       if (user.checkUniqueEmailOfUser)



    }

    public boolean checkComboLastNameFirstNameOfUser(String userFirstName, String userLastName, List<User> userList) {
        for (User user : userList) {

            if (userFirstName.equals(user.getFirstName()) && userLastName.equals(user.getLastName())){
                return false;
            } else if(userFirstName.equals(user.getLastName()) && userLastName.equals(user.getFirstName())) {
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean checkValidUsernameOfUser(String userName) {
        return false;
    }

    public boolean checkUniqueEmailOfUser(String userEmail) {
        return false;
    }

    public boolean checkValidPhoneNumberOfUser(String userPhone) {
        return false;
    }

}
