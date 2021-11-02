import model.Person;
import model.PersonProperties;
import org.junit.jupiter.api.Test;
import service.PersonService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class PersonTest {

    private final PersonService personService = new PersonService();

    @Test
    public void testTransformCSVInPersonObject() {
        List<Person> personsTransformed = personService.transformInPersonObject(givenPersons());
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

        Map<PersonProperties, String> person = new HashMap<PersonProperties, String>() {
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

    /**
     * Tests filterPersonDuplicateByEmail inside PersonService
     *
     * @throws IOException
     */
    @Test
    public void testFilterPersonDuplicateByEmail() throws IOException {
        List<Person> personList = new ArrayList<Person>() {{
            add(new Person(1, "Jean", "Bon", "Jean69", "jean.bon@example.com", "+33607080910"));
            add(new Person(2, "Jean", "Bon", "Jean69", "jean.bon@example.org", "+33607080910"));
            add(new Person(3, "Jean", "Bon", "Jean69", "jean_bon@example.com", "+33607080910"));
            add(new Person(4, "James", "Bondes", "007", "james_bondes@example.com", "+33645787878"));
            add(new Person(5, "James", "Bondes", "007", "james_bondes@example.com", "+33645787878"));
        }};
        List<Person> filteredPersonList = personService.filterPersonDuplicateByEmail(personList);
        assertEquals(2, filteredPersonList.size());
    }

    /**
     * Tests filterGenerateEmailVariants inside PersonService
     *
     * @throws IOException
     */
    @Test
    public void testGenerateEmailVariants() throws IOException {
        List<String> baseVariantsEmailList = new ArrayList<String>() {{
            add("jean_bon@example.com");
            add("jeanbon@example.com");
            add("jeanbon@example");
            add("jean_bon@example");
            add("jean.bon@example");
        }};
        List<String> variantsEmailList = personService.generateEmailVariants("jean.bon@example.com");
        assertEquals(baseVariantsEmailList, variantsEmailList);
    }

}
