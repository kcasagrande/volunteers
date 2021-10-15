import java.util.List;

public class Parser {

    public List<String[]> data;
    public Volonteer[] volonteers = new Volonteer[900];

    public Parser(List<String[]> data) {
        this.data = data;
    }

    public void format() {

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
    }
}
