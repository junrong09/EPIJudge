package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
public class KthLargestInArray {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(1, A) returns 3, findKthLargest(2, A) returns 2,
  // findKthLargest(3, A) returns 1, and findKthLargest(4, A) returns -1.
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  public static int findKthLargest(int k, List<Integer> A) {
    // TODO - you fill in here.
    // O(n); O(lg n)
    // Given that on avg T(n) = T(n/2) + O(n)
    return search(0, A.size() - 1, k, A);
  }

  public static int search(int s, int e, int k, List<Integer> A) {
    int r = randomize(s,e);
    Collections.swap(A, r, e);
    int pivot = s, pivotNum = A.get(e);
    for (int i = s; i <= e; i++) {
      if (A.get(i) <= pivotNum) {
        Collections.swap(A, pivot++, i);
      }
    }
    pivot -= 1;
    int position = e - pivot + 1;
    if (position == k) return pivotNum;
    if (position > k) {
      return search(pivot+1, e, k, A);
    } else {
      return search(s, pivot-1, k-position, A);
    }
  }

  public static int randomize(int s, int e) {
    int len = e - s + 1;
    double ran = Math.random();
    return s + (int)(ran / (1/(float)len));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
