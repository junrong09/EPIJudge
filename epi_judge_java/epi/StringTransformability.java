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
        for (String d : D) {
          if (isSimilar(k, d)) {
            //D.remove(d); // Cannot remove and use its iterator concurrently
            nextSet.add(d);
          }
        }
      }
      D.removeAll(nextSet);
      level++;
      set = nextSet;
    }
    return -1;
  }

  private static boolean isSimilar(String s1, String s2) {
    if (s1.length() != s2.length()) return false;
    int chance = 1;
    char[] ss1 = s1.toCharArray(), ss2 = s2.toCharArray();
    for (int i = 0; i < s1.length(); i++) {
      if (ss1[i] != ss2[i]) {
        if (chance < 1) return false;
        chance--;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringTransformability.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
