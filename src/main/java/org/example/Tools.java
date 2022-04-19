package org.example;

import java.util.regex.Matcher;

import org.example.volunteers.Volunteer;

public class Tools {

  public static Volunteer toFormatVolunteer(Volunteer volunteer) {

    // mettre en forme le nom de famille
    String lastNameFormat = volunteer.lastName.toUpperCase();

    // mettre en forme le prenom
    String firstNameFormat = toCapitalize(volunteer.firstName);

    // mettre en forme l'email
    String emailFormat = volunteer.eMail.toLowerCase();

    String phoneRemoveChar = removeCaractereSpeciaux(volunteer.phone);

    String phoneRemoveCountryIndex = removeCountryIndex(phoneRemoveChar);

    String phoneFormat = countNumber(phoneRemoveCountryIndex);

    return new Volunteer(firstNameFormat, lastNameFormat, volunteer.nickName, emailFormat, phoneFormat);
  }

  public static String toCapitalize(String text) {
    if (text.isBlank() || text.isEmpty())
      return "";
    text = text.toLowerCase();
    return text.substring(0, 1).toUpperCase() + text.substring(1);
  }

  public static String countNumber(String numberString) {
    if (numberString.isEmpty() || numberString.isBlank() || numberString.length() < 8)
      return "";

    if (numberString.length() >= 8 && numberString.length() < 10) {
      numberString = "00" + numberString;
    }

    return numberString.substring(numberString.length() - 10);
  }

  public static String removeCaractereSpeciaux(String number) {
    if (number.isEmpty() || number.isBlank())
      return "";
    return number.replaceAll("\\p{Punct}", "");
  }

  public static String removeEspace(String number) {
    if (number.isEmpty() || number.isBlank())
      return "";
    return number.replaceAll("\\s+", "");
  }

  public static String removeCountryIndex(String number) {
    if (number.isEmpty() || number.isBlank())
      return "";
    String twoChar = number.substring(0, 2);

    return twoChar.equals("33") ? number.substring(2) : number;
  }
}
