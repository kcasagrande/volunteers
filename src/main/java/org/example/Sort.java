package org.example;

import java.util.List;
import org.example.volunteers.Volunteer;
import java.util.ArrayList;

public class Sort {

  static int NUMBER_OF_ERRORS_OF_EMAIL = 4;

  public static List<Volunteer> removeDuplicate(List<Volunteer> volunteers) {
    List<Volunteer> result = new ArrayList<>();
    for (Volunteer volunteer : volunteers) {
      if (!result.contains(volunteer)) {
        result.add(volunteer);
      }
    }
    return result;
  }

  public static List<Volunteer> getOccurenceByCompletName(Volunteer volunteer, List<Volunteer> volunteers) {
    List<Volunteer> result = new ArrayList<>();
    for (Volunteer vol : volunteers) {
      if (Compare.compareTo(volunteer.firstName + volunteer.lastName, vol.firstName + vol.lastName) <= 2) {
        result.add(vol);
      }
    }
    return result;
  }

  public static List<Volunteer> getOccurenceByEmailOrPhone(Volunteer volunteer, List<Volunteer> volunteers) {

    List<Volunteer> result = new ArrayList<>();
    for (Volunteer vol : volunteers) {
      if (Compare.compareTo(volunteer.eMail, vol.eMail) <= NUMBER_OF_ERRORS_OF_EMAIL
          || Compare.compareTo(volunteer.phone, vol.phone) == 0) {

        if (volunteer.eMail == "" && vol.eMail == "")
          continue;
        if (volunteer.phone == "" && vol.phone == "")
          continue;
        result.add(vol);
      }
    }
    return result;
  }

  public static Volunteer fusion(List<Volunteer> volunteers) {

    // return le volunteer fusionner
    return new Volunteer("", "", "nickName", "eMail", "phone");
  }

  public static List<Volunteer> getUniqueVolunteer(List<Volunteer> formatListVolunteer) {

    // List of volunteers already add in the emptyList
    List<Volunteer> listVolunteerAlreadyAdd = new ArrayList<>();
    List<Volunteer> result = new ArrayList<>();
    // foreach sur formatListVolunteer
    formatListVolunteer.forEach(volunteer -> {
      // sur le s on appel getOccurenceByCompletName et retourne une liste des
      // volontaires avec le meme nom
      List<Volunteer> volunteersByCompletName = Sort.getOccurenceByCompletName(volunteer, formatListVolunteer);
      // Si il n'y a qu'un seul fois le volunteer -> ["Gayylord"]
      if (volunteersByCompletName.size() == 1) {
        // on l'ajoute aux listes des volontaires fait et des volontaires uniques
        listVolunteerAlreadyAdd.add(volunteersByCompletName.get(0));
        result.add(volunteersByCompletName.get(0));
        return;
      }

      // Si il y a plusieurs volontaires avec le même nom -> ["Gayylord", "Gaylord"]
      List<Volunteer> volunteersByEmailOrPhone = Sort.getOccurenceByEmailOrPhone(volunteer, volunteersByCompletName);
      if (volunteersByEmailOrPhone.size() == 1) {
        if (listVolunteerAlreadyAdd.contains(volunteersByEmailOrPhone.get(0)))
          return;

        listVolunteerAlreadyAdd.add(volunteersByEmailOrPhone.get(0));
        result.add(volunteersByEmailOrPhone.get(0));
        return;
      }

      // vérifie si les les utilisateurs ayant le meme nom et (email ou tel)
      // existe dans la liste des utilisateurs déja fusionner ?
      Volunteer volunterFusion = fusion(volunteersByEmailOrPhone);

      // si l'utilisateur fusionné existe dans la bdd :
      if (listVolunteerAlreadyAdd.contains(volunterFusion))
        return;
      // si il existe on ne l'ajoute pas
      result.add(volunterFusion);
    });

    return result;

  }

}