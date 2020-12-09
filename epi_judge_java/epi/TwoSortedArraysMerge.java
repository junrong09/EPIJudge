package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
public class TwoSortedArraysMerge {

  public static void mergeTwoSortedArrays(List<Integer> A, int m,
      List<Integer> B, int n) {
    // TODO - you fill in here.
    // O(n); O(1)``
    int ptr1 = A.size() - 1;
    for (int i = m - 1; i > -1; i--) {
      Collections.swap(A, i, ptr1--);
    }
    ptr1++;
    int ptr2 = 0, ptrAdd = 0;
    while (ptr1 < A.size() && ptr2 < n) {
      int a = A.get(ptr1), b = B.get(ptr2);
      if (a < b) {
        A.set(ptrAdd++, a);
        if (ptrAdd - 1 != ptr1) A.set(ptr1, 0);
        ptr1++;
      } else {
        A.set(ptrAdd++, b);
        ptr2++;
      }
    }
    while (ptr1 < A.size()) {
      A.set(ptrAdd++, A.get(ptr1));
      if (ptrAdd - 1 != ptr1 )A.set(ptr1, 0);
      ptr1++;
    }
    while (ptr2 < n) {
      A.set(ptrAdd++, B.get(ptr2++));
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
