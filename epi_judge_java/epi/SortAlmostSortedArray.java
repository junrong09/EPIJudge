package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class SortAlmostSortedArray {

  public static List<Integer>
  sortApproximatelySortedData(Iterator<Integer> seq, int k) {
    // TODO - you fill in here.
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int count = k + 1;
    while (count > 0 && seq.hasNext()) {
      pq.add(seq.next());
      count--;
    }

    List<Integer> result = new ArrayList<>();
    while (!pq.isEmpty()) {
      result.add(pq.poll());
      if (seq.hasNext()) pq.add(seq.next());
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
