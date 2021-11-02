import java.util.List;
import java.util.ArrayList;

public class Parser {

    public List<String[]> data;
    public List<Volunteer> volunteers = new ArrayList<Volunteer>();


    public Parser(List<String[]> data) {
        this.data = data;
    }

    public List<Volunteer> format() {

        for (String[] item : data) {
            Volunteer volunteer = new Volunteer();
            volunteer.firstname = item[0].toString();
            volunteer.name = item[1].toString();
           
            volunteer.nametag = item[2].toString();
            volunteer.mail = item[3].toString();

            if (item.length == 5) volunteer.tel = item[4].toString();
            else volunteer.tel = "";

            volunteers.add(volunteer);
        }

        return volunteers;
    }

    //corrige les numéro de télephone non conformes 
    public String formatPhoneNumber(String toCorrect) {
        String corrected = toCorrect;

         // si ne fait pas 10 caractère 
        if (toCorrect.length() > 10){            
             corrected = toCorrect.replaceAll("\\.|\\(0\\)|-|_| ", "");
             corrected = corrected.replaceAll("\\+33", "0");
        }

        return corrected;
    }
}
