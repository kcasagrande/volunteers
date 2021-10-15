import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        List<String[]> personnes = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
            .stream().map(string -> string.split(";"))
            .collect(toList());

        // Apply dark magic here...
        // LastName
        // Name
        // NickName
        // Email
        // Phone
        List<Contact> contacts = new ArrayList<Contact>();

        for (String[] _contact : personnes) {
            int length = _contact.length;
            if(length >= 5){
                Contact newContact = new Contact(){{
                    firstName = _contact[0];
                    lastName = _contact[1];
                    nickName = _contact[2];
                    email = _contact[3];
                    phone = _contact[4];
                }};
                contacts.add(newContact);
            } else {
                Contact newContact = new Contact(){{
                    firstName = _contact[0];
                    lastName = _contact[1];
                    nickName = _contact[2];
                    email = _contact[3];
                }};
                contacts.add(newContact);
            }


        }


        System.out.println(contacts.get(0).email);
    }
}
