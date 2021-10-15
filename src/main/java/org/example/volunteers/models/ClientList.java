package org.example.volunteers.models;

import java.util.ArrayList;
import java.util.List;

public class ClientList {
    private ArrayList<Client> clientList = new ArrayList<Client>();
    private ArrayList<Client> clientListClean = new ArrayList<Client>();

    public void setClientListFromFileLines(List<String[]> fileLines) {
        for (String[] line: fileLines) {
            Client client = new Client(line[0], line[1], line[2], line[3], line[4]);

            clientList.add(client);
        }
    }

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public ArrayList<Client> getClientListClean() {
        return clientListClean;
    }

    public void normalize() {
        
    }
}
