package org.example.volunteers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.example.Compare;
import org.junit.jupiter.api.Test;

public class FusionTest {

  // TODO Test Fusion

  @Test
  public void testFusion_SimpleFusion() {
    // Arrange - Given
    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Gaylord", "MANAUDOU", "", "gaylord.manaudou@example.fr", ""),
        new Volunteer("Gaylord", "MANAUDOU", "", "", "0065555734"));

    Volunteer ResultAttendu = new Volunteer("Gaylord", "MANAUDOU", "", "gaylord.manaudou@example.fr", "0065555734");
    // Act - When
    Volunteer result = Compare.fusion(volunteers);

    // Assert - Then
    assertEquals(ResultAttendu, result);
  }

  @Test
  public void testFusion_FusionData() {
    // Arrange - Given
    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Gaylord", "MANAUDO", "Man69", "gaylord.manaudou@example.fr", "0102030405"),
        new Volunteer("Gayylord", "MANAUDOU", "", "gaylord_manaudou@example.net", "0065555734"));

    Volunteer ResultAttendu = new Volunteer("Gaylord | Gayylord", "MANAUDO | MANAUDOU", "Man69",
        "gaylord.manaudou@example.fr | gaylord_manaudou@example.net",
        "0102030405 | 0065555734");
    // Act - When
    Volunteer result = Compare.fusion(volunteers);

    // Assert - Then
    assertEquals(ResultAttendu, result);
  }
  @Test

  public void testFusion_FusionData_WithSameData() {
    // Arrange - Given
    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Gaylord", "MANAUDO", "Man69", "gaylord.manaudou@example.fr", "0102030405"),
        new Volunteer("Gayylord", "MANAUDOU", "", "gaylord_manaudou@example.net", "0102030405"));

    Volunteer ResultAttendu = new Volunteer("Gaylord | Gayylord", "MANAUDO | MANAUDOU", "Man69",
        "gaylord.manaudou@example.fr | gaylord_manaudou@example.net",
        "0102030405");
    // Act - When
    Volunteer result = Compare.fusion(volunteers);

    // Assert - Then
    assertEquals(ResultAttendu, result);
  }

  @Test
  public void testFusion_FusionData_WithEmptyData() {
    // Arrange - Given
    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Gaylord", "MANAUDO", "", "gaylord.manaudou@example.fr", "0102030405"),
        new Volunteer("Gayylord", "MANAUDOU", "", "gaylord_manaudou@example.net", "0102030405"));

    Volunteer ResultAttendu = new Volunteer("Gaylord | Gayylord", "MANAUDO | MANAUDOU", "",
        "gaylord.manaudou@example.fr | gaylord_manaudou@example.net",
        "0102030405");
    // Act - When
    Volunteer result = Compare.fusion(volunteers);

    // Assert - Then
    assertEquals(ResultAttendu, result);
  }
}
