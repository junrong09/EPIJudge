package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.List;
import java.util.function.BiPredicate;
public class KLargestValuesInBst {
  @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")

  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
    // TODO - you fill in here.
    // O(h + k) time; O(h) space
    List<Integer> result = new LinkedList<>();
    findLarger(tree, k, result);
    return result;
  }

  public static void findLarger(BstNode<Integer> tree, int k, List<Integer> result) {
    if (tree == null) return;
    findLarger(tree.right, k, result);
    if (result.size() < k) {
      result.add(tree.data);
    } else {
      return;
    }
    findLarger(tree.left, k, result);
  }

  @EpiTestComparator
  public static boolean comp(List<Integer> expected, List<Integer> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
        .runFromAnnotations(args, "KLargestValuesInBst.java",
          new Object() {}.getClass().getEnclosingClass())
        .ordinal());
  }
}
