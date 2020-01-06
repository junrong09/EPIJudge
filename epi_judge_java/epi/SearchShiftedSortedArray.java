package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchShiftedSortedArray {
  @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

  public static int searchSmallest(List<Integer> A) {
    // TODO - you fill in here.
    return search(A, 0, A.size() - 1, A.get(0), 0);
  }

  private static int search(List<Integer> A, int s, int e, int p, int min) {
    if (s > e) return min;
    int mid = ((e - s) / 2) + s;
    min = (A.get(mid) < A.get(min)) ? mid : min;
    if (A.get(mid) >= p) {
      return search(A, mid + 1, e, p, min);
    } else {
      return search(A, s, mid - 1, p, min);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
