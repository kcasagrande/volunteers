package org.example.volunteers;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Cette classe est une suite de tests servant d'exemple et d'aide-mémoire de la syntaxe Java et JUnit.
// Elle n'est pas nécessaire à la réalisation de l'exercice.
public class DemoTest {

    @BeforeAll
    public static void globalSetUp() {
        System.out.println("Ce code est exécuté une seule fois avant l'ensemble des tests");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("Ce code est exécuté avant chaque test");
    }

    @Test
    public void showDuplicatedVolunteers() throws IOException {
        Volunteer volunteer1 = new Volunteer(1, "Gregg", "Sanchez", "gsanchez", "gsanchez@ynov.com", "0123456789");
        Volunteer volunteer2 = new Volunteer(2, "Aimée", "Ritleng", "aritleng", "aritleng@ynov.com", "0123456789");
        Volunteer volunteer3 = new Volunteer(3, "Nicolas", "Notararigo", "nnotararigo", "nnotararigo@ynov.com", "0123456789");
        Volunteer volunteer7 = new Volunteer(7, "Nicolas", "Notararigo", "nnotararigo", "nnotararigo@ynov.com", "+33123456789");
        Volunteer volunteer4 = new Volunteer(4, "Romain", "Frechet", "rfrechet", "rfrechet@ynov.com", "0123456789");
        Volunteer volunteer5 = new Volunteer(5, "Louise", "Baulan", "lbaulan", "lbaulan@ynov.com", "0123456789");
        Volunteer volunteer6 = new Volunteer(6, "Louise", "Baulan", "lbaulan", "gsanchez@ynov.com", "0123456789");

        List<Volunteer> volunteersList = new ArrayList<>();
        volunteersList.add(volunteer1);
        volunteersList.add(volunteer2);
        volunteersList.add(volunteer3);
        volunteersList.add(volunteer4);
        volunteersList.add(volunteer5);
        volunteersList.add(volunteer6);
        volunteersList.add(volunteer7);

        List<Duplicate> duplicatedVolunteers = Cleaner.extractDuplicated(volunteersList);
        System.out.println(duplicatedVolunteers.size());

        for (Duplicate duplicate : duplicatedVolunteers) {
            System.out.println("Volunteer ID : " + duplicate.volunteerId);
            System.out.println(duplicate.duplicates);
            System.out.println("Nombre de duplications : " + duplicate.count);
        }

        List<Volunteer> uniqueVolunteers = volunteersList.stream()
                .filter(v -> duplicatedVolunteers.stream().noneMatch(volunteer -> volunteer.volunteerId.equals(v.id)))
                .collect(Collectors.toList());
        System.out.println(uniqueVolunteers.size());
    }

    @Test
    public void shouldGenerateFile() throws IOException {
        System.out.println("Génération du fichier");
        String[] strArray = new String[] {"src/main/resources/data.csv"};
        App.main(strArray);

        Path path = Paths.get("src/main/resources/output.csv");
        boolean fileExists = Files.exists(path);
        assertEquals(true, fileExists, "Le fichier devrait exister");
    }

    @Test
    public void fileShouldContainAtLeastOneLine() throws IOException {
        System.out.println("Vérification du nombre de lignes du fichier");

        Path path = Paths.get("src/main/resources/output.csv");
        Long nbLines = Files.lines(path).skip(1L).count();

        assertEquals(true, nbLines >= 1, "Le fichier devrait contenir plus d'une ligne");
    }

    @Test
    public void shouldFormatFirstName() {
        System.out.println("Formattage du prénom");

        String firstName = "LouIs++e Anne";
        String formattedFirstName = Cleaner.formatFirstName(firstName);

        assertEquals("Louise-Anne", formattedFirstName, "Le prénom doit être formatté");
    }

    @Test
    public void shouldFormatLastName() {
        System.out.println("Formattage du nom");

        String lastName = "BourdIN!!-Michel";
        String formattedLastName = Cleaner.formatLastName(lastName);

        assertEquals("BOURDIN-MICHEL", formattedLastName, "Le nom doit être formatté");
    }

    @Test 
    public void shouldFormatEmail(){
        System.out.println("Formattage du mail");

        String email = "loUI se-aNNe-bo urdin-MICHEL@gmail.COM";
        String formattedEmail = Cleaner.formatEmail(email);

        assertEquals("louise-anne-bourdin-michel@gmail.com", formattedEmail, "Le mail doit être formatté");
    }

    @Test 
    public void shouldReturnsNoEmail(){
        System.out.println("Formattage du mail");

        String email = "loUI se-aNN!!!!e-bo urdin-MICHEL@.COM";
        String formattedEmail = Cleaner.formatEmail(email);

        assertEquals("", formattedEmail, "Le mail n'est normalement pas valide");
    }

    @Test
    public void calculateFiabilityScore() {
        System.out.println("Calcul de la fiabilité du volontaire");

        Volunteer buddy = new Volunteer("BOURDIN", "Mickael", "BouBouMi", "", "");
        int buddyScore = buddy.fiabilityScore();

        assertEquals(3, buddyScore, "Le score doit être de 3");
    }

    @Test
    public void shouldBeAGoodNumber() {
        List<Volunteer> volunteers = new ArrayList<Volunteer>();
        Volunteer v = new Volunteer("Nom", "Prenom", "Surnom", "email1@gmail.com", "+33645784578");
        volunteers.add(v);
//        List<Volunteer> newVolunteer = c::cleanUp(volunteers);
        List<Volunteer> newVolunteers = Cleaner.cleanUp(volunteers);
//        assertArrayEquals(volunteers, newVolunteers);

    }

    @Test
    public void shouldFindNullOrEmptyFields() {

    }
    @AfterEach
    public void tearDown() {
        System.out.println("Ce code est exécuté après chaque test");
    }

    @AfterAll
    public static void globalTearDown() {
        System.out.println("Ce code est exécuté une seule fois après l'ensemble des tests");
    }

}
