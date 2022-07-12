package org.example.volunteers;

import org.example.volunteers.services.VolunteerService;
import com.tngtech.java.junit.dataprovider.DataProvider;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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

    List<String[]> volunteersData = new ArrayList<String[]>(){{
        add(new String[]{"1", "Gregg", "Sanchez", "gsanchez", "gsanchez@ynov.com", "0123456789"});
        add(new String[]{"2", "Aimée", "Ritleng", "aritleng", "aritleng@ynov.com", "+33123456789"});
        add(new String[]{"3", "Nicolas", "Notararigo", "nnotararigo", "nnotararigo@ynov.com", "01 23 45 67 89"});
        add(new String[]{"7", "Nicolas", "Notararigo", "nnotararigo", "nnotararigo@ynov.com", "+33123456789"});
        add(new String[]{"4", "Romain", "Frechet", "rfrechet", "rfrechet@ynov.com", "0923456789"});
        add(new String[]{"5", "Louise", "Baulan", "lbaulan", "lbaulan@ynov.com", "0623456789"});
        add(new String[]{"6", "Louise", "Baulan", "lbaulan", "gsanchez@ynov.com", "0173456789"});
    }};

    @BeforeAll
    public static void globalSetUp() {
        System.out.println("Ce code est exécuté une seule fois avant l'ensemble des tests");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("Ce code est exécuté avant chaque test");
    }

    @Test
    public void showDuplicatedVolunteers() {
        VolunteerService volunteerService = new VolunteerService(volunteersData);
        int index = 0;
        Volunteer volunteer = volunteerService.getVolunteers().get(index);
        List<Integer> indexes = VolunteerService.retrieveDuplicatesVolunteersIndex(volunteer, index + 1);
        assertEquals(indexes.size(), 1, "Le nombre de doublons n'est pas correct !");
    }

    @Test
    public void shouldGenerateFile() throws IOException {
        System.out.println("Génération du fichier");
        String[] strArray = new String[]{"src/main/resources/data.csv"};
        App.main(strArray);

        Path path = Paths.get("src/main/resources/output.csv");
        boolean fileExists = Files.exists(path);
        assertTrue(fileExists, "Le fichier devrait exister");
    }

    @Test
    public void fileShouldContainAtLeastOneLine() throws IOException {
        System.out.println("Vérification du nombre de lignes du fichier");

        Path path = Paths.get("src/main/resources/output.csv");
        long nbLines = Files.lines(path).skip(1L).count();

        assertTrue(nbLines >= 1, "Le fichier devrait contenir plus d'une ligne");
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
    public void shouldFormatNickname(){
        System.out.println("Formattage du pseudo");

        String nickname = "LouISEb  ' { OURDIN34";
        String formattedNickname = Cleaner.formatNickName(nickname);

        assertEquals("LouISEbOURDIN34", formattedNickname, "Le pseudo doit être formatté");
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

        Volunteer buddy = new Volunteer(1, "BOURDIN", "Mickael", "BouBouMi", "", "");
        int buddyScore = buddy.fiabilityScore();

        assertEquals(3, buddyScore, "Le score doit être de 3");
    }

    @Test
    public void shouldBeAGoodNumber() {
        List<Volunteer> volunteers = new ArrayList<Volunteer>();
        Volunteer v = new Volunteer(1, "Nom", "Prenom", "Surnom", "email1@gmail.com", "+33645784578");
        volunteers.add(v);
//        List<Volunteer> newVolunteer = c::cleanUp(volunteers);
        List<Volunteer> newVolunteers = Cleaner.cleanUp(volunteers);
//        assertArrayEquals(volunteers, newVolunteers);
        assertEquals(v.getPhone(), newVolunteers.get(0).getPhone());
    }

    @DataProvider
    public static Object[][] phoneProvider() {
        return new Volunteer[][]{
                {
                        new Volunteer(1, "Nom", "Prenom", "Surnom", "email@gmail.com", "05-55-51-64-64")
                },
                {
                        new Volunteer(2,"Nom", "Prenom", "Surnom", "email@gmail.com", "+33(0)5-55-51-64-64")
                },
                {
                        new Volunteer(3,"Nom", "Prenom", "Surnom", "email@gmail.com", "05 55 51 64 64")
                },
                {
                        new Volunteer(4,"Nom", "Prenom", "Surnom", "email@gmail.com", "+335 55 51 64 64")
                },
                {
                        new Volunteer(5,"Nom", "Prenom", "Surnom", "email@gmail.com", "+335.55.51.64.64")
                },
                {
                        new Volunteer(6,"Nom", "Prenom", "Surnom", "email@gmail.com", "+335-55-51-64-64")
                },
                {
                        new Volunteer(7,"Nom", "Prenom", "Surnom", "email@gmail.com", "05.55.51.64.64")
                }
        };
    }

    @ParameterizedTest
    @MethodSource("phoneProvider")
    public void shouldBeReplaceToAGoodNumber(Volunteer v1) {
        List<Volunteer> volunteers = new ArrayList<Volunteer>();
        volunteers.add(v1);
        List<Volunteer> newVolunteers = Cleaner.cleanUp(volunteers);
        assertEquals("+33555516464", newVolunteers.get(0).getPhone());
    }

    @Test
    public void shouldFindEmptyEntry() {
        List<Volunteer> volunteers = new ArrayList<Volunteer>();
        volunteers.add(new Volunteer(1,"Nom", "Prenom", "Surnom", "email@gmail.com", "+33555516464"));
        volunteers.add(new Volunteer(2,"Nom", "", "Surnom", "email@gmail.com", "+33555516464"));
        volunteers.add(new Volunteer(3,"", "", "", "", ""));
        volunteers.add(new Volunteer(4,"Nom", "Prenom", "Surnom", "email@gmail.com", "+33555516464"));

        List<Volunteer> newVolunteers = Cleaner.cleanUp(volunteers);

        List<Volunteer> expectedOutput = new ArrayList<Volunteer>();
        expectedOutput.add(new Volunteer(1,"Nom", "PRENOM", "Surnom", "email@gmail.com", "+33555516464"));
        expectedOutput.add(new Volunteer(2,"Nom", "", "Surnom", "email@gmail.com", "+33555516464"));
        expectedOutput.add(new Volunteer(3,"Nom", "PRENOM", "Surnom", "email@gmail.com", "+33555516464"));

        assertEquals(expectedOutput, newVolunteers);
    }

    @Test
    public void shouldCapitalizeAllWords() {
        String name = "jean-marie";
        String newName = Cleaner.capitalizeWord(name);
        assertEquals("Jean-Marie",newName);
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
