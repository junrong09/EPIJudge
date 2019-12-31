package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class IsValidSudoku {
  @EpiTest(testDataFile = "is_valid_sudoku.tsv")

  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
    // TODO - you fill in here.
    for (int i = 0; i < 9; i++) {
      if (!isValidRow(partialAssignment, i) || !isValidCol(partialAssignment, i))
        return false;
    }

    for (int i = 0; i < 9; i += 3) {
      for (int j = 0; j < 9; j+= 3) {
        if (!isValidGrid(partialAssignment, i, j)) return false;
      }
    }
    return true;
  }

  private static boolean isValidRow(List<List<Integer>> A, int i) {
    Set<Integer> set = new HashSet<>();
    for (int v : A.get(i)) {
      if (set.contains(v)) {
        return false;
      } else {
        if (v != 0) set.add(v);
      }
    }
    return true;
  }

  private static boolean isValidCol(List<List<Integer>> A, int i) {
    Set<Integer> set = new HashSet<>();
    for (int j = 0; j < 9; j++) {
      int v = A.get(j).get(i);
      if (set.contains(v)) {
        return false;
      } else {
        if (v != 0) set.add(v);
      }
    }
    return true;
  }

  private static boolean isValidGrid(List<List<Integer>> A, int i, int j) {
    Set<Integer> set = new HashSet<>();
    for (int a = 0; a < 3; a++) {
      for (int b = 0; b < 3; b++) {
        int v = A.get(i+a).get(j+b);
        if (set.contains(v)) {
          return false;
        } else {
          if (v != 0) set.add(v);
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
