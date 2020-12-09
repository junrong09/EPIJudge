package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
public class IntersectSortedArrays {
  @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
      List<Integer> B) {
    // TODO - you fill in here.
    // O(n + m); O(1)
    List<Integer> result = new ArrayList<>();
    int i = 0, j = 0;
    while (i < A.size() && j < B.size()) {
      Integer r = null, a = A.get(i), b = B.get(j);
      if (!result.isEmpty()) {
        r = result.get(result.size() - 1);
      }
      if (a > b) {
        j++;
      } else if (b > a) {
        i++;
      } else {
        if (r != a) {
          result.add(a);
        }
        i++;
        j++;
      }
    }
    return result;
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
        .runFromAnnotations(args, "IntersectSortedArrays.java",
          new Object() {}.getClass().getEnclosingClass())
        .ordinal());
  }
}
