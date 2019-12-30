package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseDigits {
  @EpiTest(testDataFile = "reverse_digits.tsv")
  public static long reverse(int x) {
    // TODO - you fill in here.
    long n = 0;
    boolean neg = x < 0;
    if (neg) x *= -1;
    while (x != 0) {
      n += x % 10;
      x /= 10;
      n *= 10;
    }
    n /= 10;
    if (neg) n *= -1;
    return n;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
