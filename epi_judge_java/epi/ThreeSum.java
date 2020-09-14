package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class ThreeSum {
  @EpiTest(testDataFile = "three_sum.tsv")

  public static boolean hasThreeSum(List<Integer> A, int t) {
    // TODO - you fill in here.
    A.sort((x,y) -> x-y);
    for (int a : A) {
      int v = t - a;
      int s = 0, e = A.size() - 1;
      while (s <= e) {
        if (A.get(s) + A.get(e) > v) {
          e--;
        } else if (A.get(s) + A.get(e) < v) {
          s++;
        } else {
          return true;
        }
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
