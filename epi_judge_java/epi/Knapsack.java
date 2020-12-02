package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.*;
public class Knapsack {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class Item {
    public Integer weight;
    public Integer value;

    public Item(Integer weight, Integer value) {
      this.weight = weight;
      this.value = value;
    }
  }

  @EpiTest(testDataFile = "knapsack.tsv")

  public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
    // TODO - you fill in here.
    // O(nc); O(nc)
    Map<List<Integer>, Integer> map = new HashMap<>();
    return solve(items, map, 0, capacity);
  }

  public static int solve(List<Item> items, Map<List<Integer>, Integer> map, int i, int cap) {
    if (cap < 0) return 0;
    if (i >= items.size()) return 0;
    List<Integer> key = Arrays.asList(i, cap);
    if (map.containsKey(key)) return map.get(key);
    int noTakePrice = solve(items, map, i+1, cap);
    int takePrice = 0;
    if (cap >= items.get(i).weight) {
      takePrice = items.get(i).value + solve(items, map, i + 1, cap - items.get(i).weight);
    }
    int val = Math.max(takePrice, noTakePrice);
    map.put(key, val);
    return val;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Knapsack.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
