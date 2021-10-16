import java.util.ArrayList;
import java.util.List;

public class Compare {

    public static List<Volunteer> arrayVolunteers = new ArrayList<Volunteer>();

    public List<Volunteer> compareNameInMail(List<Volunteer> volunteers, String volunteerName) {

        List<Volunteer> arrayToReturn = new ArrayList<Volunteer>();


        for (Volunteer volunteer : volunteers) {
            if (volunteer.mail.contains(volunteerName.toLowerCase())) arrayToReturn.add(volunteer);
        }

        return arrayToReturn;

    }

    public List<Volunteer> compareFirstName(List<Volunteer> comparedVolunteer,String volunteerFistName) {
        List<Volunteer> arrayToReturn = new ArrayList<Volunteer>();

        for (Volunteer volunteer : comparedVolunteer) {
            
            if (volunteer.firstname.toLowerCase().equals(volunteerFistName.toLowerCase())) arrayToReturn.add(volunteer);
            if (volunteer.name.toLowerCase().equals(volunteerFistName.toLowerCase())) {

                // Reverse firstname and name
                
                String firstname = volunteer.firstname;

                volunteer.firstname = volunteer.name;
                volunteer.name = firstname;

                arrayToReturn.add(volunteer);
            }

            
        }
        return arrayToReturn;
    }
}