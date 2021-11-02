import model.Person;
import model.PersonProperties;
import org.junit.jupiter.api.Test;
import service.PersonService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    private final PersonService personService = new PersonService();

    @Test
    public void testTransformCSVInPersonObject() {
        List<Person> personsTransformed =  personService.transformInPersonObject(givenPersons());
        for (Person personTransformed : personsTransformed) {
            assertInstanceOf(Person.class, personTransformed);
        }
    }

    @Test
    public void testTransformatorReturnsRightSize() {
        assertEquals(2, personService.transformInPersonObject(givenPersons()).size());
    }

    public List<Map<PersonProperties, String>> givenPersons() {
        List<Map<PersonProperties, String>> persons = new ArrayList<Map<PersonProperties, String>>();

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

        persons.add(person);
        persons.add(person);

        return persons;
    }
}
