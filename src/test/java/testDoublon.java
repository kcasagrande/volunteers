import org.example.volunteers.Personne;
import org.example.volunteers.Utils;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

public class testDoublon {

    public Personne createEmptyPersonne(){
        Personne personneTest = new Personne();
        personneTest.nom = "";
        personneTest.prenom = "";
        personneTest.pseudo = "";
        personneTest.adresseMail = "";
        personneTest.tel = "";
        return personneTest;
    }

    @Test
    public void testDoublonVide(){
        Personne personneTest = createEmptyPersonne();
        Personne personneTestDeux = createEmptyPersonne();

        assertFalse(personneTest.isDoublon(personneTestDeux));
    }

    @Test
    public void testDoublonPseudo(){
        Personne personneTest = createEmptyPersonne();
        personneTest.pseudo = "taratata";

        Personne personneTestDeux = createEmptyPersonne();
        personneTestDeux.pseudo = "taratata";

        assertTrue(personneTest.isDoublon(personneTestDeux));
    }

    @Test
    public void testDoublonEmail(){
        Personne personneTest = createEmptyPersonne();
        personneTest.adresseMail = "taratata@gmail.com";

        Personne personneTestDeux = createEmptyPersonne();
        personneTestDeux.adresseMail = "taratata@gmail.com";

        assertFalse(personneTest.isDoublon(personneTestDeux));
    }

    @Test
    public void testDoublonTel(){
        Personne personneTest = createEmptyPersonne();
        personneTest.tel = "06.02.03.02.02";

        Personne personneTestDeux = createEmptyPersonne();
        personneTestDeux.tel = "06.02.03.02.02";

        assertFalse(personneTest.isDoublon(personneTestDeux));
    }

    @Test
    public void testDoublonTelPrenom(){
        Personne personneTest = createEmptyPersonne();
        personneTest.tel = "06.02.03.02.02";
        personneTest.prenom = "lolilol";

        Personne personneTestDeux = createEmptyPersonne();
        personneTestDeux.tel = "06.02.03.02.02";
        personneTestDeux.prenom = "lolilol";

        assertTrue(personneTest.isDoublon(personneTestDeux));
    }

    @Test
    public void testDoublonTelPrenomNom(){
        Personne personneTest = createEmptyPersonne();
        personneTest.tel = "06.02.03.02.02";
        personneTest.prenom = "lolilol";

        Personne personneTestDeux = createEmptyPersonne();
        personneTestDeux.tel = "06.02.03.02.02";
        personneTestDeux.nom = "lolilol";

        assertTrue(personneTest.isDoublon(personneTestDeux));
    }

    @Test
    public void testDoublonTelNomPrenom(){
        Personne personneTest = createEmptyPersonne();
        personneTest.tel = "06.02.03.02.02";
        personneTest.nom = "lolilol";

        Personne personneTestDeux = createEmptyPersonne();
        personneTestDeux.tel = "06.02.03.02.02";
        personneTestDeux.prenom = "lolilol";

        assertTrue(personneTest.isDoublon(personneTestDeux));
    }

    @Test
    public void testDoublonMailPrenom(){
        Personne personneTest = createEmptyPersonne();
        personneTest.adresseMail = "06.02.03.02.02";
        personneTest.prenom = "lolilol";

        Personne personneTestDeux = createEmptyPersonne();
        personneTestDeux.adresseMail = "06.02.03.02.02";
        personneTestDeux.prenom = "lolilol";

        assertTrue(personneTest.isDoublon(personneTestDeux));
    }

    @Test
    public void testDoublonMailPrenomNom(){
        Personne personneTest = createEmptyPersonne();
        personneTest.adresseMail = "06.02.03.02.02";
        personneTest.prenom = "lolilol";

        Personne personneTestDeux = createEmptyPersonne();
        personneTestDeux.adresseMail = "06.02.03.02.02";
        personneTestDeux.nom = "lolilol";

        assertTrue(personneTest.isDoublon(personneTestDeux));
    }

