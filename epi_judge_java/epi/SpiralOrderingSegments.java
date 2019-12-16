package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.ArrayList;
public class SpiralOrderingSegments {
  @EpiTest(testDataFile = "spiral_ordering_segments.tsv")

  public static List<Integer>
  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    // TODO - you fill in here.
    int n = (squareMatrix.size() + 1) / 2, r = 0, c = 0;
    List<Integer> result = new ArrayList<>();

    while (n > 0) {
      travel(squareMatrix, r, c, squareMatrix.size() - r * 2, result);
      r++;
      c++;
      n--;
    }

    return result;
  }

  private static void travel(List<List<Integer>> matrix, int r, int c, int size, List<Integer> result) {
    if (size == 1) {
      result.add(matrix.get(r).get(c));
      return;
    }

    int[][] shift = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int[][] end = {{r, c + size - 1}, {r + size - 1, c + size - 1}, {r + size - 1, c}, {r,c}};
    for (int s = 0; s < shift.length; s++) {
      int[] sh = shift[s];
      while (r != end[s][0] || c != end[s][1]) {
        result.add(matrix.get(r).get(c));
        r += sh[0];
        c += sh[1];
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrderingSegments.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
