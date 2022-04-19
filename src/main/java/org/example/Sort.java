package org.example;

import java.util.List;
import org.example.volunteers.Volunteer;
import java.util.ArrayList;

public class Sort {

  public static List<Volunteer> removeDuplicate(List<Volunteer> volunteers) {
    List<Volunteer> result = new ArrayList<>();
    for (Volunteer volunteer : volunteers) {
      if (!result.contains(volunteer)) {
        result.add(volunteer);
      }
    }
    return result;
  }
  // search in list of volunteers, same adresse mail with 3 characters mutation

}