package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;

public class Cleaner {
    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.
        return new ArrayList<>(volunteers);
    }


}


/*
*
* - Vérifier le nombre d’entrée par ligne
- Vérifier si il y a un mail
- Vérifier si il y a un numéro de téléphone
- Vérifier si il n’y a pas de doublon
    - Par mail
    - Par numéro
- Formater les numéros
- Vérifier si nom et prénom
* */