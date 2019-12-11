package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;
public class SortedArraysMerge {
  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")

  public static List<Integer>
  mergeSortedArrays(List<List<Integer>> sortedArrays) {
    // TODO - you fill in here.
    List<Integer> result = new ArrayList<>();
    PriorityQueue<List<Integer>> pq = new PriorityQueue<>((x,y) -> x.get(0) - y.get(0));
    for (int i = 0; i < sortedArrays.size(); i++) {
      pq.add(Arrays.asList(sortedArrays.get(i).get(0), i));
    }
    int[] indexes = new int[sortedArrays.size()];
    Arrays.fill(indexes, 1);
    while (pq.size() > 0) {
      List<Integer> tuple = pq.poll();
      result.add(tuple.get(0));
      if (indexes[tuple.get(1)] < sortedArrays.get(tuple.get(1)).size()) {
        pq.add(Arrays.asList(sortedArrays.get(tuple.get(1)).get(indexes[tuple.get(1)]++), tuple.get(1)));
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
