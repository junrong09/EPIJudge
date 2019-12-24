package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.Collections;

public class TwoSortedArraysMerge {

  public static void mergeTwoSortedArrays(List<Integer> A, int m,
                                          List<Integer> B, int n) {
    // TODO - you fill in here.
    int p = m + n;
    for (int i = m - 1; i >= 0; i--) {
      Collections.swap(A, i, --p);
    }

    int p2 = 0, i = 0;
    while (p2 < n && p < m + n) {
      if (A.get(p) < B.get(p2)) {
        A.set(i, A.get(p));
        p++;
      } else {
        A.set(i, B.get(p2));
        p2++;
      }
      i++;
    }

    while (p2 < n) {
      A.set(i++, B.get(p2++));
    }

    while (p < m + n) {
      A.set(i++, A.get(p++));
    }
  }
  @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
  public static List<Integer>
  mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
    mergeTwoSortedArrays(A, m, B, n);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TwoSortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
