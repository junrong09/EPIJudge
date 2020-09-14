package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    // TODO - you fill in here.
    ListNode<Integer> dummy = new ListNode<Integer>(0, L), p1 = dummy, p2 = dummy;
    while (k-- > 0) {
      p2 = p2.next;
    }

    while (p2.next != null) {
      p2 = p2.next;
      p1 = p1.next;
    }
    p1.next = p1.next.next;
    return dummy.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
