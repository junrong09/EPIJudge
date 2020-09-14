package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsStringPalindromicPunctuation {
  @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")

  public static boolean isPalindrome(String string) {
    // TODO - you fill in here.
    char[] c = string.toCharArray();
    int s = 0, e = c.length - 1;
    while (s < e) {
      if (!Character.isLetterOrDigit(c[s])) {
        s++;
        continue;
      }

      if (!Character.isLetterOrDigit(c[e])) {
        e--;
        continue;
      }

      if (Character.toLowerCase(c[s]) != Character.toLowerCase(c[e])) {
        return false;
      }

      s++;
      e--;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
