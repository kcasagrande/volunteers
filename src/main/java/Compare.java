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
}