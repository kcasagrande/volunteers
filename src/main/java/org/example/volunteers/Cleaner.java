package org.example.volunteers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cleaner {
    public static List<Volunteer> handleDuplicates(List<Volunteer> volunteers) {
        // supprimer tous les doublons identiques
        // assigner un familly id à ceux qui sont identique ( par mail ou phone similaire mais nom différent )
        // Autre fonction pour formater par famille

        List<Volunteer> singles = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        Integer groupIndex = 0;
        List<Volunteer> v2 = volunteers.stream().filter(v -> v.phone.equals("+33055511986")).collect(Collectors.toList());

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

            /*}
            else if ( item.email == "" || item.phone == "" ) {
                List<Volunteer> list = new ArrayList<Volunteer>();
                list.add(item);
                Group group = new Group(groupIndex, list);
                groupIndex ++;
                groups.add(group);*/
            } else singles.add(item);
        }
        //System.out.println(singles);
        //System.out.println(groups);

        List<Volunteer> result = new ArrayList<>();
        result.addAll(singles);

        for ( int i = 0; i < groups.size(); i++ ) {
            /*if (groups.get(i).volunteers.size() < 2) {
                Group g = groups.get(i);
                //System.out.println(g);
                // TODO : debug
            }*/
            // On merge tout
            Volunteer baseV = groups.get(i).volunteers.get(0);

            for ( int v = 1; v < groups.get(i).volunteers.size(); v++ ) {
                Volunteer current = groups.get(i).volunteers.get(v);
                if ( (current.firstName.equals(baseV.firstName) && current.lastName.equals(baseV.lastName)) ||
                     (current.firstName.equals(baseV.lastName) && current.lastName.equals(baseV.firstName))
                ) {
                    if ( baseV.nickName.equals("") && !current.nickName.equals("") ) baseV.nickName = current.nickName;
                    if ( baseV.email.equals("") && !current.email.equals("") ) baseV.email = current.email;
                    if ( baseV.phone.equals("") && !current.phone.equals("") ) baseV.phone = current.phone;
                    if ( !baseV.email.equals(current.email) && !baseV.email.equals("") ) baseV.email = mergeStrings(baseV.email, current.email);
                    if ( !baseV.phone.equals(current.phone) && !baseV.phone.equals("") ) baseV.phone = mergeStrings(baseV.phone, current.phone);
                }
            }

            Group item = groups.get(i);
            result.addAll(item.volunteers);
        }
        System.out.println(result);


        // vérifier si mail ou tel similaire si le nom correspond aussi ou si les infos sont complémentaires ( donc à regrouper )
        // Lequel garder entre les deux doublons ? ( celui qui a le plus d'infos ? )
        // assigner à une famille si plusieurs personnes avec le même nom de famille mais pas même prénom
        /*
         * Pour merge règles :
         * Si nom et prénom sont les même on fusionne et on fait la liste des mail / phone si besoin
         * Si nom et prénom son inversé on choisit un des deux
         * */

        return result;
    }

    public static String mergeStrings(String base, String current) {
        String[] splitBase = base.split(",");
        if (!Arrays.stream(splitBase).anyMatch(s -> s.equals(current))) {
            base += ',' + current;
        }
        return base;
    }

    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.
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
        Collections.sort(list, (o1, o2) -> o1.level.compareTo(o2.level));
        return list;
    }
}


/*
*
- Vérifier si il n’y a pas de doublon
    - Par mail
    - Par numéro
- Formater les numéros
* */