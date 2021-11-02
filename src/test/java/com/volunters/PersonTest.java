package com.volunters;

import org.example.volunteers.model.Person;
import org.example.volunteers.model.PersonProperties;
import org.example.volunteers.service.PersonService;
import org.example.volunteers.utils.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class PersonTest {


    private static PersonService personService;

    @BeforeEach
    public void setUp() {

        personService = new PersonService();
    }

    @Mock
    private static StringUtil stringUtil;

    public static void setPersonService(PersonService personService) {
        PersonTest.personService = personService;
    }

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


    @Test
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
    public void testFilterDuplicatePhone() {
        Person person1 = Person.builder()
                .id(1)
                .phoneNumber("+33085552814")
                .build();
        Person person2 = Person.builder()
                .id(2)
                .phoneNumber("+33(0)0.85.55.28.14")
                .build();

        List<Person> personList = new ArrayList<>(Arrays.asList(person1, person2));

        //when
        lenient().when(stringUtil.refactorPhoneNumberString("+33085552814")).thenReturn("0085552814");
        lenient().when(stringUtil.refactorPhoneNumberString("+33(0)0.85.55.28.14")).thenReturn("0085552814");
        List<Person> listFiltered = personService.filterPersonDuplicateByPhoneNumber(personList);

        assertEquals(listFiltered.size(), 1);
        assertEquals(listFiltered.get(0).getPhoneNumber(), "0085552814");
    }

    @Test
    public void testFilterDuplicateEmptyPhone() {
        Person person1 = Person.builder()
                .id(1)
                .phoneNumber("")
                .build();
        Person person2 = Person.builder()
                .id(2)
                .phoneNumber("")
                .build();


        List<Person> personList = new ArrayList<>(Arrays.asList(person1, person2));
        //when
        lenient().when(stringUtil.refactorPhoneNumberString(any())).thenReturn("");
        List<Person> listFiltered = personService.filterPersonDuplicateByPhoneNumber(personList);

        assertEquals(listFiltered.size(), 3);
        assertEquals(listFiltered.get(0).getPhoneNumber(), "");
        assertEquals(listFiltered.get(1).getPhoneNumber(), "");
        assertEquals(listFiltered.get(2).getPhoneNumber(), "");

    }

    @Test
    public void testGetSplitEmail() {
        Person person = new Person(1, "Jean", "Bon", "Jean69", "jean_bon@example.com", "+33611111111");
        assertEquals(person.getSplitEmail(), "jean_bon@example");
    }
}
