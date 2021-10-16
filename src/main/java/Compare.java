import java.util.ArrayList;
import java.util.List;

public class Compare {

    public static List<Volunteer> arrayVolunteers = new ArrayList<Volunteer>();

    public List<Volunteer> compareNameInMail(List<Volunteer> volunteers, String volunterrFirstName) {

        List<Volunteer> arrayToReturn = new ArrayList<Volunteer>();


        for (Volunteer volunteer : volunteers) {
            if (volunteer.mail.contains(volunterrFirstName.toLowerCase())) arrayToReturn.add(volunteer);
        }

        return arrayToReturn;

    }

    public List<Volunteer> compareFirstName(List<Volunteer> comparedVolunteer,String volunterrName) {
        List<Volunteer> arrayToReturn = new ArrayList<Volunteer>();

        for (Volunteer volunteer : comparedVolunteer) {
            
            if (volunteer.firstname.toLowerCase().equals(volunterrName.toLowerCase())) arrayToReturn.add(volunteer);
            if (volunteer.name.toLowerCase().equals(volunterrName.toLowerCase())) {

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