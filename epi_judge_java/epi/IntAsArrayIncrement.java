package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    // TODO - you fill in here.
    A.set(A.size() - 1, (A.get(A.size() - 1) + 1) % 10);
    for (int i = A.size() - 1; i >= 0; i--) {
      if (A.get(i) != 0) return A;
      if (i == 0) {
        A.add(0, 1);
      } else {
        A.set(i - 1, (A.get(i-1) + 1) % 10);
      }
    }
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
