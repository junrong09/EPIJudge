package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
public class NearestRepeatedEntries {
  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")

  public static int findNearestRepetition(List<String> paragraph) {
    // TODO - you fill in here.
    // O(n); O(d)
    Map<String, Integer> map = new HashMap<>();
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < paragraph.size(); i++) {
      String s = paragraph.get(i);
      Integer pos = map.get(s);
      if (pos == null) {
        map.put(s, i);
      } else {
        min = Math.min(min, i - pos);
        map.put(s, i);
      }
    }
    return min == Integer.MAX_VALUE ? -1 : min;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
