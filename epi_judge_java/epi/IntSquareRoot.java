package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int k) {
    // TODO - you fill in here.
    // O(lg n); O(1)
    int s = 0, e = k, found = 0;
    while (s <= e) {
      int m = (e-s)/2 + s;
      long mSq = (long) m * m;
      if (mSq == k) {
        return m;
      } else if (mSq > k) {
        e = m - 1;
      } else {
        found = Math.max(found, m);
        s = m + 1;
      }
    }
    return found;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
