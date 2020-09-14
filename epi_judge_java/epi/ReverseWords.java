package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class ReverseWords {

  public static void reverseWords(char[] input) {
    // TODO - you fill in here.
    int s = 0, e = input.length - 1;
    while (s < e) {
      char temp = input[s];
      input[s] = input[e];
      input[e] = temp;
      s++;
      e--;
    }

    s = 0;
    e = 0;
    while (s < input.length) {
      // Search for the start of a word
      while (e < input.length && input[e] == ' ') e++;
      s = e;

      // Seach for end of a word
      while (e + 1 < input.length && input[e+1] != ' ') e++;
      reverse(input, s, e);
      e += 1;
    }
  }

  private static void reverse(char[] A, int s, int e) {
    while (s < e) {
      char temp = A[s];
      A[s] = A[e];
      A[e] = temp;
      s++;
      e--;
    }
  }
  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
