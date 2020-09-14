package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Map;
import java.util.HashMap;

public class ConvertBase {
  @EpiTest(testDataFile = "convert_base.tsv")

  public static String convertBase(String numAsString, int b1, int b2) {
    // TODO - you fill in here.
    // Set mapping
    Map<Character, Integer> map1 = new HashMap<>();
    for (int i = 0; i < 10; i++) {
      map1.put((char) ('0' + i), i);
    }
    map1.put('A', 10);
    map1.put('B', 11);
    map1.put('C', 12);
    map1.put('D', 13);
    map1.put('E', 14);
    map1.put('F', 15);
    Map<Integer, Character> map2 = new HashMap<>();
    for (Map.Entry<Character, Integer> e : map1.entrySet()) {
      map2.put(e.getValue(), e.getKey());
    }

    // Convert to base 10
    boolean neg = numAsString.charAt(0) == '-';
    char[] cc = numAsString.toCharArray();
    long acc = 0;
    long bb1 = 1;
    for (int i = cc.length - 1; i >= 0; i--) {
      if (neg && i == 0) continue;
      acc += map1.get(cc[i]) * bb1;
      bb1 *= b1;
    }
    // Convert to base b2
    StringBuilder sb = new StringBuilder();
    while (acc > 0) {
      sb.append(map2.get((int) (acc % b2)));
      acc /= b2;
    }
    if (sb.length() == 0) sb.append('0');
    if (neg) sb.append('-');
    return sb.reverse().toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
