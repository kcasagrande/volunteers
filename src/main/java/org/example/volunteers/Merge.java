package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;

public class Merge {

    public List<Volunteer> mergeByName(List<Volunteer> users) {
        List<Volunteer> newUsers = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            int i2 = i;
            int j = 0;
            String exist = "no";
            String lastname = users.get(i).lastName;
            String firstname = users.get(i).firstName;
            String username = users.get(i).nickName;
            String email = users.get(i).eMail;
            String phone = users.get(i).phone;
            while (j < newUsers.size()) {
                if (lastname.toUpperCase().equals(newUsers.get(j).lastName.toUpperCase())
                        && firstname.toUpperCase().equals(newUsers.get(j).firstName.toUpperCase())
                        && !firstname.equals("") && !lastname.equals("")) {
                    exist = "yes";
                    break;
                } else {
                    j++;
                }
            }
            if (exist.equals("no")) {
                while (i2 < users.size()) {
                    if (lastname.equalsIgnoreCase(users.get(i2).lastName)
                            && firstname.equalsIgnoreCase(users.get(i2).firstName)) {
                        if (username.equals("")) {
                            username = users.get(i2).nickName;
                        }
                        if (email.equals("")) {
                            email = users.get(i2).eMail;
                        }
                        if (phone.equals("")) {
                            phone = users.get(i2).phone;
                        }
                    }
                    i2++;
                }
                newUsers.add(newUsers.size(), new Volunteer(lastname,firstname,username,email,phone));
            }
        }
        return newUsers;
    }

    public List<Volunteer> mergeByPhoneNumber(List<Volunteer> users) {
        List<Volunteer> newUsers = new ArrayList<Volunteer>();
        for (int i = 0; i < users.size(); i++) {
            int i2 = i;
            int j = 0;
            boolean exist = false;
            String lastname = users.get(i).lastName;
            String firstname = users.get(i).firstName;
            String username = users.get(i).nickName;
            String email = users.get(i).eMail;
            String phone = users.get(i).phone;
            while (j < newUsers.size()) {
                if (phone.equalsIgnoreCase(newUsers.get(j).phone)
                        && !phone.equals("")) {
                    System.out.println(newUsers.get(j));
                    exist = true;
                    break;
                } else {
                    j++;
                }
            }
            if (!exist) {
                while (i2 < users.size()) {
                    if (phone.equalsIgnoreCase(users.get(i2).phone)) {
                        if (lastname.equals("")) {
                            lastname = users.get(i2).lastName;
                        }
                        if (firstname.equals("")) {
                            firstname = users.get(i2).firstName;
                        }
                        if (username.equals("")) {
                            username = users.get(i2).nickName;
                        }
                        if (email.equals("")) {
                            email = users.get(i2).eMail;
                        }
                    }
                    i2++;
                }

                newUsers.add(newUsers.size(), new Volunteer(lastname,firstname,username,email,phone));
            }
        }
        return newUsers;
    }
}
