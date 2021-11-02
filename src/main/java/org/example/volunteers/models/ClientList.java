package org.example.volunteers.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ClientList {
    private final ArrayList<Client> clientList = new ArrayList<>();
    private final ArrayList<Client> clientListClean = new ArrayList<>();

    public void setClientListFromFileLines(List<String[]> fileLines) {
        for (String[] line: fileLines) {
            Client client = new Client(line[0], line[1], line[2], line[3], line[4]);

            clientList.add(client);
        }
    }

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public ArrayList<Client> getClientListClean() { return clientListClean; }

    public void normalize() {
        ArrayList<Integer> excludedIndexes = new ArrayList<>();

        int i = 0;
        for(Client client : clientList) {
            if (excludedIndexes.contains(i)) {
                i++;

                continue;
            }

            ArrayList<Client> duplicatesClients = new ArrayList<>();
            duplicatesClients.add(client);

            int y = 0;
            for (Client clientPotentialDuplicate : clientList) {
                if (y <= i) {
                    y++;

                    continue;
                }

                if (this.isDuplicate(client, clientPotentialDuplicate)) {
                    duplicatesClients.add(clientPotentialDuplicate);

                    excludedIndexes.add(y);
                }

                y++;
            }

            clientListClean.add(this.mergeDuplicates(duplicatesClients));

            i++;
        }
    }

    private boolean isDuplicate(Client clientA, Client clientB) {
        boolean firstNameMatch = Objects.equals(clientA.getFirstname().toLowerCase(), clientB.getFirstname().toLowerCase());
        boolean lastNameMatch = Objects.equals(clientA.getLastname().toLowerCase(), clientB.getLastname().toLowerCase());
        boolean usernameMatch = Objects.equals(clientA.getUsername().toLowerCase(), clientB.getUsername().toLowerCase());
        boolean emailMatch = Objects.equals(clientA.getEmail().toLowerCase(), clientB.getEmail().toLowerCase());
        boolean phoneMatch = Objects.equals(clientA.getPhone(), clientB.getPhone());

        if (usernameMatch || emailMatch || phoneMatch) {
            return true;
        }

        return firstNameMatch && lastNameMatch;
    }

    private Client mergeDuplicates(ArrayList<Client> duplicatesClients) {
        if (duplicatesClients.size() == 1) {
            return duplicatesClients.get(0);
        }

        String firstName = this.getUniqueForAttribute("firstname", duplicatesClients);
        String lastName = this.getUniqueForAttribute("lastname", duplicatesClients);
        String username = this.getUniqueForAttribute("username", duplicatesClients);
        String email = this.getUniqueForAttribute("email", duplicatesClients);
        String phone = this.getUniqueForAttribute("phone", duplicatesClients);

        return new Client(firstName, lastName, username, email, phone);
    }

    private String getUniqueForAttribute(String attribute, ArrayList<Client> duplicatesClients) {
        HashMap<String, Integer> attributes = new HashMap<>();

        for (Client client: duplicatesClients) {
            switch (attribute) {
                case "firstname":
                    attribute = client.getFirstname();
                    break;
                case "lastname":
                    attribute = client.getLastname();
                    break;
                case "username":
                    attribute = client.getUsername();
                    break;
                case "email":
                    attribute = client.getEmail();
                    break;
                case "phone":
                    attribute = client.getPhone();
                    break;
                default:
                    break;
            }

            if (!attributes.containsKey(attribute)) {
                attributes.put(attribute, 0);
            } else {
                attributes.put(attribute, attributes.get(attribute) + 1);
            }
        }

        return this.getMostFromStringList(attributes);
    }

    private String getMostFromStringList(HashMap<String, Integer> strings) {
        String mostValue = "";
        int occurrencesNumber = 0;

        for (String value: strings.keySet()) {
            if (occurrencesNumber < strings.get(value)) {
                occurrencesNumber = strings.get(value);
                mostValue = value;
            }
        }

        return mostValue;
    }
}
