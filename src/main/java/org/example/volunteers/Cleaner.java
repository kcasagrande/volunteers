package org.example.volunteers;
import java.util.*;

public class Cleaner {

  public static ArrayList<Volunteer> cleanVolunteersFields(ArrayList<Volunteer> volunteers) {
    ArrayList<Volunteer> cleanedVolunteers = new ArrayList<>();

    for (Volunteer volunteer : volunteers) {
      cleanedVolunteers.add(new Volunteer(
          volunteer.firstName.isBlank()
              ? volunteer.firstName
              : volunteer.firstName.substring(0, 1).toUpperCase() + volunteer.firstName.substring(1).toLowerCase(),
          volunteer.lastName.toUpperCase(),
          volunteer.nickName,
          volunteer.email.toLowerCase(),
          volunteer.phone.isBlank() ? "" : PhoneConverter.toUniversalFormat(volunteer.phone)
      ));
    }

    return cleanedVolunteers;
  }

  public static ArrayList<Volunteer> mergeByEmail(List<Volunteer> volunteers) {
    Map<String, Volunteer> fixedVolunteers = new LinkedHashMap<>();
    ArrayList<Volunteer> volunteersWithoutEmail = new ArrayList<>();

    for (Volunteer volunteer : volunteers) {
      // If a volunteer has an email and not in map, add it to map
      if (!volunteer.email.isBlank() && !fixedVolunteers.containsKey(volunteer.email)) {
        fixedVolunteers.put(volunteer.email, volunteer);
      }
      // If a volunteer has an email and in map, merge it with the one in map
      else if (!volunteer.email.isBlank() && fixedVolunteers.containsKey(volunteer.email)) {
        Volunteer volunteerWithSameEmail = fixedVolunteers.get(volunteer.email);

        if (volunteerWithSameEmail.hasSameName(volunteer)) {
          Volunteer existingVolunteer = fixedVolunteers.get(volunteer.email);
          Volunteer mergedVolunteer = mergeVolunteers(existingVolunteer, volunteer);
          fixedVolunteers.put(volunteer.email, mergedVolunteer);
        }
      }
      else {
        volunteersWithoutEmail.add(volunteer);
      }
    }

    ArrayList<Volunteer> volunteersCleanedByEmail = new ArrayList<>(volunteersWithoutEmail);
    volunteersCleanedByEmail.addAll(fixedVolunteers.values());

    return volunteersCleanedByEmail;
  }

  public static ArrayList<Volunteer> mergeByPhoneNumber(List<Volunteer> volunteers) {
    Map<String, Volunteer> fixedVolunteers = new LinkedHashMap<>();
    ArrayList<Volunteer> volunteersWithoutPhone = new ArrayList<>();

    for (Volunteer volunteer : volunteers) {
      // If a volunteer has an email and not in map, add it to map
      if (!volunteer.phone.isBlank() && !fixedVolunteers.containsKey(volunteer.phone)) {
        fixedVolunteers.put(volunteer.phone, volunteer);
      }
      // If a volunteer has an email and in map, merge it with the one in map
      else if (!volunteer.phone.isBlank() && fixedVolunteers.containsKey(volunteer.phone)) {
        Volunteer volunteerWithSamePhone = fixedVolunteers.get(volunteer.phone);

        if (volunteerWithSamePhone.hasSameName(volunteer)) {
          Volunteer existingVolunteer = fixedVolunteers.get(volunteer.phone);
          Volunteer mergedVolunteer = mergeVolunteers(existingVolunteer, volunteer);
          fixedVolunteers.put(volunteer.phone, mergedVolunteer);
        }
      }
      else {
        volunteersWithoutPhone.add(volunteer);
      }
    }

    ArrayList<Volunteer> volunteersCleanedByPhone = new ArrayList<>(volunteersWithoutPhone);
    volunteersCleanedByPhone.addAll(fixedVolunteers.values());

    return volunteersCleanedByPhone;
  }

  public static ArrayList<Volunteer> mergeByName(List<Volunteer> volunteers) {
    Map<String, Volunteer> fixedVolunteers = new LinkedHashMap<>();
    ArrayList<Volunteer> volunteersWithoutName = new ArrayList<>();

    for (Volunteer volunteer : volunteers) {
      // If a volunteer has an email and not in map, add it to map
      if (
          volunteer.hasName() && !fixedVolunteers.containsKey(volunteer.getFullName())
      ) {
        fixedVolunteers.put(volunteer.getFullName(), volunteer);
      }
      // If a volunteer has an email and in map, merge it with the one in map
      else if (volunteer.hasName() && fixedVolunteers.containsKey(volunteer.getFullName())) {
        Volunteer volunteerWithSameName = fixedVolunteers.get(volunteer.getFullName());

        if(volunteerWithSameName.hasSameCredentials(volunteer)) {

          Volunteer existingVolunteer = fixedVolunteers.get(volunteer.getFullName());
          Volunteer mergedVolunteer = mergeVolunteers(existingVolunteer, volunteer);
          fixedVolunteers.put(volunteer.getFullName(), mergedVolunteer);
        }
      }
      else {
        volunteersWithoutName.add(volunteer);
      }
    }

    ArrayList<Volunteer> volunteersCleanedByName = new ArrayList<>(volunteersWithoutName);
    volunteersCleanedByName.addAll(fixedVolunteers.values());

    return volunteersCleanedByName;
  }

  public static Volunteer mergeVolunteers(Volunteer v1, Volunteer v2) {
    String firstName = v1.firstName.equals("") ? v2.firstName : v1.firstName;
    String lastName = v1.lastName.equals("") ? v2.lastName : v1.lastName;
    String nickName = v1.nickName.equals("") ? v2.nickName : v1.nickName;
    String email = v1.email.equals("") ? v2.email : v1.email;
    String phone = v1.phone.equals("") ? v2.phone : v1.phone;

    return new Volunteer(firstName, lastName, nickName, email, phone);
  }

  public static ArrayList<Volunteer> clean(List<Volunteer> volunteers) {
    ArrayList<Volunteer> cleanedVolunteers = cleanVolunteersFields(new ArrayList<>(volunteers));

    ArrayList<Volunteer> volunteersMergedByEmail = mergeByEmail(cleanedVolunteers);
    ArrayList<Volunteer> volunteersMergedByEmailAndPhone = mergeByPhoneNumber(volunteersMergedByEmail);
    ArrayList<Volunteer> volunteersMergedByEmailPhoneAndName = mergeByName(volunteersMergedByEmailAndPhone);

    return volunteersMergedByEmailPhoneAndName;
  }
}
