package org.example.volunteers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.example.Format;

public class CountNumberTest {

  @Test
  public void testCountNumber_MoreTenChar() {
    // Arrange - Given
    String number = "0325654687946515";

    // Act - When
    String result = Format.countNumber(number);

    // Assert - Then
    assertEquals("4687946515", result);
  }

  // test si chaine vide renvoi rien (ne doit pas lever d'erreur)..
  @Test
  public void testCountNumber_Empty() {
    // Arrange - Given
    String number = "";

    // Act - When
    String result = Format.countNumber(number);

    // Assert - Then
    assertEquals("", result);
  }

  // test si moins de 10 charactères mais > 8 => mettre des 00
  @Test
  public void testCountNumber_GreaterEightCharButLesserTenChar() {
    // Arrange - Given
    String number = "12345678";

    // Act - When
    String result = Format.countNumber(number);

    // Assert - Then
    assertEquals("0012345678", result);
  }

  // test si moins de 8 caractères => on supprime
  @Test
  public void testCountNumber_LesserEightCharRemove() {
    // Arrange - Given
    String number = "45678";

    // Act - When
    String result = Format.countNumber(number);

    // Assert - Then
    assertEquals("", result);
  }

  // Suppression des caractères spéciaux des numéros (. , - () etc...)
  @Test
  public void testCountNumber_RemoveCaracteres() {
    // Arrange - Given
    String number = "02564564,541.1541&&5-12154";

    // Act - When
    String result = Format.removeCaractereSpeciaux(number);

    // Assert - Then
    assertEquals("025645645411541512154", result);
  }

  @Test
  public void testCountNumber_RemoveEspaces() {
    // Arrange - Given
    String number = " 02564564 541  15415 12154";

    // Act - When
    String result = Format.removeEspace(number);

    // Assert - Then
    assertEquals("025645645411541512154", result);
  }

  //
  @Test
  public void testCountNumber_RemoveCountryIndex() {
    // Arrange - Given
    String number = "330102030405";

    // Act - When
    String result = Format.removeCountryIndex(number);

    // Assert - Then
    assertEquals("0102030405", result);
  }
}