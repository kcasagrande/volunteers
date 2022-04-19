package org.example.volunteers.cleanup;

import org.example.volunteers.Volunteer;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CleanUp {

  public static Map<String, Volunteer> fixVolunteersFields(List<Volunteer> volunteers) {
    Map<String, Volunteer> fixedVolunteers = new HashMap<>();
    ArrayList<Volunteer> volunteersWithoutEmail = new ArrayList<>();

    for (Volunteer volunteer : volunteers) {
      Volunteer volunteerToClean = new Volunteer(
          volunteer.firstName.isBlank()
              ? volunteer.firstName
              : volunteer.firstName.substring(0, 1).toUpperCase() + volunteer.firstName.substring(1).toLowerCase(),
          volunteer.lastName.toUpperCase(),
          volunteer.nickName,
          volunteer.email.toLowerCase(),
          volunteer.phone
      );

      // If a volunteer has an email and not in map, add it to map
      if (!volunteerToClean.email.isBlank() && !fixedVolunteers.containsKey(volunteerToClean.email)) {
        fixedVolunteers.put(volunteerToClean.email, volunteerToClean);
      }
      // If a volunteer has an email and in map, merge it with the one in map
      else if (!volunteerToClean.email.isBlank() && fixedVolunteers.containsKey(volunteerToClean.email)) {
        Volunteer existingVolunteer = fixedVolunteers.get(volunteerToClean.email);
        Volunteer mergedVolunteer = mergeVolunteers(existingVolunteer, volunteerToClean);
        fixedVolunteers.put(volunteerToClean.email, mergedVolunteer);
      } else {
        volunteersWithoutEmail.add(volunteerToClean);
      }
    }

    // Clean volunteers without email

    return fixedVolunteers;
  }

  public static Volunteer mergeVolunteers(Volunteer v1, Volunteer v2) {
    String firstName = v1.firstName.equals("") ? v2.firstName : v1.firstName;
    String lastName = v1.lastName.equals("") ? v2.lastName : v1.lastName;
    String nickName = v1.nickName.equals("") ? v2.nickName : v1.nickName;
    String email = v1.email.equals("") ? v2.email : v1.email;
    String phone = v1.phone.equals("") ? v2.phone : v1.phone;

    return new Volunteer(firstName, lastName, nickName, email, phone);
  }

  public static Set<Volunteer> clean(List<Volunteer> volunteers) {
    // This function should contain your dark magic.
    // For now, it simply returns a copy of the initial list.

    Comparator<Volunteer> comparator = new Comparator<Volunteer>() {
      @Override
      public int compare(Volunteer o1, Volunteer o2) {
        return o1.email.compareTo(o2.email);
      }
    };

    Set<Volunteer> sortedWithoutDupes = new TreeSet<>(comparator);
    sortedWithoutDupes.addAll(volunteers);

    return sortedWithoutDupes;
  }

  public static Map<String, Volunteer> clean2(List<Volunteer> volunteers) {
    Map<String, Volunteer> uniqueVolunteerMap = fixVolunteersFields(volunteers);

    return uniqueVolunteerMap;
  }
}
