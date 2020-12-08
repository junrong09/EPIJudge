package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.*;
import java.util.List;
import java.util.function.BiPredicate;
public class Anagrams {
  @EpiTest(testDataFile = "anagrams.tsv")

  public static List<List<String>> findAnagrams(List<String> dictionary) {
    // TODO - you fill in here.
    // O(n * m lg m); O(n)
    Map<String, List<String>> result = new HashMap<>();
    for (String s : dictionary) {
      char[] chars = s.toCharArray();
      Arrays.sort(chars);
      String sortedString = new String(chars);
      if (result.containsKey(sortedString)) {
        List<String> l = result.get(sortedString);
        l.add(s);
      } else {
        List<String> l = new ArrayList<>();
        l.add(s);
        result.put(sortedString, l);
      }
    }
    List<List<String>> A = new ArrayList<List<String>>();
    for (List<String> r : result.values()) {
      if (r.size() > 1) {
        A.add(r);
      }
    }
    return A;
  }

  @EpiTestComparator
  public static boolean comp(List<List<String>> expected,
      List<List<String>> result) {
    if (result == null) {
      return false;
    }
    for (List<String> l : expected) {
      Collections.sort(l);
    }
    expected.sort(new LexicographicalListComparator<>());
    for (List<String> l : result) {
      Collections.sort(l);
    }
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
        .runFromAnnotations(args, "Anagrams.java",
          new Object() {}.getClass().getEnclosingClass())
        .ordinal());
  }
}
