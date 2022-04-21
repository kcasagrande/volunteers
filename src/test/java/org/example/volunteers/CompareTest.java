package org.example.volunteers;

import static org.junit.jupiter.api.Assertions.*;

import org.example.Compare;

import org.junit.jupiter.api.Test;

public class CompareTest {

  @Test
  public void testCompareToFunct_HasSame() {

    // Given
    String orderName = "Gaylord MANAUDOU";
    String customerName = "Gaylord MANAUDOU";

    // When
    int difference = orderName.compareTo(customerName);

    // Assert - Then
    assertEquals(0, difference);

  }

  @Test
  public void testCompareToFunct_OneDifference() {

    // Given
    String orderName = "Gaylord MANAUDOU";
    String customerName = "Gayylord MANAUDOU";

    // When
    int difference = Compare.compareTo(orderName, customerName);

    // Assert - Then
    assertEquals(1, difference);

  }

  @Test
  public void testCompareToFunct_TwoDifference() {

    // Given
    String orderName = "Gaylord MANAUDO";
    String customerName = "Gayylord MANAUDOU";

    // When
    int difference = Compare.compareTo(orderName, customerName);

    // Assert - Then
    assertEquals(2, difference);
  }

  @Test
  public void testCompareToFunct_SameWithSpace() {

    // Given
    String orderName = "Gayylord  MANAUDOU";
    String customerName = "Gayylord  MANAUDOU";

    // When
    int difference = Compare.compareTo(orderName, customerName);

    // Assert - Then
    assertEquals(0, difference);
  }

  @Test
  public void testCompareToFunct_OneDifferenceWithSpace() {

    // Given
    String orderName = "Gaylord MANAU DOU";
    String customerName = "Gayylord MANAUDOU";

    // When
    int difference = Compare.compareTo(orderName, customerName);

    // Assert - Then
    assertEquals(1, difference);
  }

  @Test
  public void testCompareToFunct_EmptyString() {

    // Given
    String orderName = "";
    String customerName = "";

    // When
    int difference = Compare.compareTo(orderName, customerName);

    // Assert - Then
    assertEquals(999999, difference);

  }
}