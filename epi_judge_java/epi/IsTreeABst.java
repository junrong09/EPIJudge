package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
public class IsTreeABst {

  public static class Entry {
      BinaryTreeNode<Integer> tree;
      int min = Integer.MIN_VALUE;
      int max = Integer.MAX_VALUE;

      public Entry(BinaryTreeNode<Integer> tree, int min, int max) {
        this.tree = tree;
        this.min = min;
        this.max = max;
      }
    }

  @EpiTest(testDataFile = "is_tree_a_bst.tsv")
  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    Queue<Entry> q = new LinkedList<>();
    q.add(new Entry(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));
    
    while (!q.isEmpty()) {
      Entry e = q.poll();
      if (e.tree != null) {
        if (e.tree.data >= e.min && e.tree.data <= e.max) {
          q.add(new Entry(e.tree.left, e.min, e.tree.data));
          q.add(new Entry(e.tree.right, e.tree.data, e.max));
        } else {
          return false;
        }
      }
    }
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
