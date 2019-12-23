package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>>
  binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    List<List<Integer>> result = new ArrayList<>();
    Queue<BinaryTreeNode<Integer>> levelList = new LinkedList<>();
    if (tree != null) levelList.add(tree);
    while (!levelList.isEmpty()) {
      Queue<BinaryTreeNode<Integer>> nextList = new LinkedList<>();
      List<Integer> levelIntegers = new ArrayList<>();
      while (!levelList.isEmpty()) {
        BinaryTreeNode<Integer> node = levelList.poll();
        levelIntegers.add(node.data);
        if (node.left != null) nextList.add(node.left);
        if (node.right != null) nextList.add(node.right);
      }
      levelList = nextList;
      result.add(levelIntegers);
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
