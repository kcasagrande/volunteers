package org.example.volunteers.models;

import java.util.ArrayList;

public class ClientList {
    private Client[] clientList;
    private ArrayList<Client> clientListClean = new ArrayList<Client>();

    public ClientList(Client[] clientList) {
        this.clientList = clientList;
    }

    public Client[] getClientList() {
        return clientList;
    }

    public ArrayList<Client> getClientListClean() {
        return clientListClean;
    }

    public void normalize() {
    }
}
