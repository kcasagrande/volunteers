package org.example.volunteers.comparison;

import org.example.volunteers.UserMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparatorTest {


    //Test pour Nom = Nom & Prenom = Prenom
    @Test
    public void compareName(){
        UserMock user1 = new UserMock("Jean", "Dupont");
        UserMock user2 = new UserMock("Jean", "Dupont");
        String result;
        Comparator comparator = new Comparator();
        result = comparator.Compare(user1, user2);
        assertEquals("same", result);
    }

    //Test si nom et prenom inversé
    @Test
    public void compareInverted(){
        UserMock user1 = new UserMock("Jean", "Dupont");
        UserMock user2 = new UserMock("Dupont", "Jean");
        String result;
        Comparator comparator = new Comparator();
        result = comparator.Compare(user1, user2);
        assertEquals("same", result);
    }

    //Test pour verifier que le comparateur ignore les majuscules/minuscules
    @Test
    public void compareIgnoreUpper(){
        UserMock user1 = new UserMock("DUPONT", "JEAN");
        UserMock user2 = new UserMock("Dupont", "Jean");
        String result;
        Comparator comparator = new Comparator();
        result = comparator.Compare(user1, user2);
        assertEquals("same", result);
    }

    //Test avec une list a la place des user
    @Test
    public void compareItemInList(){
        String result;
        List<String[]> lines = new ArrayList<String[]>();
        String[] a1 = new String[]{"Jean", "Bon"," ", "jeanbon@example.org",""};
        lines.add(0, a1);
        String[] a2 = new String[]{"Bon", "Jean"," ", "jeanbon@example.org",""};
        lines.add(1, a2);
        Comparator comparator = new Comparator();
        result = comparator.CompareList(lines);
        assertEquals("same", result);
        assertEquals(1, lines.size());
    }

    @Test
    public void compareListPhone(){
        String result;
        List<String[]> lines = new ArrayList<String[]>();
        String[] a1 = new String[]{"Jean", "Bon","", "jeanbon@example.org", "+33055541000"};
        lines.add(0, a1);
        String[] a2 = new String[]{"Bon", "Jean","", "jeanbon@example.org", "+33055541000"};
        lines.add(1, a2);
        Comparator comparator = new Comparator();
        result = comparator.CompareList(lines);
        assertEquals("same", result);
        assertEquals(1, lines.size());
    }

}
