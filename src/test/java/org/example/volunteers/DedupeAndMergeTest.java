package org.example.volunteers;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.example.volunteers.cleanup.CleanUp;
import org.example.volunteers.utils.VolunteerParser;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(DataProviderRunner.class)
public class DedupeAndMergeTest {
  @DataProvider
  public static Object[][] dataProviderVolunteers() {
    String data1 = """
        Sarah;Guilloux;;sarah_guilloux@example.org;+33085552877
        Sarah;Guilloux;;sarah_guilloux@example.org;+33085552877
        Camille;Thévenet;;camille_thevenet@example.net;+33007709351
        Camille;;;camille_thevenet@example.net;
        """;

    // Create a list of deduped and merged volunteers from data1
    List<Volunteer> volunteers1 = new ArrayList<>(List.of(
        new Volunteer(
            "Sarah",
            "GUILLOUX",
            "",
            "sarah_guilloux@example.org",
            "+33085552877"
        ),
        new Volunteer(
            "Camille",
            "THÉVENET",
            "",
            "camille_thevenet@example.net",
            "+33007709351"
        )
    ));

    String data2 = """
        Benoît;Beaudouin;;benoit_beaudouin@example.net;+33099395922
        ;Beaudouin;"EdgeLordDu38";benoit_beaudouin@example.net;+0099395922
        LESLY;LOZÉ;Fledgling;Fledgling4390@example.org;+33045550388
        """;

    List<Volunteer> volunteers2 = new ArrayList<>(List.of(
        new Volunteer(
            "Benoît",
            "BEAUDOUIN",
            "EdgeLordDu38",
            "benoit_beaudouin@example.net",
            "+33099395922"
        ),
        new Volunteer(
            "Lesly",
            "LOZÉ",
            "Fledgling",
            "fledgling4390@example.org",
            "+33045550388"
        )
    ));

    String data3 = """
        Nicolas;Abbadie;;nicolasabbadie@example.com;
        Nicolas;;;nicolasabbadie@example.com;
        ;Abbadie;;nicolasabbadie@example.com;+33012345678
        """;

    List<Volunteer> volunteers3 = new ArrayList<>(List.of(
        new Volunteer(
            "Nicolas",
            "ABBADIE",
            "",
            "nicolasabbadie@example.com",
            "+33012345678"
        )
    ));

    return new Object[][] {
        { VolunteerParser.listFromString(data1), volunteers1 },
        { VolunteerParser.listFromString(data2), volunteers2 },
        { VolunteerParser.listFromString(data3), volunteers3 },
    };
  }


  @Test
  @UseDataProvider(value = "dataProviderVolunteers")
  public void shouldReturnDedupedAndMergedFromEmailVolunteers(
      List<Volunteer> volunteers,
      List<Volunteer> expectedDedupedAndMergedList
  ) {
    // When
    Map<String, Volunteer> cleanedList = CleanUp.clean2(volunteers);

    // Then
    int volunteerIndex = 0;
    for(Volunteer cleanedVolunteer : cleanedList.values()) {
      assertThat(expectedDedupedAndMergedList.get(volunteerIndex)).usingRecursiveComparison().isEqualTo(cleanedVolunteer);
      volunteerIndex++;
    }
  }
}
