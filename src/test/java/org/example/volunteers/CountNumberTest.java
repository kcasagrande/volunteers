package org.example.volunteers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.example.Tools;

public class CountNumberTest {

  @Test
  public void testCountNumber_MoreTenChar() {
    // Arrange - Given
    String number = "0325654687946515";

    // Act - When
    String result = Tools.countNumber(number);

    // Assert - Then
    assertEquals(result, "4687946515");
  }

  // test si chaine vide renvoi rien (ne doit pas lever d'erreur)..

  // test si moins de 10 charactères mais > 0 => result doit mettre des 000000

  // Suppression des caractères spéciaux des numéros (. , - () etc...)

}