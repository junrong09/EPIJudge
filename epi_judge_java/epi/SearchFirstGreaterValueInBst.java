package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SearchFirstGreaterValueInBst {

  public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree,
                                                       Integer k) {
    // TODO - you fill in here.
    BstNode<Integer> max = new BstNode(Integer.MAX_VALUE);
    BstNode<Integer> poss = max;
    while (tree != null) {
      if (tree.data > k) {
        if (tree.data <= poss.data) poss = tree; // No need to check because of the way the tree is being traversed, this is always true.
        tree = tree.left;
      } else {
        tree = tree.right;
      }
    }
    return poss == max ? new BstNode(-1) : poss;
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
