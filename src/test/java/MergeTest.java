import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeTest {

    private final App app = new App();

    @Test
    public void mergeSameUserWithSameFirstname(){
        User userA = new User("About", "Axelle","Axelle","axelleabout@example.net","0000555503");
        User userB = new User("About","Axelle","","axelleabout@example.net","0000555503");
        User userC = new User("About","Axelle","","axelle_about@example.org","0000555503");
        User userD = new User("About","Axelle","","axelleabout@example.org","0000555503");


        Merge merge = new Merge("About");
        merge.addUser(userA);
        merge.addUser(userB);
        merge.addUser(userD);
        merge.addUser(userC);

        merge.mergeUsers();

        assertEquals(1, merge.getResult().size());
        assertEquals(new User("About", "Axelle", "Axelle", "axelleabout@example.net", "0000555503"),
                merge.getResult().get(0));
    }

    @Test
    public void mergeSameUserWithSameRecurrentFirstname(){
        User userA = new User("About", "Axelle","Axelle","axelleabout@example.net","0000555503");
        User userB = new User("About","Axele","","axelleabout@example.net","0000555503");
        User userC = new User("About","Axell","","axelle_about@example.org","0000555503");
        User userD = new User("About","Axelle","","axelleabout@example.org","0000555503");


        Merge merge = new Merge("About");
        merge.addUser(userA);
        merge.addUser(userB);
        merge.addUser(userD);
        merge.addUser(userC);

        merge.mergeUsers();

        assertEquals(1, merge.getResult().size());
        assertEquals(new User("About", "Axelle", "Axelle", "axelleabout@example.net", "0000555503"),
                merge.getResult().get(0));
    }
}
