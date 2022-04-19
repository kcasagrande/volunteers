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

    String nickNameFormat = 
    // mettre en forme le phone

    String telFormat =

    return new Volunteer(firstNameFormat, lastNameFormat, nickName, emailFormat, phone);
  }

  public static String toCapitalize(String text) {
    return text.substring(0, 1).toUpperCase() + text.substring(1);
  }

  public static String toCapitalize(String text) {
    return ""
  }

  public static String toCapitalize(String text) {
    return ""
  }

}
