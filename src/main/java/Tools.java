import org.example.volunteers.Volunteer;

public class Tools {

  public static Volunteer toFormatVolunteer(Volunteer volunteer) {

    // mettre en forme le nom de famille
    volunteer.lastName = volunteer.lastName.toUpperCase();

    // mettre en forme le prenom
    volunteer.firstName = toCapitalize(volunteer.firstName);

    // mettre en forme l'email
    volunteer.eMail = volunteer.eMail.toLowerCase();

    // mettre en forme le nickName

    // mettre en forme le phone

    return volunteer;
  }

  public static String toCapitalize(String text) {
    return text.substring(0, 1).toUpperCase() + text.substring(1);
  }

}
