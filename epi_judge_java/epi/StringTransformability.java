package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
public class StringTransformability {
  @EpiTest(testDataFile = "string_transformability.tsv")
  public static int transformString(Set<String> D, String s, String t) {
    // TODO - you fill in here.
    // O(n^2); O(n)
    Set<String> visited = new HashSet<>();
    int len = 0;
    Queue<String> toVisit = new LinkedList<>();
    toVisit.add(s);
    toVisit.add("");
    while (!toVisit.isEmpty()) {
      String visiting = toVisit.poll();
      if (visited.contains(visiting)) {
        continue;
      }
      if (visiting.equals("")) {
        len++;
        if (!toVisit.isEmpty()) {
          toVisit.add("");
        }
        continue;
      }
      if (visiting.equals(t)) {
        return len;
      }
      visited.add(visiting);
      addAllRelated(visiting, D, toVisit);
    }
    return -1;
  }

  public static void addAllRelated(String s, Set<String> D, Queue<String> toVisit) {
    for (String d : D) {
      if (s.length() != d.length()) {
        continue;
      }
      int diff = 0;
      for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) != d.charAt(i)) {
          diff++;
        }
      }
      if (diff == 1) toVisit.add(d);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringTransformability.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
