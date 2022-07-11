package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;

public class Cleaner {
    public static boolean handleDuplicates(List<Volunteer> volunteers) {
        // supprimer tous les doublons identiques
        // assigner un familly id à ceux qui sont identique ( par mail ou phone similaire mais nom différent )
        // Autre fonction pour formater par famille

        List<Volunteer> singles = new ArrayList<>();
        List<Volunteer> hasDuplicates = new ArrayList<>();

        for ( int i = 0; i < volunteers.size(); i++ ) {
            Volunteer item = volunteers.get(i);
            boolean sameMail = volunteers.stream().filter(o -> o.eMail.equals(item.eMail)).count() > 1;
            boolean samePhone = volunteers.stream().filter(o -> o.phone.equals(item.phone)).count() > 1;

            if ( sameMail || samePhone ) hasDuplicates.add(item);
            else singles.add(item);
        }

        System.out.println(singles);
        System.out.println(hasDuplicates);

        // vérifier si mail ou tel similaire si le nom correspond aussi ou si les infos sont complémentaires ( donc à regrouper )
        // Lequel garder entre les deux doublons ? ( celui qui a le plus d'infos ? )
        // assigner à une famille si plusieurs personnes avec le même nom de famille mais pas même prénom

        return false;
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

            if (!hasValidPhone && volunteer.phone != null) {
                volunteer.formatPhoneNumber();
            }

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

        Cleaner.handleDuplicates(finalVolunteers);

        return new ArrayList<>(finalVolunteers);
    }
}


/*
*
- Vérifier si il n’y a pas de doublon
    - Par mail
    - Par numéro
- Formater les numéros
* */