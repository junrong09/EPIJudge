package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  public static String intToString(int x) {
    // TODO - you fill in here.
    StringBuilder sb = new StringBuilder();
    boolean neg = x < 0;
    while (x != 0) {
	    int v = Math.abs(x % 10);
	    x /= 10;
	    sb.append(v);
    }
    if (neg) sb.append('-');
    return sb.length() == 0 ? "0" : sb.reverse().toString();
  }

  public static int stringToInt(String s) {
    // TODO - you fill in here.
    char[] cc = s.toCharArray();
    long result = 0;

    for (char c : cc) {
	    if (c != '-') {
		    result = (result * 10) + (c - '0');
	    }
    }
    if (cc[0] == '-') result *= -1;
    return (int) result;
  }

  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (Integer.parseInt(intToString(x)) != x) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
