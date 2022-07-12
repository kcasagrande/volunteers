package org.example.volunteers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cleaner {
    public static List<Volunteer> handleDuplicates(List<Volunteer> volunteers) {
        List<Volunteer> singles = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        Integer groupIndex = 0;

        for ( int i = 0; i < volunteers.size(); i++ ) {
            Volunteer item = volunteers.get(i);
            boolean sameMail = !item.email.equals("") && volunteers.stream().filter(o -> o.email.equals(item.email)).count() > 1;
            boolean samePhone = !item.phone.equals("") && volunteers.stream().filter(o -> o.phone.equals(item.phone)).count() > 1;

            if ((sameMail) || ( samePhone )) {
                List<Group> currentGroup = null;
                if ( sameMail) {
                    currentGroup = groups.stream().filter(o -> o.volunteers.stream().anyMatch(v -> v.email.equals(item.email)) ).collect(Collectors.toList());
                }
                if ( (currentGroup == null || currentGroup.size() == 0) && samePhone) {
                    currentGroup = groups.stream().filter(o -> o.volunteers.stream().anyMatch(v -> v.phone.equals(item.phone)) ).collect(Collectors.toList());
                }

                if ( currentGroup != null  && currentGroup.size() > 0) {
                    currentGroup.get(0).volunteers.add(item);
                }
                else {
                    List<Volunteer> list = new ArrayList<Volunteer>();
                    list.add(item);
                    Group group = new Group(groupIndex, list);
                    groupIndex ++;
                    groups.add(group);
                }
            } else singles.add(item);
        }

        List<Volunteer> result = new ArrayList<>();
        result.addAll(singles);

        for ( int i = 0; i < groups.size(); i++ ) {
            List<Volunteer> listV = new ArrayList<>();
            listV.add(groups.get(i).volunteers.get(0));

            for ( int v = 1; v < groups.get(i).volunteers.size(); v++ ) {
                Volunteer current = groups.get(i).volunteers.get(v);

                List<Volunteer> vFind = listV.stream().filter(o -> (
                        (o.lastName.equals(current.lastName) && o.firstName.equals(current.firstName)) ||
                        (o.firstName.equals(current.lastName) && o.lastName.equals(current.firstName))
                )).collect(Collectors.toList());

                if (vFind != null && vFind.size() > 0) {
                    Volunteer baseV = vFind.get(0);
                    if ( baseV.nickName.equals("") && !current.nickName.equals("") ) baseV.nickName = current.nickName;
                    if ( baseV.email.equals("") && !current.email.equals("") ) baseV.email = current.email;
                    if ( baseV.phone.equals("") && !current.phone.equals("") ) baseV.phone = current.phone;
                    if ( !baseV.email.equals(current.email) ) baseV.email = mergeStrings(baseV.email, current.email);
                    if ( !baseV.phone.equals(current.phone) ) baseV.phone = mergeStrings(baseV.phone, current.phone);
                }
                else {
                    listV.add(current);
                }

            }
            groups.get(i).volunteers = listV;

            Group item = groups.get(i);
            result.addAll(item.volunteers);
        }
        System.out.println(result);

        return result;
    }

    public static String mergeStrings(String base, String current) {
        String[] splitBase = base.split(",");
        if (!current.equals("")) {
            if (!base.equals("")) {
                if (!Arrays.stream(splitBase).anyMatch(s -> s.equals(current))) {
                    base += "," + current;
                }
            } else base = current;
        }
        return base;
    }

    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        List<Volunteer> finalVolunteers = new ArrayList<Volunteer>();

        for (int i = 0; i < volunteers.size(); i++) {
            Volunteer volunteer = volunteers.get(i);
            boolean hasValidEmail = volunteer.hasValidMail();
            boolean hasValidPhone = volunteer.hasValidPhoneNumber();
            boolean hasFullName = volunteer.hasFullName();
            boolean hasPseudo = (volunteer.nickName == null || volunteer.nickName == "");

            volunteer.format();

            if (hasFullName && hasValidEmail && hasValidPhone) {
                volunteer.level = 1;
            } else if (hasFullName && (hasValidEmail || hasValidPhone)) {
                volunteer.level = 2;
            } else if (!hasFullName && hasPseudo && (hasValidEmail || hasValidPhone)) {
                volunteer.level = 3;
            } else if (!hasFullName && !hasPseudo && (hasValidEmail || hasValidPhone)) {
                volunteer.level = 4;
            } else {
                volunteer.level = 5;
            }

            finalVolunteers.add(volunteer);
        }

        List<Volunteer> list = Cleaner.handleDuplicates(finalVolunteers);
        return list;
    }
}