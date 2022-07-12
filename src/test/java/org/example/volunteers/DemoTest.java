package org.example.volunteers;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public void shouldAlwaysPass() {
        assertTrue(true);
    }

    @Test
    public void shouldComputeTheSumOfTwoNumbers() {
        // Arrange
        int a = 1;
        int b = 2;

        // Act
        int actualResult = a + b;

        // Assert
        int expectedResult = 3;
        assertEquals(expectedResult, actualResult, "La somme de 1 et 2 devrait être 3");
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
