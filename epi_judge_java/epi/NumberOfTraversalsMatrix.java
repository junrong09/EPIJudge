package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class NumberOfTraversalsMatrix {
  @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")

  public static int numberOfWays(int n, int m) {
    // TODO - you fill in here.
    // O(n^2); O(n^2)
    if (n == 1 && m == 1) return 1;
    int[][] counts = new int[n][m];
    for (int j = 1; j < m; j++) {
      counts[0][j] = 1;
    }
    for (int i = 1; i < n; i++) {
      counts[i][0] = 1;
    }
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        counts[i][j] = counts[i-1][j] + counts[i][j-1];
      }
    }
    return counts[n-1][m-1];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
        .runFromAnnotations(args, "NumberOfTraversalsMatrix.java",
          new Object() {}.getClass().getEnclosingClass())
        .ordinal());
  }
}
