package org.example.volunteers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

import org.example.Sort;
import org.junit.jupiter.api.Test;

public class GetUniqueVolunteerTest {

  // TODO Test get unique volunteers
  @Test
  public void testUniqueVolunteer() {
    // Arrange - Given
    List<Volunteer> volunteerList = Arrays.asList(
      new Volunteer("Léonard","TREMBLAY","","leonard.tremblay@example.net","0076093618"),
      new Volunteer("Léonard","TREMBLAY","","leonard.tremblay@example.net","0000555927"),
      new Volunteer("Léonard","TREMBLAY","","leonardtremblay@example.net",""));

    List<Volunteer>  ResultAttendu = Arrays.asList(
      new Volunteer("Léonard","TREMBLAY","","leonard.tremblay@example.net | leonardtremblay@example.net","0076093618 | 0000555927"));

    // Act - When
    List<Volunteer> uniqueListName = Sort.getUniqueVolunteer(volunteerList);

    // Assert - Then
    assertEquals(ResultAttendu, uniqueListName);
  }
}
