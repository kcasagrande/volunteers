import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        List<String[]> personnes = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
            .stream().map(string -> string.split(";"))
            .collect(toList());

        // Apply dark magic here...
        LinkedList<String> contactList = new LinkedList<String>();
        contactList.add("FirstName");
        contactList.add("LastName");
        contactList.add("NickName");
        contactList.add("Email");
        contactList.add("Phone");

        List<Contact> contacts = new ArrayList<Contact>();

        try {
            for (String[] _contact : personnes) {
                ArrayList list = new ArrayList<>(Arrays.asList(_contact));
                if (list.size() == contactList.size()){
                    contacts.add(new Contact(){{
                        firstName = _contact[contactList.indexOf("FirstName")].trim();
                        lastName = _contact[contactList.indexOf("LastName")].trim();
                        nickName = _contact[contactList.indexOf("NickName")].trim();
                        email = _contact[contactList.indexOf("Email")].trim();
                        phone = _contact[contactList.indexOf("Phone")].trim();
                    }});
                }
            }

            for (Contact c : contacts){
                if(!isEmailValid(c.email)){

                }
            }

        }catch (Exception ex){

        }
    }

    public static boolean isEmailValid(String email){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
}
