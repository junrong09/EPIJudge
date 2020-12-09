package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
public class TwoSortedArraysMerge {

  public static void mergeTwoSortedArrays(List<Integer> A, int m,
      List<Integer> B, int n) {
    int ptr1 = m - 1, ptr2 = n - 1, ptrAdd = m + n - 1;
    while (ptr1 > -1 && ptr2 > -1) {
      int a = A.get(ptr1), b = B.get(ptr2);
      if (a > b) {
        A.set(ptrAdd--, a);
        ptr1--;
      } else {
        A.set(ptrAdd--, b);
        ptr2--;
      }
    }
    while (ptr2 > -1) {
      A.set(ptrAdd--, B.get(ptr2--));
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
