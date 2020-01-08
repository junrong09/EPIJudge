package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.function.BiPredicate;
public class Permutations {
  @EpiTest(testDataFile = "permutations.tsv")

  public static List<List<Integer>> permutations(List<Integer> A) {
    // TODO - you fill in here.
    List<List<Integer>> list = new ArrayList<>();
    List<Set<Integer>> remain = new ArrayList<>();
    list.add(new ArrayList<>());
    remain.add(new HashSet(A));
    while (remain.get(0).size() > 0) {
      List<List<Integer>> newList = new ArrayList<>();
      List<Set<Integer>> newRemain = new ArrayList<>();
      for (int i = 0; i < list.size(); i++) {
        for (int v : remain.get(i)) {
          newList.add(new ArrayList(list.get(i)));
          newList.get(newList.size() - 1).add(v);
          newRemain.add(new HashSet(remain.get(i)));
          newRemain.get(newRemain.size() - 1).remove(v);
        }
      }
      list = newList;
      remain = newRemain;
    }
    return list;
  }
  @EpiTestComparator
  public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
      (expected, result) -> {
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
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Permutations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
