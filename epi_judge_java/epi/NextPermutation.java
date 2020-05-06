package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class NextPermutation {
  @EpiTest(testDataFile = "next_permutation.tsv")
  public static List<Integer> nextPermutation(List<Integer> perm) {
    // TODO - you fill in here.
    if (perm.size() < 2) return new ArrayList<>();
    int i = perm.size() - 2;
    while (i >= 0) {
      if (perm.get(i) < perm.get(i+1)) break;
      i--;
    }

    if (i < 0) return new ArrayList<>();

    int s = perm.size() - 1;
    while (s > i) {
      if (perm.get(s) > perm.get(i)) break;
      s--;
    }

    Collections.swap(perm, i, s);
    reverse(perm, i+1, perm.size() - 1);
    return perm;
  }

  private static void reverse(List<Integer> A, int s, int e) {
    while (s < e) {
      Collections.swap(A, s, e);
      s++;
      e--;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NextPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
