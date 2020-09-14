package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Set;
import java.util.HashSet;

public class StringTransformability {
  @EpiTest(testDataFile = "string_transformability.tsv")
  public static int transformString(Set<String> D, String s, String t) {
    // TODO - you fill in here.
    int level = 0;
    Set<String> set = new HashSet<>();
    set.add(s);
    D.remove(s);

    while (!set.isEmpty()) {
      Set<String> nextSet = new HashSet<>();
      if (set.contains(t)) return level;
      for (String k : set) {
        for (int i = 0; i < k.length(); i++) {
          String start = k.substring(0, i);
          String end = k.substring(i + 1, k.length());
          for (int j = 0; j < 26; j++) {
            String temp = start + (char)('a' + j) + end;
            if (D.remove(temp)) {
              nextSet.add(temp);
            }
          }
        }
      }
      level++;
      set = nextSet;
    }
    return -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringTransformability.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
