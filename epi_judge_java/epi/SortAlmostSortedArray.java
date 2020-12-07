package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.List;
public class SortAlmostSortedArray {

  public static List<Integer>
  sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
    // TODO - you fill in here.
    // O(n lg k); O(k)
    Queue<Integer> q = new PriorityQueue<>();
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i <= k; i++) {
      if (sequence.hasNext()) {
        q.add(sequence.next());
      }
    }
    while (!q.isEmpty()) {
      result.add(q.poll());
      if (sequence.hasNext()) q.add(sequence.next());
    }
    return result;
  }

  @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
  public static List<Integer>
  sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
    return sortApproximatelySortedData(sequence.iterator(), k);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortAlmostSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
