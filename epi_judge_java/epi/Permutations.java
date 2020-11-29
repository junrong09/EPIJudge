package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.Collections;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
public class Permutations {
  @EpiTest(testDataFile = "permutations.tsv")

  public static List<List<Integer>> permutations(List<Integer> A) {
    List<Integer> empty = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();
    permute(new HashSet<>(A), empty, result);
    return result;
  }

  public static void permute(Set<Integer> A, List<Integer> nums, List<List<Integer>> result) {
    if (A.isEmpty()) {
      result.add(nums);
    }

    for (Integer a: A) {
      List<Integer> newNums = new ArrayList(nums);
      newNums.add(a);
      Set<Integer> newA = new HashSet<>(A);
      newA.remove(a);
      permute(newA, newNums, result);
    }
  }

  @EpiTestComparator
  public static boolean comp(List<List<Integer>> expected,
      List<List<Integer>> result) {
    if (result == null) {
      return false;
    }
    for (List<Integer> l : expected) {
      Collections.sort(l);
    }
    expected.sort(new LexicographicalListComparator<>());
    for (List<Integer> l : result) {
      Collections.sort(l);
    }
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
        .runFromAnnotations(args, "Permutations.java",
          new Object() {}.getClass().getEnclosingClass())
        .ordinal());
  }
}
