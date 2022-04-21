package org.example.volunteers.cleaner;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.example.volunteers.Cleaner;
import org.example.volunteers.Volunteer;
import org.example.volunteers.utils.VolunteerParser;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class CleanByPhoneTest {
  @DataProvider
  public static Object[][] dataProviderVolunteersWithSamePhone() {
    String[][] volunteersData1 = new String[][]{
        {"Sarah", "Guilloux", "", "sarah_guilloux@example.org", "+33085552877"},
        {"Sarah", "Guilloux", "Sarouh", "", "+33085552877"},
    };
    String[][] expectedVolunteersData1 = new String[][]{
        {"Sarah", "Guilloux", "Sarouh", "sarah_guilloux@example.org", "+33085552877"},
    };

    // Create a list of deduped and merged volunteers from data1
    List<Volunteer> volunteers1 = VolunteerParser.parseVolunteersFromStringArray(volunteersData1);
    List<Volunteer> expectedCleanedVolunteers1 = VolunteerParser.parseVolunteersFromStringArray(expectedVolunteersData1);

    String[][] volunteersData2 = new String[][]{
        {"Benoît", "Beaudouin", "bebe", "benoit_beaudoin@example.org", "+33099395922"},
        {"Benoît", "Beaudouin", "", "", "+33099395922"},
    };
    String[][] expectedVolunteersData2 = new String[][]{
        {"Benoît", "Beaudouin", "bebe", "benoit_beaudoin@example.org", "+33099395922"},
    };

    // Create a list of deduped and merged volunteers from data2
    List<Volunteer> volunteers2 = VolunteerParser.parseVolunteersFromStringArray(volunteersData2);
    List<Volunteer> expectedCleanedVolunteers2 = VolunteerParser.parseVolunteersFromStringArray(expectedVolunteersData2);

    return new Object[][] {
        { volunteers1, expectedCleanedVolunteers1 },
        { volunteers2, expectedCleanedVolunteers2 },
    };
  }

  @DataProvider
  public static Object[][] dataProviderVolunteersWithDifferentPhone() {
    String[][] volunteersData1 = new String[][]{
        {"Sarah", "Guilloux", "", "sarah_guilloux@example.org", "+33085552878"},
        {"Sarah", "Guilloux", "Sarouh", "", "+33085552877"},
    };

    // Create a list of deduped and merged volunteers from data1
    List<Volunteer> volunteers1 = VolunteerParser.parseVolunteersFromStringArray(volunteersData1);

    String[][] volunteersData2 = new String[][]{
        {"Benoît", "Beaudouin", "bebe", "benoit_beaudoin@example.org", "+33099395922"},
        {"Benoît", "Beaudouin", "", "", "+33099395921"},
    };

    // Create a list of deduped and merged volunteers from data2
    List<Volunteer> volunteers2 = VolunteerParser.parseVolunteersFromStringArray(volunteersData2);

    return new Object[][] {
        { volunteers1 },
        { volunteers2 },
    };
  }


  @Test
  @UseDataProvider(value = "dataProviderVolunteersWithSamePhone")
  public void shouldDedupedAndMergeVolunteersWithSamePhone(
      List<Volunteer> volunteers,
      List<Volunteer> expectedDedupedAndMergedList
  ) {
    // When
    ArrayList<Volunteer> cleanedList = Cleaner.mergeByPhoneNumber(volunteers);

    // Then
    int volunteerIndex = 0;
    for(Volunteer cleanedVolunteer : cleanedList) {
      assertThat(expectedDedupedAndMergedList.get(volunteerIndex)).usingRecursiveComparison().isEqualTo(cleanedVolunteer);
      volunteerIndex++;
    }
  }

  @Test
  @UseDataProvider(value = "dataProviderVolunteersWithDifferentPhone")
  public void shouldNotDedupedAndMergeVolunteersWithDifferentPhone(List<Volunteer> volunteers) {
    // When
    ArrayList<Volunteer> cleanedList = Cleaner.mergeByPhoneNumber(volunteers);

    // Then
    int volunteerIndex = 0;
    for(Volunteer volunteer : volunteers) {
      assertThat(cleanedList.get(volunteerIndex)).usingRecursiveComparison().isEqualTo(volunteer);
      volunteerIndex++;
    }
  }
}
