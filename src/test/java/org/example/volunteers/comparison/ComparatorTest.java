package org.example.volunteers.comparison;

import org.example.volunteers.UserMock;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparatorTest {

    @Test
    public void compareName(){
        UserMock user1 = new UserMock("Jean", "Dupont");
        UserMock user2 = new UserMock("Jean", "Dupont");
        String result;

        Comparator comparator = new Comparator();
        result = comparator.Compare(user1, user2);
        assertEquals("same", result);
    }
}
