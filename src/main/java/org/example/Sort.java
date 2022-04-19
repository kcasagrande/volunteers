package org.example;

import java.util.List;
import org.example.volunteers.Volunteer;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.*;

public class Sort {

  public static List<Volunteer> removeDuplicate(List<Volunteer> list) {

    return new ArrayList(new HashSet(list));
  }

  

}