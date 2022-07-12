package org.example.volunteers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailTest {
    @Test
    public void mailValidOne() {
        String input = "jacquie@barbac.fr";
        String result = Cleaner.emailValidator(input);
        assertEquals("jacquie@barbac.fr", result, "Mail valide, doit renvoyé l'email");
    }

    @Test
    public void mailValidTwo() {
        String input = "jacquie.chane@kungfu.com";
        String result = Cleaner.emailValidator(input);
        assertEquals("jacquie.chane@kungfu.com",result, "Mail valide, doit renvoyé l'email");
    }

    @Test
    public void mailValidThree() {
        String input = "jacquie.chanedu59@chti.gouv";
        String result = Cleaner.emailValidator(input);
        assertEquals("jacquie.chanedu59@chti.gouv",result, "Mail valide, doit renvoyé l'email");
    }

    @Test
    public void mailValidErrorOne() {
        String input = "jacquie..@barbac.fr";
        String result = Cleaner.emailValidator(input);
        assertEquals("Null",result, "Mail invalide, doit renvoyé Null");
    }

    @Test
    public void mailValidErrorTwo() {
        String input = "jacquiebarbac.fr";
        String result = Cleaner.emailValidator(input);
        assertEquals("Null",result, "Mail invalide, doit renvoyé Null");
    }

    @Test
    public void mailValidErrorThree() {
        String input = "@orange.mg";
        String result = Cleaner.emailValidator(input);
        assertEquals("Null",result, "Mail invalide, doit renvoyé Null");
    }
}
