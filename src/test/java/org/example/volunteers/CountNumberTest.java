package org.example.volunteers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.example.Tools;

public class CountNumberTest {

  @Test
    public void testCountNumber()
    {
        // Arrange - Given
        String number = "0325654687946515";

        // Act - When
        String result = Tools.countNumber(number);

        // Assert - Then
        assertEquals(result,"4687946515");
       
    }
}