package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchShiftedSortedArray {
  @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

  public static int searchSmallest(List<Integer> A) {
    // TODO - you fill in here.
    // O(lg n); O(1)
    int pivot = A.get(0);
    int s = 0, e = A.size() - 1;
    while (s <= e) {
      int m = s + (e-s) / 2;
      if (m - 1 >= 0 && A.get(m-1) > A.get(m)) {
        return m;
      }
      if (pivot > A.get(m)) {
        e = m - 1;
      } else {
        s = m + 1;
      }
    }
    return 0;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
