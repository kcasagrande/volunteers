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
    public void testTransformCSVInPersonObject() {
        List<Person> personsTransformed = personService.transformInPersonObject(givenPersons());
        for (Person personTransformed : personsTransformed) {
            assertInstanceOf(Person.class, personTransformed);
        }
    }

    @Test
    public void testTransformerReturnsCorrectSize() {
        assertEquals(2, personService.transformInPersonObject(givenPersons()).size());
    }

    @Test

    public void testPersonContent() {
        Person person = personService.transformInPersonObject(givenPersons()).get(0);
        assertEquals("+33085552814", person.phoneNumber);
        assertEquals("rebeccacompere@example.org", person.email);
        assertEquals("", person.userName);
        assertEquals("Rébecca", person.firstName);
        assertEquals("Compere", person.lastName);
    }


    public void testTransformerReturnsWrongSize() {
        assertNotEquals(1, personService.transformInPersonObject(givenPersons()).size());
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
     * Tests FilterPersonDuplicateByName inside PersonService
     *
     * @throws IOException
     */
    @Test
    public void testFilterPersonDuplicateByName() throws IOException {
        List<Person> personList = new ArrayList<Person>() {{
            add(new Person(1, "Jean", "Bon", "Jean69", "jean.bon@example.com", "+33607080910"));
            add(new Person(2, "Jean", "Bon", "Jean69", "jean.bon@example.org", "+33607080910"));
            add(new Person(3, "Jean", "Bon", "Jean69", "jean_bon@example.com", "+33607080910"));
            add(new Person(4, "James", "Bondes", "007", "james_bondes@example.com", "+33645787878"));
            add(new Person(5, "James", "Bondes", "007", "james_bondes@example.com", "+33645787878"));
        }};
        List<Person> filteredPersonList = personService.listSortByName(personList);
        assertEquals(2, filteredPersonList.size());
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void testFilterPersonDuplicateByNameWithoutName() throws IOException {
        List<Person> personList = new ArrayList<Person>() {{
            add(new Person(1, "Jean", "Bon", "Jean69", "jean.bon@example.com", "+33607080910"));
            add(new Person(2, "Jean", "Bon", "Jean69", "jean.bon@example.org", "+33607080910"));
            add(new Person(3, "", "", "Jean69", "jean_bon@example.com", "+33607080910"));
        }};
        List<Person> filteredPersonList = personService.listSortByName(personList);
        assertEquals(2, filteredPersonList.size());
    }


    /**
     * Tests filterPersonDuplicateByEmail inside PersonService
     */
    @Test
    public void testFilterPersonDuplicateByEmail() {
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

    @Test
    public void testListPersonContent() {
        List<Person> personList = new ArrayList<Person>() {{
            add(new Person(1, "Jean", "Bon", "Jean69", "jean.bon@example.com", "+33607080910"));
            add(new Person(2, "Jean", "Bon", "Jean69", "jean.bon@example.org", "+33607080910"));
            add(new Person(5, "James", "Bondes", "007", "james_bondes@example.com", "+33645787878"));
        }};

        Person person = personService.filterPersonDuplicateByEmail(personList).get(0);
        assertEquals("+33607080910", person.phoneNumber);
        assertEquals("jean.bon@example.com", person.email);
        assertEquals("Jean69", person.userName);
        assertEquals("Jean", person.firstName);
        assertEquals("Bon", person.lastName);
    }

    /**
     * Tests filterGenerateEmailVariants inside PersonService
     */
    @Test
    public void testGenerateEmailVariants() {
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

    @Test
    public void testPhoneNumberFormatting() {
        Map<String, String> nonFormattedAndFormattedMap = new HashMap<>();
        nonFormattedAndFormattedMap.put("+33(0)0.75.55.99.79", "0075559979");
        nonFormattedAndFormattedMap.put("+33(0)0-75-55-55-20", "0075555520");
        nonFormattedAndFormattedMap.put("+33055587491", "0055587491");
        nonFormattedAndFormattedMap.put("+33(0)0 85 55 67 37", "0085556737");
        nonFormattedAndFormattedMap.put("+33(0)000555091", "0000555091");
        nonFormattedAndFormattedMap.put("+330 00 55 52 25", "0000555225");
        nonFormattedAndFormattedMap.put("+330-55-55-66-33", "0055556633");
        nonFormattedAndFormattedMap.put("+330.00.55.52.42", "0000555242");
        nonFormattedAndFormattedMap.put("00 00 55 55 33", "0000555533");
        nonFormattedAndFormattedMap.put("00-35-55-85-21", "0035558521");
        nonFormattedAndFormattedMap.put("00.45.55.63.57", "0045556357");
        nonFormattedAndFormattedMap.put("0000555204", "0000555204");

        nonFormattedAndFormattedMap.forEach((nonFormatted, formatted) -> {
            assertEquals(this.personService.refactorPhoneNumber(nonFormatted), formatted);
        });
    }

    @Test
    public void testGetSplitEmail() {
        Person person = new Person(1, "Jean", "Bon", "Jean69", "jean_bon@example.com", "+33611111111");
        assertEquals(person.getSplitEmail(), "jean_bon@example");
    }
}
