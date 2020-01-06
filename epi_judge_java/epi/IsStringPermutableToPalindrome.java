package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Set;
import java.util.HashSet;

public class IsStringPermutableToPalindrome {
  @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

  public static boolean canFormPalindrome(String s) {
    // TODO - you fill in here.
    Set<Character> set = new HashSet<>();
    char[] cc = s.toCharArray();
    for (char c : cc) {
      if (!set.remove(c)) {
        set.add(c);
      }
    }
    return (set.size() <= 1);
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
