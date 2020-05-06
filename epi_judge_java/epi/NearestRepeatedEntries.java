package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class NearestRepeatedEntries {
  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")

  public static int findNearestRepetition(List<String> paragraph) {
    // TODO - you fill in here.
    int minDistance = Integer.MAX_VALUE;
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < paragraph.size(); i++) {
      if (map.containsKey(paragraph.get(i))) {
        minDistance = Math.min(minDistance, i - map.get(paragraph.get(i)));
      }
      map.put(paragraph.get(i), i);
    }
    return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
