package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.*;
public class IsAnonymousLetterConstructible {
  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

  public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                          String magazineText) {
    // TODO - you fill in here.
    // O(LM); O(M)
    Map<Character, Integer> charInMag = new HashMap<>();
    for (char c : magazineText.toCharArray()) {
      int count = 0;
      if (charInMag.containsKey(c)) {
        count = charInMag.get(c);
      }
      charInMag.put(c, count + 1);
    }
    for (char c : letterText.toCharArray()) {
      if (!charInMag.containsKey(c)) {
        return false;
      }
      int count = charInMag.get(c) - 1;
      if (count < 0) {
        return false;
      }
      charInMag.put(c, count);
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
