package org.example.volunteers;

import org.example.volunteers.utils.FieldsSanitizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestFieldsSanitizerName {

    @Test
    public void shouldKeepLatinSpecialChar() {
        Assertions.assertEquals("Léo", FieldsSanitizer.clearName("Léo"));
        Assertions.assertEquals("Raphaël", FieldsSanitizer.clearName("Raphaël"));
        Assertions.assertEquals("Владимир", FieldsSanitizer.clearName("Владимир"));
    }

    @Test
    public void digitsShouldBeRemoved() {
        Assertions.assertEquals("Mathieu", FieldsSanitizer.clearName("Mathieu69"));
    }

    @Test
    public void specialCharsShouldBeRemoved() {
        Assertions.assertEquals("Math", FieldsSanitizer.clearName("Math@"));
        Assertions.assertEquals("", FieldsSanitizer.clearName("[][;$]"));
    }

    @Test
    public void emptyNameShouldStayEmpty() {
        Assertions.assertEquals("", FieldsSanitizer.clearName(""));
    }

}
