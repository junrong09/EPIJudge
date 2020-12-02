package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.*;
public class LevenshteinDistance {
  @EpiTest(testDataFile = "levenshtein_distance.tsv")

  public static int levenshteinDistance(String A, String B) {
    // TODO - you fill in here.
    // O(n^2), O(n^2)
    Map<List<Integer>, Integer> map = new HashMap<>();
    return distance(A, A.length() - 1, B, B.length() - 1, map);
  }

  public static int distance(String A, int a, String B, int b, Map<List<Integer>, Integer> map) {
    if (map.containsKey(Arrays.asList(a, b))) {
      return map.get(Arrays.asList(a, b));
    }
    int val = 0;
    if (b < 0) {
      val = a + 1;
    } else if (a < 0) {
      val = b + 1;
    } else if (A.charAt(a) == B.charAt(b)) {
      val = distance(A, a - 1, B, b - 1, map);
    } else {
      val = 1 + Math.min(Math.min(distance(A, a - 1, B, b - 1, map), distance(A, a, B, b - 1, map)), distance(A, a - 1, B, b, map));
    }
    map.put(Arrays.asList(a, b), val);
    return val;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LevenshteinDistance.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
