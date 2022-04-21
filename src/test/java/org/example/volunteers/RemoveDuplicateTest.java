package org.example.volunteers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.example.Sort;

public class RemoveDuplicateTest {

  @Test
  public void testRemoveDuplicateTestVolunteer() {
    // Arrange - Given
    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("John", "DOE", "joNeDo", "john.doe@gmail.com", "0602050403"),
        new Volunteer("John", "DOE", "joNeDo", "john.doe@gmail.com", "0602050403"));

    // Act - When
    List<Volunteer> result = Sort.removeDuplicate(volunteers);

    // Assert - Then
    assertEquals(Arrays.asList(new Volunteer("John", "DOE", "joNeDo", "john.doe@gmail.com", "0602050403")), result);

  }

  @Test
  public void testRemoveDuplicateTestVolunteerManyEntries() {
    // Arrange - Given
    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("John", "DOE", "joNeDo", "john.doe@gmail.com", "0602050403"),
        new Volunteer("John", "DOE", "joNeDo", "john.doe@gmail.com", "0602050403"),
        new Volunteer("John", "DOE", "joNeDo", "john.doe@gmail.com", "0602050403"));

    // Act - When
    List<Volunteer> result = Sort.removeDuplicate(volunteers);

    // Assert - Then
    assertEquals(Arrays.asList(new Volunteer("John", "DOE", "joNeDo", "john.doe@gmail.com", "0602050403")), result);

  }

  @Test
  public void testRemoveDuplicateTestVolunteerManyEntriesWithUniqueValue() {
    // Arrange - Given
    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("John", "DOE", "joNeDo", "john.doe@gmail.com", "0602050403"),
        new Volunteer("Bill", "DOE", "joNeDo", "john.doe@gmail.com", "0602050403"),
        new Volunteer("John", "DOE", "joNeDo", "john.doe@gmail.com", "0602050403"));

    // Act - When
    List<Volunteer> result = Sort.removeDuplicate(volunteers);

    // Assert - Then
    assertEquals(Arrays.asList(
        new Volunteer("John", "DOE", "joNeDo", "john.doe@gmail.com", "0602050403"),
        new Volunteer("Bill", "DOE", "joNeDo", "john.doe@gmail.com", "0602050403")), result);

  }

}
