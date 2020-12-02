package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class MaxTrappedWater {
  @EpiTest(testDataFile = "max_trapped_water.tsv")

  public static int getMaxTrappedWater(List<Integer> heights) {
    // TODO - you fill in here.
    // O(n); O(1)
    int max = 0, i = 0, j = heights.size() - 1;
    while (i < j) {
      int area = (j - i) * Math.min(heights.get(i), heights.get(j));
      max = Math.max(area, max);
      if (heights.get(i) > heights.get(j)) {
        j--;
      } else if (heights.get(i) < heights.get(j)) {
        i++;
      } else {
        i++;
        j--;
      }
    }
    return max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxTrappedWater.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
