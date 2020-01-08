package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Iterator;
import java.util.List;
public class MajorityElement {

  public static String majoritySearch(Iterator<String> stream) {
    // TODO - you fill in here.
    String s = stream.next();
    int count = 1;
    while (stream.hasNext()) {
      String n = stream.next();
      if (s.equals(n)) {
        count++;
      } else {
        count--;
      }

      if (count < 0) {
        s = n;
        count = 1;
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
