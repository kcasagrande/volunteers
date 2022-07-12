package org.example.volunteers;

import org.example.volunteers.utils.FieldsSanitizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestFieldsSanitizerName {
    FieldsSanitizer fieldsSanitizer = new FieldsSanitizer();

    @Test
    public void shouldKeepLatinSpecialChar() {
        Assertions.assertEquals("Léo", fieldsSanitizer.clearName("Léo"));
        Assertions.assertEquals("Raphaël", fieldsSanitizer.clearName("Raphaël"));
        Assertions.assertEquals("Владимир", fieldsSanitizer.clearName("Владимир"));
    }

    @Test
    public void digitsShouldBeRemoved() {
        Assertions.assertEquals("Mathieu", fieldsSanitizer.clearName("Mathieu69"));
    }

    @Test
    public void specialCharsShouldBeRemoved() {
        Assertions.assertEquals("Math", fieldsSanitizer.clearName("Math@"));
        Assertions.assertEquals("", fieldsSanitizer.clearName("[][;$]"));
    }

    @Test
    public void emptyNameShouldStayEmpty() {
        Assertions.assertEquals("", fieldsSanitizer.clearName(""));
    }

}
