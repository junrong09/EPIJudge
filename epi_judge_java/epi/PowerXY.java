package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PowerXY {
  @EpiTest(testDataFile = "power_x_y.tsv")
  public static double power(double x, int y) {
    // TODO - you fill in here.
    if (y == 0) {
      return 1;
    } else if (y > 0) {
      double v = power(x, y/2);
      if (y % 2 == 0) {
        return v * v;
      } else {
        return v * v * x;
      }
    } else {
      return power(1.0/x, -1 * y);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerXY.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
