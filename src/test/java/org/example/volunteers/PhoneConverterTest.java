package org.example.volunteers;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneConverterTest {
  @Test
  public void shouldAlwaysPass() {
    assertTrue(true);
  }

  @Test
  public void TestIfContentInsideParenthesisRemoveCorrectly() {
    String stringWithParenthesis = "+33(0)0-55-55-78-08";
    String stringWithoutParenthesis = "+330-55-55-78-08";

    String result = PhoneConverter.deleteContentInsideParenthesis(stringWithParenthesis);

    assertEquals(stringWithoutParenthesis, result);
  }

  @Test
  public void TestIfFirstZeroDontReplaceIfPhoneStartWithPlus() {
    String stringWithParenthesis = "66-55-55-78-08";

    String result = PhoneConverter.toUniversalFormat(stringWithParenthesis);

    assertFalse(result.startsWith("+33"));
  }

  @Test
  public void TestIfFirstZeroReplaceCorrectlyIfPhoneDoesntStartWithPlus() {
    String stringWithParenthesis = "06-55-55-78-08";

    String result = PhoneConverter.toUniversalFormat(stringWithParenthesis);

    assertTrue(result.startsWith("+33"));
  }

  @Test
  public void TestIfDotRemove() {
    String stringWithDot = "+330.55.55.78.08";
    String stringWithoutDot = "+33055557808";

    String result = PhoneConverter.removeAllNonIntegerCharacter(stringWithDot);

    assertEquals(stringWithoutDot, result);
  }

  @Test
  public void TestIfSpaceRemove() {
    String StringWithDot = "+330 55 55 78 08";
    String StringWithoutDot = "+33055557808";

    String result = PhoneConverter.removeAllNonIntegerCharacter(StringWithDot);

    assertEquals(StringWithoutDot, result);
  }


  @Test
  public void TestIfPhoneNumberWithHyphenConvertCorrectly() {
    // Given
    String numberWithHyphens = "06-15-26-50-20";
    String numberConverted = "0615265020";

    // When
    String result = PhoneConverter.convertPhoneNumberWithHyphen(numberWithHyphens);

    //Then
    assertEquals(numberConverted, result);
  }

  @Test
  public void TestIfPhoneConvertToUniversalFormat() {
    String stringWithParenthesis = "+33(0)6-55-55.78 08";
    String stringWithoutParenthesis = "+33655557808";

    String result = PhoneConverter.toUniversalFormat(stringWithParenthesis);

    assertEquals(stringWithoutParenthesis, result);
  }
}
