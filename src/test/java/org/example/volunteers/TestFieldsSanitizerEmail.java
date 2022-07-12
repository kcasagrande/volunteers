package org.example.volunteers;

import org.example.volunteers.utils.FieldsSanitizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestFieldsSanitizerEmail {
    FieldsSanitizer fieldsSanitizer = new FieldsSanitizer();
    

    @Test
    public void shouldRemoveAccents() {
        Assertions.assertEquals("leo.dupuis@quiches.lardon", fieldsSanitizer.clearEmail("léo.dupuis@quiches.lardon"));
    }

    @Test
    public void shouldLowerEmail() {
        Assertions.assertEquals("leo.dupuis@quiches.lardon", fieldsSanitizer.clearEmail("Leo.Dupuis@quiches.lardon"));
    }

    @Test
    public void shouldRemoveLastDotWhenExist() {
        Assertions.assertEquals("leo.dupuis@quiches.lardon", fieldsSanitizer.clearEmail("Leo.Dupuis@quiches.lardon."));
    }

    @Test
    public void shouldReturnEmptyStringIfInvalid() {
        Assertions.assertEquals("", fieldsSanitizer.clearEmail("leo.dupuis@quiches"));
        Assertions.assertEquals("", fieldsSanitizer.clearEmail("leo.dupuis"));
    }

    @Test
    public void shouldReturnEmptyStringForEmptyField() {
        Assertions.assertEquals("", fieldsSanitizer.clearEmail(""));
    }

    @Test
    public void shouldReturnEmailWhenFieldIsValid() {
        Assertions.assertEquals("leo.dupuis@quiches.ovh", fieldsSanitizer.clearEmail("leo.dupuis@quiches.ovh"));
    }
}
