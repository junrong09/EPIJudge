package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MaxTrappedWater {
  @EpiTest(testDataFile = "max_trapped_water.tsv")

  public static int getMaxTrappedWater(List<Integer> heights) {
    // TODO - you fill in here.
    int s = 0, e = heights.size() - 1, ms = s, me = e, max = Integer.MIN_VALUE;
    while (s < e) {
      int value = (e-s) * Math.min(heights.get(s), heights.get(e));
      if (value > max) {
        max = value;
        ms = s;
        me = e;
      }

      if (heights.get(s) < heights.get(e)) {
        s++;
      } else {
        e--;
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
