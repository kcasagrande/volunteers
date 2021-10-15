package org.example.volunteers.comparison;

import org.example.volunteers.UserMock;

public class Comparator {
    UserMock user1;
    UserMock user2;

    public static String Compare(UserMock user1, UserMock user2){
        if(user1.firstName.equals(user2.firstName) || user1.lastName.equals(user2.lastName)){
            return "same";
        }
        return "not same";
    }



}
