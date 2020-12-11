package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseDigits {
  @EpiTest(testDataFile = "reverse_digits.tsv")
  public static long reverse(int x) {
    // TODO - you fill in here.
    // O(d); O(1)
    int temp = x;
    long result = 0;
    while (temp != 0) {
      int d = temp % 10;
      result += d;
      temp /= 10;
      result *= 10;
    }
    result /= 10;
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
