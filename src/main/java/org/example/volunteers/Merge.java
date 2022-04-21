package org.example.volunteers;

import java.util.*;

public class Merge {

    public List<Volunteer> mergeByName(List<Volunteer> users) {
        List<Volunteer> newUsers = new ArrayList<>();
        List<String> searchedNames = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            int i2 = i;
            String lastname = users.get(i).lastName.toLowerCase(Locale.ROOT);
            String firstname = users.get(i).firstName.toLowerCase(Locale.ROOT);
            String username = users.get(i).nickName;
            ArrayList<String> emailList = !users.get(i).eMail.equals("") ? new ArrayList<>(Collections.singleton(users.get(i).eMail)) : new ArrayList<>();
            ArrayList<String> phoneList = !users.get(i).phone.equals("") ? new ArrayList<>(Collections.singleton(users.get(i).phone)) : new ArrayList<>();
            if (!searchedNames.contains(firstname + lastname)) {
                while (i2 < users.size()) {
                    if (lastname.equalsIgnoreCase(users.get(i2).lastName)
                            && firstname.equalsIgnoreCase(users.get(i2).firstName)) {
                        if (username.equals("")) {
                            username = users.get(i2).nickName;
                        }
                        if (!emailList.contains(users.get(i2).eMail) && !users.get(i2).eMail.equals("")) {
                            emailList.add(users.get(i2).eMail);
                        }
                        if (!phoneList.contains(users.get(i2).phone) && !users.get(i2).phone.equals("")) {
                            phoneList.add(users.get(i2).phone);
                        }
                    }
                    i2++;
                }
                searchedNames.add(firstname + lastname);
                newUsers.add(newUsers.size(), new Volunteer(lastname,firstname,username,String.join(",", emailList),String.join(",", phoneList)));
            }
        }
        return newUsers;
    }

    public List<Volunteer> mergeByPhoneNumber(List<Volunteer> users) {
        List<Volunteer> newUsers = new ArrayList<Volunteer>();
        List<String> searchedPhoneNumbers = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            int i2 = i;
            String lastname = users.get(i).lastName;
            String firstname = users.get(i).firstName;
            String username = users.get(i).nickName;
            ArrayList<String> emailList = new ArrayList<>(Collections.singleton(users.get(i).eMail));
            String phone = users.get(i).phone;
            if (!searchedPhoneNumbers.contains(phone)) {
                while (i2 < users.size()) {
                    if (phone.equalsIgnoreCase(users.get(i2).phone) && !phone.equals("")) {
                        if (lastname.equals("")) {
                            lastname = users.get(i2).lastName;
                        }
                        if (firstname.equals("")) {
                            firstname = users.get(i2).firstName;
                        }
                        if (username.equals("")) {
                            username = users.get(i2).nickName;
                        }
                        if (!emailList.contains(users.get(i2).eMail) && !users.get(i2).eMail.equals("")) {
                            emailList.add(users.get(i2).eMail);
                        }
                    }
                    i2++;
                }
                searchedPhoneNumbers.add(phone);
                newUsers.add(newUsers.size(), new Volunteer(lastname,firstname,username, String.join(",", emailList),phone));
            }
        }
        return newUsers;
    }

    public List<Volunteer> mergeByEmail(List<Volunteer> users) {
        List<Volunteer> newUsers = new ArrayList<Volunteer>();
        List<String> searchedEmails = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            int i2 = i;
            String lastname = users.get(i).lastName;
            String firstname = users.get(i).firstName;
            String username = users.get(i).nickName;
            String email = users.get(i).eMail;
            ArrayList<String> phoneList = new ArrayList<>(Collections.singleton(users.get(i).phone));
            if (!searchedEmails.contains(email)) {
                while (i2 < users.size()) {
                    if (email.equalsIgnoreCase(users.get(i2).eMail) && !email.equals("")) {
                        if (lastname.equals("")) {
                            lastname = users.get(i2).lastName;
                        }
                        if (firstname.equals("")) {
                            firstname = users.get(i2).firstName;
                        }
                        if (username.equals("")) {
                            username = users.get(i2).nickName;
                        }
                        if (!phoneList.contains(users.get(i2).phone) && !users.get(i2).phone.equals("")) {
                            phoneList.add(users.get(i2).phone);
                        }
                    }
                    i2++;
                }
                searchedEmails.add(email);
                newUsers.add(newUsers.size(), new Volunteer(lastname,firstname,username, email,String.join(",", phoneList)));
            }
        }
        return newUsers;
    }
}
