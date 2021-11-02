package org.example.volunteers.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientListTest {
    @Test
    public void testGetClientListFromListOfStrings() {
        List<String[]> listClients = new ArrayList<>();
        listClients.add(new String[]{
                "Arthur",
                "Genthial",
                "Volt",
                "a@a.com",
                "+336 17 33 25 45"
        });

        ClientList clientList = new ClientList();
        clientList.setClientListFromFileLines(listClients);
        List<Client> resultList = clientList.getClientList();

        Client client = resultList.get(0);

        assertEquals(1, resultList.size());
        assertEquals("Arthur", client.getFirstname());
        assertEquals("Genthial", client.getLastname());
        assertEquals("Volt", client.getUsername());
        assertEquals("a@a.com", client.getEmail());
        assertEquals("0617332545", client.getPhone());
    }

    @Test
    public void testNormalizeClientList() {
        List<String[]> listClientsLines = new ArrayList<>();
        listClientsLines.add(new String[]{
                "Arthur",
                "Genthial",
                "Volt",
                "a@a.com",
                "+336 17 33 25 45"
        });
        listClientsLines.add(new String[]{
                "Arthur",
                "Genthial",
                "Voltzy",
                "a.g@test.com",
                "06 17 33 25 45"
        });

        ClientList clientList = new ClientList();
        clientList.setClientListFromFileLines(listClientsLines);
        clientList.normalize();

        ArrayList<Client> clientsResult = clientList.getClientListClean();

        Client client = clientsResult.get(0);

        assertEquals(1, clientsResult.size());
        assertEquals("Arthur", client.getFirstname());
        assertEquals("Genthial", client.getLastname());
        assertEquals("Volt", client.getUsername());
        assertEquals("a@a.com", client.getEmail());
        assertEquals("0617332545", client.getPhone());
    }

    @Test
    public void testNormalizeClientListSameEmail() {
        List<String[]> listClientsLines = new ArrayList<>();
        listClientsLines.add(new String[]{
                "Arthur",
                "Genthial",
                "Voltzy",
                "a.g@test.com",
                "06 17 33 25 45"
        });
        listClientsLines.add(new String[]{
                "Ahur",
                "Gehial",
                "Volt",
                "a.g@test.com",
                "+336 17 33 25 4"
        });

        ClientList clientList = new ClientList();
        clientList.setClientListFromFileLines(listClientsLines);
        clientList.normalize();

        ArrayList<Client> clientsResult = clientList.getClientListClean();

        Client client = clientsResult.get(0);

        assertEquals(1, clientsResult.size());
        assertEquals("Arthur", client.getFirstname());
        assertEquals("Genthial", client.getLastname());
        assertEquals("Voltzy", client.getUsername());
        assertEquals("a.g@test.com", client.getEmail());
        assertEquals("0617332545", client.getPhone());
    }

    @Test
    public void testNormalizeClientListSamePhoneNotSameFormat() {
        List<String[]> listClientsLines = new ArrayList<>();
        listClientsLines.add(new String[]{
                "Arthur",
                "Genthial",
                "Voltzy",
                "a@test.com",
                "06 17 33 25 45"
        });
        listClientsLines.add(new String[]{
                "Ahur",
                "Gehial",
                "Volt",
                "a.g@test.com",
                "+336 17 33 25 45"
        });

        ClientList clientList = new ClientList();
        clientList.setClientListFromFileLines(listClientsLines);
        clientList.normalize();

        ArrayList<Client> clientsResult = clientList.getClientListClean();

        Client client = clientsResult.get(0);

        assertEquals(1, clientsResult.size());
        assertEquals("Arthur", client.getFirstname());
        assertEquals("Genthial", client.getLastname());
        assertEquals("Voltzy", client.getUsername());
        assertEquals("a@test.com", client.getEmail());
        assertEquals("0617332545", client.getPhone());
    }
}
