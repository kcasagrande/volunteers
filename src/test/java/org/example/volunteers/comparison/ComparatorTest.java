package org.example.volunteers.comparison;

import org.example.volunteers.UserMock;
import org.junit.jupiter.api.Test;
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
}
