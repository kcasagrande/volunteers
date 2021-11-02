import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        List<String[]> personnes = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
            .stream().map(string -> string.split(";"))
            .collect(toList());

        ArrayList<Contact> contacts = CreateContactList(personnes);
        //System.out.println(contacts.size());
        Collection<Contact> setItems = removeDuplicates(contacts);
        //System.out.println(setItems.size());

        for(Contact c : setItems){
            System.out.println(c.getFirstName().concat(" " + c.getLastName()).concat(" " + c.getNickName()).concat(" " + c.getEmail()).concat(" " + c.getPhone()));
        }
        System.out.println("Nombre de Contacts de base: " + contacts.size());
        System.out.println("Nombre de Contacts après avoir enlevé les doublons: " + setItems.size());

    }

    public static ArrayList<Contact> CreateContactList(List<String[]> contactLine) {

        // Apply dark magic here...
        LinkedList<String> contactList = new LinkedList<String>();
        contactList.add("FirstName");
        contactList.add("LastName");
        contactList.add("NickName");
        contactList.add("Email");
        contactList.add("Phone");

        ArrayList<Contact> contacts = new ArrayList<Contact>();

        for (String[] _contact : contactLine) {
            ArrayList listContact = new ArrayList<>(Arrays.asList(_contact));

            if (isValidContact(listContact, contactList, _contact[contactList.indexOf("FirstName")].trim())){
                Contact newContact = new Contact();
                newContact.setFirstName(_contact[contactList.indexOf("FirstName")].trim());
                newContact.setLastName(_contact[contactList.indexOf("LastName")].trim());
                newContact.setNickName(_contact[contactList.indexOf("NickName")].trim());
                newContact.setEmail(_contact[contactList.indexOf("Email")].trim());
                newContact.setPhone(_contact[contactList.indexOf("Phone")].trim());
                contacts.add(newContact);
            }
        }
        return contacts;
    }

    public static boolean isValidContact(
            ArrayList contact,
            LinkedList<String> contactList,
            String _contact){
        return contact.size() == contactList.size() && !_contact.isEmpty();
    }

    public static Collection<Contact> removeDuplicates(ArrayList<Contact> contacts){
        contacts.sort(new ContactSorted());
        return contacts.stream().collect(
                Collectors.toMap(
                        obj -> obj.getFirstName(),
                        Function.identity(),
                        (o1,o2) -> o1))
                .values();
    }

}
