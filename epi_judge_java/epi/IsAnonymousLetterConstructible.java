package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Map;
import java.util.HashMap;

public class IsAnonymousLetterConstructible {
  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

  public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                          String magazineText) {
    // TODO - you fill in here.
    Map<Character, Integer> map = new HashMap<>();
    for (char c : letterText.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    for (char c : magazineText.toCharArray()) {
      if (map.containsKey(c))
        map.put(c, map.get(c) - 1);
    }

    for (int i : map.values()) {
      if (i > 0) return false;
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
