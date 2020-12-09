package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.*;
public class IsTreeABst {
  @EpiTest(testDataFile = "is_tree_a_bst.tsv")

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    return check(Integer.MIN_VALUE, Integer.MAX_VALUE, tree);
  }

  public static boolean check(int lower, int upper, BinaryTreeNode<Integer> tree) {
    if (tree == null) return true;
    if (tree.data < lower || tree.data > upper) return false;
    return check(lower, tree.data, tree.left) && check(tree.data, upper, tree.right);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
        .runFromAnnotations(args, "IsTreeABst.java",
          new Object() {}.getClass().getEnclosingClass())
        .ordinal());
  }
}
