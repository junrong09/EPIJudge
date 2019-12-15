package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Arrays;
import java.util.List;
public class IsTreeABst {
  @EpiTest(testDataFile = "is_tree_a_bst.tsv")

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    return isBST(tree, Arrays.asList(0,0));
  }

  private static boolean isBST(BinaryTreeNode<Integer> tree, List<Integer> stats) {
    if (tree == null) {
      stats.set(0, null);
      stats.set(1, null);
      return true;
    }

    List<Integer> s1 = Arrays.asList(0, 0), s2 = Arrays.asList(0,0);
    if (!isBST(tree.left, s1) || !isBST(tree.right, s2)) {
      return false;
    }

    if ((s1.get(0) != null && tree.data < s1.get(1)) || (s2.get(0) != null && tree.data > s2.get(0))) {
      return false;
    }

    stats.set(0, s1.get(0) == null ? tree.data : s1.get(0));
    stats.set(1, s2.get(1) == null ? tree.data : s2.get(1));
    return true;
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
