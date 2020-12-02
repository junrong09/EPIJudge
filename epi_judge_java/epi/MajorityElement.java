package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Iterator;
import java.util.List;
public class MajorityElement {

  public static String majoritySearch(Iterator<String> stream) {
    // TODO - you fill in here.
    // O(n); O(1)
    String s = "";
    int count = 0;
    while (stream.hasNext()) {
      String temp = stream.next();
      if (temp.equals(s)) {
        count++;
      } else {
        count--;
        if (count < 1) {
          s = temp;
          count = 1;
        }
      }
    }
    return s;
  }
  
  @EpiTest(testDataFile = "majority_element.tsv")
  public static String majoritySearchWrapper(List<String> stream) {
    return majoritySearch(stream.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MajorityElement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
