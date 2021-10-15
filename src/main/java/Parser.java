import java.util.List;

public class Parser {

    public List<String[]> data;
    public Volonteer[] volonteers = new Volonteer[900];

    public Parser(List<String[]> data) {
        this.data = data;
    }

    public Volonteer[] format() {

        int index = 0;

        for (String[] item : data) {
            Volonteer volonteer = new Volonteer();

            volonteer.firstname = item[0].toString();
            volonteer.name = item[1].toString();
            volonteer.nametag = item[2].toString();
            volonteer.mail = item[3].toString();

            if (item.length == 5) volonteer.tel = item[4].toString();
            else volonteer.tel = "";


            volonteers[index] = volonteer;

            index++;
        }
        return volonteers;
    }

    //corrige les numéro de télephone non conformes 
    public String formatePhoneNumber(String toCorrect) {
        String corrected = toCorrect;
        boolean isCorrect = false;
        while (!isCorrect){
            // si ne fait pas 10 caractère 
            if (toCorrect.length() > 10){            
            // si . suppr             
            corrected = toCorrect.replace(".", "");
            // si - suppr
            corrected = toCorrect.replace("-", "");
            // si _ suppr
            corrected = toCorrect.replace("_", "");
            // si (0) suprr
            corrected = toCorrect.replace("(0)", "");
            // si espace suprr
            corrected = toCorrect.replace(" ", "");
            // si +33 remplace 0
            corrected = toCorrect.replace("+33", "0");            

            //marche aussi avec 
            // corrected = toCorrect.replaceAll("\\.|\\(0\\)|-|_| ", "");
            // corrected = toCorrect.replaceAll("\\+33", "0");

            }
            else {isCorrect = true;}      
        }        
        
        return corrected;
    }
}
