package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")

  public static int searchFirstOfK(List<Integer> A, int k) {
    // TODO - you fill in here.
    // O(lg n); O(1)
    int s = 0, e = A.size() - 1, found = -1;
    while (s <= e) {
      int m = ((e - s) / 2) + s;
      if (A.get(m) == k) {
        found = m;
      }
      if (A.get(m) < k) {
        s = m + 1;
        continue;
      }
      e = m - 1;
    }
    return found;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
