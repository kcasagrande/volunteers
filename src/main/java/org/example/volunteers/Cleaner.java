package org.example.volunteers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Cleaner {

    public static List<Volunteer> volunteerMatcher(Volunteer v, List<Volunteer>volunteers){
        List<Volunteer> volunteerListTmp = new ArrayList<Volunteer>();
        ///v.phone
        if(v.phone != ""){
            for (Volunteer vTmp : volunteers.stream().filter(currentV -> currentV.phone == "123456").collect(Collectors.toList())){
                volunteerListTmp.add(vTmp);
                volunteers.remove(vTmp);
            }
        }

        return volunteerListTmp;
    }



    public static List<Volunteer> cleanVolunteers(List<Volunteer> volunteers){

        /***
         * Volunteers : List des volunteers du fichier
         *
         * 1 : Rassembler la data en agglomérant les volunteers susceptible d'être similaire dans volunteerListTmp
         *      Similarité gradé par : tel > email > pseudo > nom + prénom
         * 2 : Agglomérer les informations des volunteers détecté comme similaires
         * 3 : Supprimer les enregistrements incomplets ou partiels des volunteers
         * 4 : Retourner les volunteers agglomérés et non similaires
         * 5 : Construire un nouveau Volunteer à partir des différentes listes retournées
         */
        List<Volunteer> finalList = new ArrayList<>();

        Volunteer volunteerTmp;
        int i = 0;
        while(volunteers.size() > 0 && i < 10000){
            volunteerTmp = volunteers.stream().findFirst().get();
            finalList.addAll(volunteerMatcher(volunteerTmp, volunteers));
            volunteers.remove(volunteerTmp);
        }

        return finalList;
    }


    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        Volunteer volunteerOne;
        Volunteer volunteerToCompare;
        List<Integer> indiceExclue = new ArrayList<>();
        List<Volunteer> volunteerMerge = new ArrayList<>();

        for(int i = 0; i < volunteers.size(); i++) {
            volunteerOne = volunteers.get(i);
            if(!indiceExclue.contains(i)) {
                for(int j = 0; j < volunteers.size(); j++) {
                    volunteerToCompare = volunteers.get(j);
                    if(i != j && !indiceExclue.contains(i) && volunteerOne.isSameThan(volunteerToCompare)){
                        //On a trouvé quelqu'un qui lui ressemble on peut donc compléter leurs informations
                        volunteerOne.fillInformations(volunteerToCompare);
                        indiceExclue.add(j);
                    }
                }
            }
        }

        for(int i = 0; i < volunteers.size(); i++) {
            if(!indiceExclue.contains(i)) {
                volunteerMerge.add(volunteers.get(i));
            }
        }

        return volunteerMerge;
    }
}
