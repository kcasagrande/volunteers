package org.example.volunteers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CountNumberTest {

    @Test
    public void testCountNumber(){
        // Arrange - Given
     Int number = "0325654687946515";

        // Act - When
        Tools.countNumber(number);

        // Assert - Then
        assertEquals(countNumber,"4687946515");
       
    }
}