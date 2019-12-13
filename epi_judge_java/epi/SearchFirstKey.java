package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")

  public static int searchFirstOfK(List<Integer> A, int k) {
    // TODO - you fill in here.
    int s = 0, e = A.size() - 1, found = -1;
    while (s <= e) {
      int mid = s + (e - s) / 2;
      if (A.get(mid) == k) {
        found = mid;
        e = mid - 1;
      } else if (A.get(mid) < k) {
        s = mid + 1;
      } else {
        e = mid - 1;
      }
    }
    return found;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
