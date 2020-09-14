package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    return checkBalanced(tree, new int[1]);
  }

  private static boolean checkBalanced(BinaryTreeNode<Integer> tree, int[] h) {
    if (tree == null) {
      h[0] = 0;
      return true;
    }
    int[] lh = new int[1];
    int[] rh = new int[1];
    if (!checkBalanced(tree.left, lh) || !checkBalanced(tree.right, rh) || Math.abs(lh[0] - rh[0]) > 1) {
      return false;
    } else {
      h[0] = Math.max(lh[0], rh[0]) + 1;
      return true;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
