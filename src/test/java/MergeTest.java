import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MergeTest {

    private final App app = new App();

    @Test
    public void mergeSameUserWithSameFirstname(){
        User userA = new User("About", "Axelle","Axelle","axelleabout@example.net","0000555503");
        User userB = new User("About","Axelle","","axelleabout@example.net","0000555503");
        User userC = new User("About","Axelle","","axelle_about@example.org","0000555503");
        User userD = new User("About","Axelle","","axelleabout@example.org","0000555503");
    }
}
