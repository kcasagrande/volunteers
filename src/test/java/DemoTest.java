import org.example.volunteers.Demo;
import org.example.volunteers.model.Person;
import org.example.volunteers.utils.PersonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoTest {
    @Test
    void testModel(){
        Person person = new Person("GUYON", "clement.guyon@gmail.com", "Clément", "0781915332");

    }

    @Test
    void testParser(){
        PersonParser personParser = new PersonParser();
        Assertions.assertEquals(null, personParser.parse(""));
    }
}
