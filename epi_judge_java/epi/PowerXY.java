package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PowerXY {
  @EpiTest(testDataFile = "power_x_y.tsv")
  public static double power(double x, int y) {
    // TODO - you fill in here.
    if (y == 1) return x;
    if (y == 0) return 1;
    boolean neg = false;
    if (y < 0) {
      neg = true;
      y *= -1;
    }
    double result = power(x*x, y/2);
    if (y % 2 == 1) {
      result *= x;
    }
    if (neg) {
      result = 1/result;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
        .runFromAnnotations(args, "PowerXY.java",
          new Object() {}.getClass().getEnclosingClass())
        .ordinal());
  }
}
