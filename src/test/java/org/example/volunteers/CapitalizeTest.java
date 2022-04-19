package org.example.volunteers;

import static org.junit.jupiter.api.Assertions.*;

import org.example.Tools;


import org.junit.jupiter.api.Test;

public class CapitalizeTest {

    @Test
    public void testCapitalizeFirstName() {
        // Arrange - Given
        String firstName = "john";
        // Act - When
        String result = Tools.toCapitalize(firstName);

        // Assert - Then
        assertEquals(result, "John");
    }

    @Test
    public void testCapitalizeFirstName_EmptyString() {
        // Arrange - Given
        String firstName = "";
        
        // Act - When
        String result = Tools.toCapitalize(firstName);

        // Assert - Then
        assertEquals(result, "");
    }
}
