package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Objects;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class IsTreeSymmetric {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    if (tree == null) return true;
    return isSym(tree.left, tree.right);
  }

  private static boolean isSym(BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right) {
    if (left == null || right == null) {
      return left == right;
    }

    return (left.data == right.data && isSym(left.left, right.right) && isSym(left.right, right.left));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
