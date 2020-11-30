package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Map;
import java.util.HashMap;
public class Fibonacci {

  @EpiTest(testDataFile = "fibonacci.tsv")

  public static int fibonacci(int n) {
    // TODO - you fill in here.
    //return fn1(n);
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 0);
    map.put(1, 1);
    return fn2(n, map);
  }

  /**
   * Backtracking with O(n) time, O(1) space.
   */
  public static int fn1(int n) {
    int f1 = 0, f2 = 1;
    for (int i = 0; i < n; i++) {
      int temp = f1;
      f1 = f2;
      f2 = temp + f1;
    }
    return f1;
  }

  /**
   * Dynamic Programming with O(n) time, O(n) space.
   */
  public static int fn2(int n, Map<Integer, Integer> map) {
    if (n < 1) n = 0;
    if (map.containsKey(n)) {
      return map.get(n);
    }
    int value = fn2(n - 1, map) + fn2(n - 2, map);
    map.put(n, value);
    return value;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
        .runFromAnnotations(args, "Fibonacci.java",
          new Object() {}.getClass().getEnclosingClass())
        .ordinal());
  }
}
