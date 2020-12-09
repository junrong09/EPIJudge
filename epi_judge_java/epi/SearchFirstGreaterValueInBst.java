package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SearchFirstGreaterValueInBst {

  public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree,
      Integer k) {
    // TODO - you fill in here.
    BstNode<Integer> ptr = tree;
    BstNode<Integer> found = new BstNode<Integer>(Integer.MAX_VALUE);
    while (ptr != null) {
      if (k < ptr.data) {
        if (ptr.data < found.data) found = ptr;
        ptr = ptr.left;
      } else {
        ptr = ptr.right;
      }
    }
    return found.data == Integer.MAX_VALUE ? null : found;
  }

  @EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
  public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree,
      Integer k) {
    BstNode<Integer> result = findFirstGreaterThanK(tree, k);
    return result != null ? result.data : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
        .runFromAnnotations(args, "SearchFirstGreaterValueInBst.java",
          new Object() {}.getClass().getEnclosingClass())
        .ordinal());
  }
}
