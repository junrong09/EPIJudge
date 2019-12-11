package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import java.util.ArrayList;
import java.util.List;
public class StringIntegerInterconversion {

  public static String intToString(int x) {
    // TODO - you fill in here.
    StringBuilder sb = new StringBuilder();
    List<Character> list = new ArrayList<>();
    boolean neg = x < 0;
    x = Math.abs(x);
    while (x != 0) {
	    int v = x % 10;
	    x /= 10;
	    list.add((char) (v + '0'));
    }
    if (neg) list.add('-');
    for (int i = list.size() - 1; i >= 0; i--) {
	    sb.append(list.get(i));
    }
    String s = sb.toString();
    return s.isEmpty() ? "0" : s;
  }

  public static int stringToInt(String s) {
    // TODO - you fill in here.
    int result = 0, digit = 0;
    char[] cc = s.toCharArray();
    while (digit < cc.length) {
	    switch (cc[cc.length - digit - 1]) {
		    case '-':
			    result *= -1;
			    break;
		    default:
			    int val = cc[cc.length - digit - 1] - '0';
			    result += val * Math.pow(10, digit);
			    
	    }
	    digit++;
    }
    return result;
  }

  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (!intToString(x).equals(s)) {
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
