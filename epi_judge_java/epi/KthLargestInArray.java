package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.Collections;
import java.util.Random;

public class KthLargestInArray {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(A, 1) returns 3, findKthLargest(A, 2) returns 2,
  // findKthLargest(A, 3) returns 1, and findKthLargest(A, 4) returns -1.
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  public static int findKthLargest(int k, List<Integer> A) {
    // TODO - you fill in here.
    return find(k, A, 0, A.size() - 1);
  }

  public static int find(int k, List<Integer> A, int s, int e) {
    for (int i = s; i <= e; i++) {
      System.out.printf("%d ", A.get(i));
    }
    System.out.println();

    if (k == 1) {
      int max = Integer.MIN_VALUE;
      for (int i = s; i <= e; i++) {
        max = A.get(i) > max ? A.get(i) : max;
      }
      return max;
    }

    int p1 = s, p2 = s, pivot = A.get(s + new Random().nextInt(e - s + 1));
    while (p2 <= e) {
      if (A.get(p2) < pivot) {
        Collections.swap(A, p1, p2);
        p1++;
      }
      p2++;
    }

    if (e-p1+1 >= k) {
      return find(k, A, p1, e);
    } else {
      return find(k - (e-p1+1), A, s, p1 - 1);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
