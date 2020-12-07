package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.*;
public class IsTreeSymmetric {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    List<BinaryTreeNode<Integer>> toVisit = new ArrayList<>();
    List<BinaryTreeNode<Integer>> next = new ArrayList<>();
    toVisit.add(tree);
    while (!toVisit.isEmpty()) {
      if (!isPalin(toVisit)) {
        return false;
      }
      for (BinaryTreeNode<Integer> n : toVisit) {
        if (n == null) {
          continue;
        }
        next.add(n.left);
        next.add(n.right);
      }
      toVisit = next;
      next = new ArrayList<>();
    }
    return true;
  }

  public static boolean isPalin(List<BinaryTreeNode<Integer>> list) {
    int i = 0, j = list.size() - 1;
    while (i < j) {
      BinaryTreeNode<Integer> r = list.get(i), l = list.get(j);
      if (r == null || l == null) {
        if (r != l) {
          return false;
        }
      } else {
        if (r.data != l.data) {
          return false;
        }
      }
      i++;
      j--;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
