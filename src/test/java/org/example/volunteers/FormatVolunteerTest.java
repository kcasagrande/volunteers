package org.example.volunteers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.Format;

public class FormatVolunteerTest {

  @Test
  public void testFormatVolunteer() {
    // Arrange - Given
    Volunteer volunteer = new Volunteer("JOHN", "dOe", "joNeDo", "JoHN.doE@gmaIL.COm", "+3306-02-05-04-03");

    // Act - When
    Volunteer formattedVolunteer = Format.toFormatVolunteer(volunteer);

    // Assert - Then
    assertEquals(formattedVolunteer, new Volunteer("John", "DOE", "joNeDo", "john.doe@gmail.com", "0602050403"));

  }

}
