package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    return cal(tree, new int[1]);
  }

  public static boolean cal(BinaryTreeNode<Integer> tree, int[] A) {
    if (tree == null) {
      A[0] = 0;
      return true;
    }
    int[] A1 = new int[1];
    int[] A2 = new int[1];
    boolean result = cal(tree.left, A1) && cal(tree.right, A2);
    A[0] = Math.max(A1[0], A2[0]) + 1;
    if (Math.abs(A1[0] - A2[0]) > 1) result = false;
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
