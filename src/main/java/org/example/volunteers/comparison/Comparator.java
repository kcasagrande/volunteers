package org.example.volunteers.comparison;

import org.example.volunteers.UserMock;

public class Comparator {
    UserMock user1;
    UserMock user2;

    public static String Compare(UserMock user1, UserMock user2){
        if(user1.firstName.toLowerCase().equals(user2.firstName.toLowerCase()) && user1.lastName.toLowerCase().equals(user2.lastName.toLowerCase())
                || user1.firstName.toLowerCase().equals(user2.lastName.toLowerCase()) && user1.lastName.toLowerCase().equals(user2.firstName.toLowerCase())){
            return "same";
        }
        return "not same";
    }



}
