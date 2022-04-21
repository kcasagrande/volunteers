package org.example.volunteers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.example.Sort;

public class GetOccurenceByCompletName {

  @Test
  public void GetOccurenceByCompletName_WithSameCompletName() {
    // Arrange - Given
    Volunteer volunteer = new Volunteer("Gaylord", "MANAUDOU", "", "gaylord.manaudou@example.org", "0065555734");

    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Gaylord", "MANAUDOU", "", "gaylord.manaudou@example.fr", ""),
        new Volunteer("Gayylord", "MANAU DOU", "", "gaylord.manaudou@example.org", "0065555734"),
        new Volunteer("John", "DOE", "", "John.DOE@example.org", "0065555714"));

    // Act - When
    List<Volunteer> result = Sort.getOccurenceByCompletName(volunteer, volunteers);

    // Assert - Then
    assertEquals(2, result.size());
  }

  @Test
  public void GetOccurenceByCompletName_WithThreeMistake() {
    // Arrange - Given
    Volunteer volunteer = new Volunteer("Gaylord", "MANAUDOU", "", "gaylord.manaudou@example.org", "0065555734");

    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Gaylord", "MANAUDOU", "", "gaylord.manaudou@example.fr", "0065555734"),
        new Volunteer("Gayyyylord", "MANAU DOU", "", "gaylord.manaudou@example.org", "0065555734"),
        new Volunteer("John", "DOE", "", "John.DOE@example.org", "0065555714"));

    // Act - When
    List<Volunteer> result = Sort.getOccurenceByCompletName(volunteer, volunteers);

    // Assert - Then
    assertEquals(1, result.size());
  }

  @Test
  public void GetOccurenceByCompletName_WhatsInsideInResult() {
    // Arrange - Given
    // Arrange - Given
    Volunteer volunteer = new Volunteer("Gaylord", "MANAUDOU", "", "gaylord.manaudou@example.org", "0065555734");

    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Gaylord", "MANAUDOU", "", "gaylord.manaudou@example.fr", "0065555734"),
        new Volunteer("Gayylord", "MANAU DOU", "", "gaylord.manaudou@example.org", "0065555734"),
        new Volunteer("John", "DOE", "", "John.DOE@example.org", "0065555714"));

    // Act - When
    List<Volunteer> result = Sort.getOccurenceByCompletName(volunteer, volunteers);
    List<Volunteer> resultListExpected = Arrays.asList(
        new Volunteer("Gaylord", "MANAUDOU", "", "gaylord.manaudou@example.fr", "0065555734"),
        new Volunteer("Gayylord", "MANAU DOU", "", "gaylord.manaudou@example.org", "0065555734"));

    // Assert - Then
    assertEquals(resultListExpected, result);
  }

  @Test
  public void GetOccurenceByCompletName_WithAnyMatch() {
    // Arrange - Given
    Volunteer volunteer = new Volunteer("Gaylord", "MAN", "", "gaylord.manaudou@example.org", "0065555734");

    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Gaylord", "MANAUDOU", "", "gaylord.manaudou@example.fr", "0065555734"),
        new Volunteer("Gayylord", "MANAU DOU", "", "gaylord.manaudou@example.org", "0065555734"),
        new Volunteer("John", "DOE", "", "John.DOE@example.org", "0065555714"));

    // Act - When
    List<Volunteer> result = Sort.getOccurenceByCompletName(volunteer, volunteers);

    // Assert - Then
    assertEquals(0, result.size());
  }
}
