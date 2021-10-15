import java.util.List;
import java.util.ArrayList;

public class Parser {

    public List<String[]> data;
    public List<Volonteer> volonteers = new ArrayList<Volonteer>();


    public Parser(List<String[]> data) {
        this.data = data;
    }

    public List<Volonteer> format() {

        for (String[] item : data) {
            Volonteer volonteer = new Volonteer();

            volonteer.firstname = item[0].toString();
            volonteer.name = item[1].toString();
            volonteer.nametag = item[2].toString();
            volonteer.mail = item[3].toString();

            if (item.length == 5) volonteer.tel = item[4].toString();
            else volonteer.tel = "";
        }

        System.out.println(volonteers.get(0).name);

        return volonteers;
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
