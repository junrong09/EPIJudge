package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class IntersectSortedArrays {
  @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
    // TODO - you fill in here.
    //return implementation1(A,B);
    return implementation2(A,B);
  }

  // O(n + m)
  private static List<Integer> implementation1(List<Integer> A, List<Integer> B) {
    List<Integer> result = new ArrayList<>();
    int a = 0, b = 0, found = 0;
    while (a < A.size() && b < B.size()) {
      if (A.get(a) == B.get(b)) {
        if (A.get(a) != found || result.isEmpty()) {
          found = A.get(a);
          result.add(A.get(a));
        }
        a++;
        b++;
      } else if (A.get(a) > B.get(b)) {
        b++;
      } else {
        a++;
      }
    }
    return result;
  }

  // O( n log m), m > n
  private static List<Integer> implementation2(List<Integer> A, List<Integer> B) {
    List<Integer> result = new ArrayList<>();
    if (A.size() > B.size()) {
      List<Integer> temp = B;
      B = A;
      A = temp;
    }

    int prev = A.isEmpty() ? 0 : A.get(0) - 1;
    for (int v : A) {
      if (v != prev && Collections.binarySearch(B, v) >= 0) {
        result.add(v);
      }
      prev = v;
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
