package org.example.volunteers;

import java.util.*;

public class Merge {

    public List<Volunteer> mergeByName(List<Volunteer> users) {
        Map<String,Volunteer> newUsersMap = new LinkedHashMap<>();
        List<Map<String,String>> searchedNames = new ArrayList<>();
        List<String> searchedPhones = new ArrayList<>();
        List<String> searchedEmails = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            int i2 = i;
            String lastname = users.get(i).lastName.toLowerCase(Locale.ROOT);
            String firstname = users.get(i).firstName.toLowerCase(Locale.ROOT);
            String username = users.get(i).nickName;
            ArrayList<String> emailList = !users.get(i).eMail.isEmpty() ? new ArrayList<>(Collections.singleton(users.get(i).eMail)) : new ArrayList<>();
            ArrayList<String> phoneList = !users.get(i).phone.isEmpty() ? new ArrayList<>(Collections.singleton(users.get(i).phone)) : new ArrayList<>();
            Map<String, String> initMap = new HashMap<>();
            initMap.put("firstName", firstname);
            initMap.put("lastName", lastname);
            boolean isDuplicate = false;
            if (searchedNames.contains(initMap) ||searchedPhones.contains(users.get(i).phone) || (searchedEmails.contains(users.get(i).eMail))){
                    for(Map.Entry<String, Volunteer> entry : newUsersMap.entrySet()) {
                        if (username.isEmpty()) {
                            username = entry.getValue().nickName;
                        }
                        ArrayList<String> volunteerEmails = new ArrayList<>(Arrays.asList(entry.getValue().eMail.split(",")));
                        ArrayList<String> volunteerPhones = new ArrayList<>(Arrays.asList(entry.getValue().phone.split(",")));
                        if (volunteerPhones.contains(users.get(i).phone) || volunteerEmails.contains(users.get(i).eMail)) {
                            if (!volunteerEmails.contains(users.get(i).eMail)) {
                                volunteerEmails.add(users.get(i).eMail);
                            }
                            if (!volunteerPhones.contains(users.get(i).phone)) {
                                volunteerPhones.add(users.get(i).phone);
                            }
                            isDuplicate = true;
                            volunteerEmails.removeAll(Arrays.asList("", null));
                            volunteerPhones.removeAll(Arrays.asList("", null));
                            newUsersMap.put(
                                    entry.getValue().firstName + entry.getValue().lastName,
                                    new Volunteer(entry.getValue().lastName.isEmpty() ? lastname : entry.getValue().lastName, entry.getValue().firstName.isEmpty() ? firstname: entry.getValue().firstName, username, String.join(",", volunteerEmails), String.join(",", volunteerPhones)));
                        }
                    }
            }
            for(Map<String, String>  searchedName : searchedNames) {
                if (
                        JaroWinklerDistance.round(JaroWinklerDistance.jaro_distance(firstname + lastname, searchedName.get("firstName").toString() + searchedName.get("lastName").toString()), 1) > 0.8
                || JaroWinklerDistance.round(JaroWinklerDistance.jaro_distance(lastname+firstname, searchedName.get("firstName").toString()+searchedName.get("lastName").toString()), 1) > 0.8
                ) {
                    isDuplicate = true;
                    Volunteer dupUser = newUsersMap.get(searchedName.get("firstName").toString() + searchedName.get("lastName").toString());
                    username = dupUser.nickName;
                    if (username.equals("")) {
                        username = users.get(i2).nickName;
                    }
                    List<String> emailArray = new ArrayList<>(Arrays.asList(dupUser.eMail.split(",")));
                    if (!emailArray.contains(users.get(i).eMail) && !users.get(i).eMail.equals("")) {
                        emailArray.add(users.get(i).eMail);
                    }
                    List<String> phoneArray = new ArrayList<>(Arrays.asList(dupUser.phone.split(",")));
                    if (!phoneArray.contains(users.get(i).phone) && !users.get(i).phone.equals("")) {
                        phoneArray.add(users.get(i).phone);
                    }
                    newUsersMap.put(
                            searchedName.get("firstName").toString()+searchedName.get("lastName").toString(),
                            new Volunteer(searchedName.get("lastName").toString(),searchedName.get("firstName").toString(),username,String.join(",", emailArray),String.join(",", phoneArray)));
                }
            }

            if (!searchedNames.contains(initMap) && !isDuplicate) {
                while (i2 < users.size()) {
                    if (lastname.equalsIgnoreCase(users.get(i2).lastName)
                            && firstname.equalsIgnoreCase(users.get(i2).firstName)
                    ) {
                        if (username.isEmpty()) {
                            username = users.get(i2).nickName;
                        }
                        if (!emailList.contains(users.get(i2).eMail) && !users.get(i2).eMail.isEmpty()) {
                            emailList.add(users.get(i2).eMail);
                        }
                        if (!phoneList.contains(users.get(i2).phone) && !users.get(i2).phone.isEmpty()) {
                            phoneList.add(users.get(i2).phone);
                        }
                    }
                    i2++;
                }
                Map<String,String> nameMap = new HashMap<String, String>();
                nameMap.put("firstName", firstname);
                nameMap.put("lastName", lastname);
                searchedNames.add(nameMap);
                searchedEmails.addAll(emailList);
                searchedPhones.addAll(phoneList);
                newUsersMap.put(firstname+lastname, new Volunteer(lastname,firstname,username,String.join(",", emailList),String.join(",", phoneList)));
            }
        }
        return new ArrayList<>(newUsersMap.values());
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
