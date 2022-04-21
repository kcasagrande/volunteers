package org.example.volunteers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.Compare;
import org.junit.jupiter.api.Test;

public class HasCompleteWordTest {

  @Test
  public void testHasCompleteWord_SameText() {
    // Arrange - Given
    String sentence = "MANOUDOU";
    String word = "MANOUDOU";
    // Act - When
    boolean result = Compare.hasCompleteWord(word, sentence);

    // Assert - Then
    assertTrue(result);
  }
  @Test
  public void testHasCompleteWord_HasNotSameText() {
    // Arrange - Given
    String sentence = "MANOUDOUS";
    String word = "MANOUDOU";
    // Act - When
    boolean result = Compare.hasCompleteWord(word, sentence);

    // Assert - Then
    assertFalse(result);
  }
  
  @Test
  public void testHasCompleteWord_HasSentenceWithSameWord() {
    // Arrange - Given
    String sentence = "MANOUDOU EST LA MEILLEUR";
    String word = "MANOUDOU";
    // Act - When
    boolean result = Compare.hasCompleteWord(word, sentence);

    // Assert - Then
    assertTrue(result);
  }
  @Test
  public void testHasCompleteWord_HasSentenceWithNotSameWord() {
    // Arrange - Given
    String sentence = "MANOUDOUS EST LA MEILLEUR";
    String word = "MANOUDOU";
    // Act - When
    boolean result = Compare.hasCompleteWord(word, sentence);

    // Assert - Then
    assertFalse(result);
  }

}
