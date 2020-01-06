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
    List<BinaryTreeNode<Integer>> list = new ArrayList<>();
    list.add(tree);
    while (!list.isEmpty()) {
      List<BinaryTreeNode<Integer>> nextList = new ArrayList<>();
      if (!isSym(list)) {
        return false;
      }
      for (BinaryTreeNode<Integer> node : list) {
        if (node == null) continue;
        nextList.add(node.left);
        nextList.add(node.right);
      }
      list = nextList;
    }
    return true;
  }

  private static boolean isSym(List<BinaryTreeNode<Integer>> list) {
    int s = 0, e = list.size() - 1;
    while (s < e) {
      if (list.get(s) != null && list.get(e) != null) {
        if (list.get(s).data != list.get(e).data) return false;
      } else {
        if (list.get(s) != null || list.get(e) != null) return false;
      }
      s++;
      e--;
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
