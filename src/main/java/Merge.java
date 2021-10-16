import java.util.List;

public class Merge {

    public Volunteer mergeVolunteers(List<Volunteer> comparedVolunteer) {
      Volunteer firstVonlunteer = comparedVolunteer.get(0);

      for (Volunteer volunteer : comparedVolunteer) {
          if (firstVonlunteer.firstname.equals("") && !volunteer.firstname.equals("")) firstVonlunteer.firstname = volunteer.firstname;
          if (firstVonlunteer.name.equals("") && !volunteer.name.equals("")) firstVonlunteer.name = volunteer.name;
          if (firstVonlunteer.nametag.equals("") && !volunteer.nametag.equals("")) firstVonlunteer.nametag = volunteer.nametag;
          if (firstVonlunteer.mail.equals("") && !volunteer.mail.equals("")) firstVonlunteer.mail = volunteer.mail;
          if (firstVonlunteer.tel.equals("") && !volunteer.tel.equals("")) firstVonlunteer.tel = volunteer.tel;
      }

      return firstVonlunteer;

  }
}