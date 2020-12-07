package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
public class SortedArraysMerge {
  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")

  public static List<Integer>
  mergeSortedArrays(List<List<Integer>> sortedArrays) {
    // TODO - you fill in here.
    // O(n lg k); O(k)
    Queue<List<Integer>> queue = new PriorityQueue<>((x,y) -> x.get(0)-y.get(0));
    List<Integer> result = new ArrayList<>();
    int[] indexes = new int[sortedArrays.size()];
    for (int i = 0; i < indexes.length; i++) {
      queue.add(Arrays.asList(sortedArrays.get(i).get(indexes[i]++), i));
    }
    while (!queue.isEmpty()) {
      List<Integer> r = queue.poll();
      result.add(r.get(0));
      int listNum = r.get(1);
      if (indexes[listNum] < sortedArrays.get(listNum).size()) {
        queue.add(Arrays.asList(sortedArrays.get(listNum).get(indexes[listNum]++), listNum));
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
