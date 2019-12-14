package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Map;
import java.util.HashMap;

public class IsStringPermutableToPalindrome {
  @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

  public static boolean canFormPalindrome(String s) {
    // TODO - you fill in here.
    char[] cc = s.toCharArray();
    boolean allowOdd = cc.length % 2 == 1;
    Map<Character, Integer> map = new HashMap<>();
    for (char c : cc) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    for (Map.Entry<Character, Integer> entry: map.entrySet()) {
      if (entry.getValue() % 2 == 1) {
        if (allowOdd) {
          allowOdd = false;
        } else {
          return false;
        }
      }
    }
    return true;
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
