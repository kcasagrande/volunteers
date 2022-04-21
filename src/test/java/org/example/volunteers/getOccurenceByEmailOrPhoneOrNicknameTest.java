package org.example.volunteers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.example.Sort;
import org.junit.jupiter.api.Test;

public class getOccurenceByEmailOrPhoneOrNicknameTest {

  // create test for the function getOccurenceByEmailOrPhoneOrNickname
  @Test
  public void getOccurenceByEmailOrPhoneOrNickname_WithSameEmail() {
    // Arrange - Given
    Volunteer volunteer = new Volunteer("Gaylord", "MANAUDOU", "", "gaylord.manaudou@example.org", "0065555734");

    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Frog", "MAN", "", "gaylord.manaudou@example.org", "0065555794"),
        new Volunteer("Gayyyylord", "MANAU DOU", "", "gaylord.manaudou@example.fr", "0065555744"),
        new Volunteer("John", "DOE", "", "John.DOE@example.org", "0065555614"));

    // Act - When
    List<Volunteer> result = Sort.getOccurenceByEmailOrPhoneOrNickname(volunteer, volunteers);

    // Assert - Then
    assertEquals(2, result.size());
  }

  @Test
  public void getOccurenceByEmailOrPhoneOrNickname_WithSameEmailResult() {
    // Arrange - Given
    Volunteer volunteer = new Volunteer("Gaylord", "MANAUDOU", "", "gaylord.manaudou@example.org", "0065555734");

    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Frog", "MAN", "", "gaylord.manaudou@example.org", "0065555794"),
        new Volunteer("Gayyyylord", "MANAU DOU", "", "gaylord.manaudou@example.fr", "0065555744"),
        new Volunteer("John", "DOE", "", "John.DOE@example.org", "0065555614"));

    // Act - When
    List<Volunteer> result = Sort.getOccurenceByEmailOrPhoneOrNickname(volunteer, volunteers);
    List<Volunteer> resultExpected = Arrays.asList(
        new Volunteer("Frog", "MAN", "", "gaylord.manaudou@example.org", "0065555794"),
        new Volunteer("Gayyyylord", "MANAU DOU", "", "gaylord.manaudou@example.fr", "0065555744"));
    // Assert - Then
    assertEquals(resultExpected, result);
  }

  @Test
  public void getOccurenceByEmailOrPhoneOrNickname_WithSamePhone() {
    // Arrange - Given
    Volunteer volunteer = new Volunteer("Bibi", "dodo", "", "Bibi.dodo@example.lala", "0065555734");

    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Frog", "MAN", "", "gaylord.manaudou@example.org", "0065555734"),
        new Volunteer("Gayyyylord", "MANAU DOU", "", "gaylord.manaudou@example.fr", "0102030405"),
        new Volunteer("John", "DOE", "", "John.DOE@example.org", "0908070605"));

    // Act - When
    List<Volunteer> result = Sort.getOccurenceByEmailOrPhoneOrNickname(volunteer, volunteers);

    // Assert - Then
    assertEquals(1, result.size());
  }

  @Test
  public void getOccurenceByEmailOrPhoneOrNickname_WithSamePhoneResult() {
    // Arrange - Given
    Volunteer volunteer = new Volunteer("Bibi", "dodo", "", "Bibi.dodo@example.lala", "0065555734");

    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Frog", "MAN", "", "gaylord.manaudou@example.org", "0065555734"),
        new Volunteer("Gayyyylord", "MANAU DOU", "", "gaylord.manaudou@example.fr", "0102030405"),
        new Volunteer("John", "DOE", "", "John.DOE@example.org", "0908070605"));

    // Act - When
    List<Volunteer> result = Sort.getOccurenceByEmailOrPhoneOrNickname(volunteer, volunteers);
    List<Volunteer> resultExpected = Arrays.asList(
        new Volunteer("Frog", "MAN", "", "gaylord.manaudou@example.org", "0065555734"));

    // Assert - Then
    assertEquals(resultExpected, result);
  }

  @Test
  public void getOccurenceByEmailOrPhoneOrNickname_WithEmptyEmail() {
    // Arrange - Given
    Volunteer volunteer = new Volunteer("Bibi", "DODO", "", "", "0065555734");

    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Frog", "MAN", "", "gaylord.manaudou@example.org", "0065555739"),
        new Volunteer("Bibi", "DODO", "", "", "0102030405"),
        new Volunteer("John", "DOE", "", "John.DOE@example.org", "0908070605"));

    // Act - When
    List<Volunteer> result = Sort.getOccurenceByEmailOrPhoneOrNickname(volunteer, volunteers);

    // Assert - Then
    assertEquals(0, result.size());
  }

  @Test
  public void getOccurenceByEmailOrPhoneOrNickname_WithEmptyPhone() {
    // Arrange - Given
    Volunteer volunteer = new Volunteer("Bibi", "DODO", "", "bobo.bi@example.fr", "");

    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Bibi", "DODO", "", "gaylord.manaudou@example.org", "0065555739"),
        new Volunteer("Bibi", "DODO", "", "test.arrage@example.fr", ""),
        new Volunteer("Bibi", "DODO", "", "John.DOE@example.org", "0908070605"));

    // Act - When
    List<Volunteer> result = Sort.getOccurenceByEmailOrPhoneOrNickname(volunteer, volunteers);

    // Assert - Then
    assertEquals(0, result.size());
  }

  @Test
  public void getOccurenceByEmailOrPhoneOrNickname_WithSameNickname() {
    // Arrange - Given
    Volunteer volunteer = new Volunteer("Gaylord", "MANAUDOU", "Doujésu", "gaylord.manaudou@example.org", "0065555734");

    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Frog", "MAN", "Doujésu", "gaylord.manaudou@example.org", "0065555794"),
        new Volunteer("Gayyyylord", "MANAU DOU", "", "gaylord.manaudou@example.fr", "0065555744"),
        new Volunteer("John", "DOE", "", "John.DOE@example.org", "0065555614"));

    // Act - When
    List<Volunteer> result = Sort.getOccurenceByEmailOrPhoneOrNickname(volunteer, volunteers);

    // Assert - Then
    assertEquals(1, result.size());
  }

  @Test
  public void getOccurenceByEmailOrPhoneOrNickname_WithEmptyNickname() {
    // Arrange - Given
    Volunteer volunteer = new Volunteer("Bibi", "DODO", "", "", "0065555734");

    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Frog", "MAN", "Didoudou", "gaylord.manaudou@example.org", "0065555739"),
        new Volunteer("Bibi", "DODO", "Didoudou", "", "0102030405"),
        new Volunteer("John", "DOE", "", "John.DOE@example.org", "0908070605"));

    // Act - When
    List<Volunteer> result = Sort.getOccurenceByEmailOrPhoneOrNickname(volunteer, volunteers);

    // Assert - Then
    assertEquals(0, result.size());
  }

}
