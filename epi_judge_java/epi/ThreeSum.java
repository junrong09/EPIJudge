package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class ThreeSum {
  @EpiTest(testDataFile = "three_sum.tsv")

  public static boolean hasThreeSum(List<Integer> A, int t) {
    // TODO - you fill in here.
    // O(n^2), O(1)
    A.sort((a, b) -> a - b);
    for (int a : A) {
      if (check(A, t - a)) {
        return true;
      }
    }
    return false;
  }

  public static boolean check(List<Integer> A, int t) {
    int i = 0, j = A.size() - 1;
    while (i <= j) {
      int sum = A.get(i) + A.get(j);
      if (sum == t) {
        return true;
      } else if (sum > t) {
        j--;
      } else {
        i++;
      }

    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ThreeSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
