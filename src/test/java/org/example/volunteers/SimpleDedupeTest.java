package org.example.volunteers;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.example.volunteers.cleanup.CleanUp;
import org.example.volunteers.utils.VolunteerParser;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.junit.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(DataProviderRunner.class)
public class SimpleDedupeTest {
  @DataProvider
  public static Object[][] dataProviderVolunteers() {
    String data1 = """
         Guilloux;Sarah;;sarah_guilloux@example.org;+33085552877
         Guilloux;Sarah;;sarah_guilloux@example.org;+33085552877
         Thévenet;Camille;;camille_thevenet@example.net;+33007709351
         Beaudouin;Benoît;;benoit_beaudouin@example.net;+33099395922
         LOZÉ;LESLY;Fledgling;Fledgling4390@example.org;+33045550388
         Bethune;Lauurent;Leaf123;Leaf1235364@example.com;+33055542145
         Abbadie;Nicolas;;nicolasabbadie@example.com;
        """;

    String data2 = """
         Guilloux;Sarah;;sarah_guilloux@example.org;+33085552877
         Thévenet;Camille;;camille_thevenet@example.net;+33007709351
         Beaudouin;Benoît;;benoit_beaudouin@example.net;+33099395922
         Beaudouin;Benoît;;benoit_beaudouin@example.net;+33099395922
         LOZÉ;LESLY;Fledgling;Fledgling4390@example.org;+33045550388
        """;

    String data3 = """
         Abbadie;Nicolas;;nicolasabbadie@example.com;
         Guilloux;Sarah;;sarah_guilloux@example.org;+33085552877
         Thévenet;Camille;;camille_thevenet@example.net;+33007709351
         Beaudouin;Benoît;;benoit_beaudouin@example.net;+33099395922
         Bethune;Lauurent;Leaf123;Leaf1235364@example.com;+33055542145
         Abbadie;Nicolas;;nicolasabbadie@example.com;
         Abbadie;Nicolas;;nicolasabbadie@example.com;
        """;

    return new Object[][]{
        { VolunteerParser.listFromString(data1), 6 },
        { VolunteerParser.listFromString(data2), 4 },
        { VolunteerParser.listFromString(data3), 5 },
    };
  }


  @Test
  @UseDataProvider(value = "dataProviderVolunteers")
  public void shouldReturnDedupedVolunteers(List<Volunteer> volunteers, int  expectedDedupedListSize) {
    // When
    Map<String, Volunteer> cleanedList = CleanUp.clean2(volunteers);

    // Then
    assertEquals(expectedDedupedListSize, cleanedList.size());
  }
}
