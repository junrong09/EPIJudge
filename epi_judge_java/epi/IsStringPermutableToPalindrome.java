package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.*;
public class IsStringPermutableToPalindrome {
  @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

  public static boolean canFormPalindrome(String s) {
    // TODO - you fill in here.
    // O(c), O(d)
    Map<Character, Integer> map = new HashMap<>();
    for (char c : s.toCharArray()) {
      int count = 0;
      if (map.containsKey(c)) {
        count = map.get(c);
      }
      count += 1;
      map.put(c, count);
    }
    boolean odd = false;
    for (int v : map.values()) {
      if (v % 2 > 0) {
        if (odd) return false;
        odd = true;
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
