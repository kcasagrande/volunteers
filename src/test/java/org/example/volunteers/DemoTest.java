package org.example.volunteers;

import org.junit.jupiter.api.*;

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

    @AfterEach
    public void tearDown() {
        System.out.println("Ce code est exécuté après chaque test");
    }

    @AfterAll
    public static void globalTearDown() {
        System.out.println("Ce code est exécuté une seule fois après l'ensemble des tests");
    }

}
