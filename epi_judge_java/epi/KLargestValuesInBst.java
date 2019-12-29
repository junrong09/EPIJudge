package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.ArrayList;
public class KLargestValuesInBst {
  @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")

  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
    // TODO - you fill in here.
    List<Integer> result = new ArrayList<>();
    find(tree, k, result);
    return result;
  }

  private static void find(BstNode<Integer> tree, int k, List<Integer> result) {
    if (tree == null || result.size() >= k) return;

    find(tree.right, k, result);
    if (result.size() < k) result.add(tree.data);
    find(tree.left, k, result);
  }
  @EpiTestComparator
  public static BiPredicate<List<Integer>, List<Integer>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestValuesInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
