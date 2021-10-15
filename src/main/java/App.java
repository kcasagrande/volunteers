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
//            int length = _contact.length;
//            if(length >= 5){
//                Contact newContact = new Contact(){{
//                   if(!_contact[0].isEmpty()) firstName = _contact[0];
//                    lastName = _contact[1];
//                    nickName = _contact[2];
//                    email = _contact[3];
//                    phone = _contact[4];
//                }};
//                contacts.add(newContact);
//            } else {
//                Contact newContact = new Contact(){{
//                    firstName = _contact[0];
//                    lastName = _contact[1];
//                    nickName = _contact[2];
//                    email = _contact[3];
//                }};
//                contacts.add(newContact);
//            }

            Contact newContact = new Contact(){{
                if(!_contact[0].isEmpty()) firstName = _contact[0]; else firstName = null;
                if(!_contact[1].isEmpty()) lastName = _contact[1]; else lastName = null;
                if(!_contact[2].isEmpty()) nickName = _contact[2]; else nickName = null;
                if(!_contact[3].isEmpty()) email = _contact[3]; else email = null;
                if(_contact.length < 5){
                    phone = null;
                }else{
                    if(!_contact[4].isEmpty()) phone = _contact[4]; else phone = null;
                }
            }};
            contacts.add(newContact);

        }


        System.out.println(contacts.get(0).email);

        for(Contact contact : contacts){
            System.out.println("LastName: " + contact.lastName + "\n FirstName: " +
                    contact.firstName + "\n NickName: " + contact.nickName + "\n Email: " + contact.email
            + "\n Phone: " + contact.phone);
        }

//        for (String[] personne : personnes) {
//            System.out.println(personne[0] + personne[1] + personne[2] + personne[3]);
//        }
    }
}
