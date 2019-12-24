package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int k) {
    // TODO - you fill in here.
    int s = 0, e = k;
    while (s != e && s+1 != e) {
      int m = s + (e-s)/2;
      if ((long) m*m == k) {
        return m;
      } else if ((long) m*m < k) {
        s = m;
      } else {
        e = m - 1;
      }
    }
    return e * e > k ? s : e;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
