package org.example.volunteers;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

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

}
