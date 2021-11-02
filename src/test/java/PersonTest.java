import model.Person;
import model.PersonProperties;
import org.junit.jupiter.api.Test;
import service.PersonService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    private final PersonService personService = new PersonService();

    @Test
    public void testListPersonWDuplicate() throws IOException {
        List<Map<PersonProperties, String>> personsList = new ArrayList<Map<PersonProperties, String>>();

        Map<PersonProperties, String> person = new HashMap<PersonProperties, String>()
        {
            {
                put(PersonProperties.phoneNumber, "+33085552814");
                put(PersonProperties.email, "rebeccacompere@example.org");
                put(PersonProperties.userName, "");
                put(PersonProperties.firstName, "Rébecca");
                put(PersonProperties.lastName, "Compere");
            }
        };

        personsList.add(person);
        personsList.add(person);

        List<Person> persons = personService.transformInPersonObject(personsList);

        for (Person personTransformed : persons) {
            assertInstanceOf(Person.class, personTransformed);
        }
    }
}
