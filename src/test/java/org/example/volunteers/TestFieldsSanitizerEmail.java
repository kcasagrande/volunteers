package org.example.volunteers;

import org.example.volunteers.utils.FieldsSanitizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestFieldsSanitizerEmail {

    @Test
    public void shouldRemoveAccents() {
        Assertions.assertEquals("leo.dupuis@quiches.lardon", FieldsSanitizer.clearEmail("léo.dupuis@quiches.lardon"));
    }

    @Test
    public void shouldLowerEmail() {
        Assertions.assertEquals("leo.dupuis@quiches.lardon", FieldsSanitizer.clearEmail("Leo.Dupuis@quiches.lardon"));
    }

    @Test
    public void shouldRemoveLastDotWhenExist() {
        Assertions.assertEquals("leo.dupuis@quiches.lardon", FieldsSanitizer.clearEmail("Leo.Dupuis@quiches.lardon."));
    }

    @Test
    public void shouldReturnEmptyStringIfInvalid() {
        Assertions.assertEquals("", FieldsSanitizer.clearEmail("leo.dupuis@quiches"));
        Assertions.assertEquals("", FieldsSanitizer.clearEmail("leo.dupuis"));
    }

    @Test
    public void shouldReturnEmptyStringForEmptyField() {
        Assertions.assertEquals("", FieldsSanitizer.clearEmail(""));
    }

    @Test
    public void shouldReturnEmailWhenFieldIsValid() {
        Assertions.assertEquals("leo.dupuis@quiches.ovh", FieldsSanitizer.clearEmail("leo.dupuis@quiches.ovh"));
    }
}