    @Test
    public void testDoublonMailNomPrenom(){
        Personne personneTest = createEmptyPersonne();
        personneTest.adresseMail = "06.02.03.02.02";
        personneTest.nom = "lolilol";

        Personne personneTestDeux = createEmptyPersonne();
        personneTestDeux.adresseMail = "06.02.03.02.02";
        personneTestDeux.prenom = "lolilol";

        assertTrue(personneTest.isDoublon(personneTestDeux));
    }

    /////////////////// Les tests du faux ///////////////////
    @Test
    public void testDoublonPseudoFalse(){
        Personne personneTest = createEmptyPersonne();
        personneTest.pseudo = "taratata";

        Personne personneTestDeux = createEmptyPersonne();
        personneTestDeux.pseudo = "taratoto";

        assertFalse(personneTest.isDoublon(personneTestDeux));
    }

    @Test
    public void testDoublonEmailFalse(){
        Personne personneTest = createEmptyPersonne();
        personneTest.adresseMail = "taratata@gmail.com";

        Personne personneTestDeux = createEmptyPersonne();
        personneTestDeux.adresseMail = "taratata@gmail.com2";

        assertFalse(personneTest.isDoublon(personneTestDeux));
    }

    @Test
    public void testDoublonTelPrenomFalse(){
        Personne personneTest = createEmptyPersonne();
        personneTest.tel = "06.02.03.02.02";
        personneTest.prenom = "lolilol";

        Personne personneTestDeux = createEmptyPersonne();
        personneTestDeux.tel = "06.02.03.02.02";
        personneTestDeux.prenom = "lolilala";

        assertFalse(personneTest.isDoublon(personneTestDeux));
    }

    @Test
    public void testDoublonTelPrenomNomFalse(){
        Personne personneTest = createEmptyPersonne();
        personneTest.tel = "06.02.03.02.02";
        personneTest.prenom = "lolilol";

        Personne personneTestDeux = createEmptyPersonne();
        personneTestDeux.tel = "06.02.03.02.02";
        personneTestDeux.nom = "lolilala";

        assertFalse(personneTest.isDoublon(personneTestDeux));
    }

    @Test
    public void testDoublonTelNomPrenomFalse(){
        Personne personneTest = createEmptyPersonne();
        personneTest.tel = "06.02.03.02.02";
        personneTest.nom = "lolilol";

        Personne personneTestDeux = createEmptyPersonne();
        personneTestDeux.tel = "06.02.03.02.02";
        personneTestDeux.prenom = "lolilala";

        assertFalse(personneTest.isDoublon(personneTestDeux));
    }

    @Test
    public void testDoublonMailPrenomFalse(){
        Personne personneTest = createEmptyPersonne();
        personneTest.adresseMail = "06.02.03.02.02";
        personneTest.prenom = "lolilol";

        Personne personneTestDeux = createEmptyPersonne();
        personneTestDeux.adresseMail = "06.02.03.02.02";
        personneTestDeux.prenom = "lolilala";

        assertFalse(personneTest.isDoublon(personneTestDeux));
    }

    @Test
    public void testDoublonMailPrenomNomFalse(){
        Personne personneTest = createEmptyPersonne();
        personneTest.adresseMail = "06.02.03.02.02";
        personneTest.prenom = "lolilol";

        Personne personneTestDeux = createEmptyPersonne();
        personneTestDeux.adresseMail = "06.02.03.02.02";
        personneTestDeux.nom = "lolilala";

        assertFalse(personneTest.isDoublon(personneTestDeux));
    }

    @Test
    public void testDoublonMailNomPrenomFalse(){
        Personne personneTest = createEmptyPersonne();
        personneTest.adresseMail = "06.02.03.02.02";
        personneTest.nom = "lolilol";

        Personne personneTestDeux = createEmptyPersonne();
        personneTestDeux.adresseMail = "06.02.03.02.02";
        personneTestDeux.prenom = "lolilala";

        assertFalse(personneTest.isDoublon(personneTestDeux));
    }
}
