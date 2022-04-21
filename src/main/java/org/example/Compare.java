package org.example;

import org.apache.commons.text.similarity.LevenshteinDistance;

public class Compare {

  public static int compareTo(String strA, String strB) {

    strA = Format.removeEspace(strA);
    strB = Format.removeEspace(strB);
    LevenshteinDistance ld = new LevenshteinDistance();
    return ld.apply(strA, strB);
  }

}