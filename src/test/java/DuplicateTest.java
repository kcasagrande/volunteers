import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuplicateTest {

    @Test
    public void test_mergeDuplicateByName() {
        List<User> listUser = new ArrayList<>();
        listUser.add(listUser.size(), new User("Doe","John","","","0612345678"));
        listUser.add(listUser.size(), new User("DOE","John","unknown","john.doe@example.com",""));
        listUser.add(listUser.size(), new User("Doe","Jane","unknown","jane.doe@example.com",""));
        listUser.add(listUser.size(), new User("Martins","Alexis","","martins.alexis@gmail.com",""));
        listUser.add(listUser.size(), new User("","","","jackie@example.com",""));
        listUser.add(listUser.size(), new User("","","","jesaisplusquo@mettre.com",""));

        Duplicate duplicate = new Duplicate();
        var finalListUser = duplicate.mergeByName(listUser);

        List<User> listExpected = new ArrayList<>();
        listExpected.add(listExpected.size(), new User("Doe","John","unknown","john.doe@example.com","0612345678"));
        listExpected.add(listExpected.size(), new User("Doe","Jane","unknown","jane.doe@example.com",""));
        listExpected.add(listExpected.size(), new User("Martins","Alexis","","martins.alexis@gmail.com",""));
        listExpected.add(listExpected.size(), new User("","","","jackie@example.com",""));
        listExpected.add(listExpected.size(), new User("","","","jesaisplusquo@mettre.com",""));

        assertEquals(listExpected, finalListUser);
    }
}
