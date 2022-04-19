package org.example;

import org.example.volunteers.Volunteer;

public class Tools {

  public static Volunteer toFormatVolunteer(Volunteer volunteer) {

    // mettre en forme le nom de famille
    String lastNameFormat = volunteer.lastName.toUpperCase();

    // mettre en forme le prenom
    String firstNameFormat = toCapitalize(volunteer.firstName);

    // mettre en forme l'email
    String emailFormat = volunteer.eMail.toLowerCase();

    // mettre en forme le nickName
    String nickNameFormat = toCapitalize(volunteer.nickName);

    // mettre en forme le phone
    String phoneFormat = formatPhone(volunteer.phone);

    return new Volunteer(firstNameFormat, lastNameFormat, nickNameFormat, emailFormat, phoneFormat);
  }

  public static String toCapitalize(String text) {
    if (text.isBlank() || text.isEmpty())
      return "";
    return text.substring(0, 1).toUpperCase() + text.substring(1);
  }

  public static String formatPhone(String phone) {
    return "";
  }

  public static String countNumber(String numberString) {
    return numberString.substring(numberString.length() - 10);
  }

}
