package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.List;
public class ReplaceAndRemove {

  public static int replaceAndRemove(int size, char[] s) {
    // TODO - you fill in here.
    // Remove 'b' and shift
    int count = 0;
    for (int i = 0; i < size; i++) {
      if (s[i] == 'b') {
        count++;
      } else {
        s[i-count] = s[i];
      }
    }
    size -= count;
    // Shift and provide space for 'a'
    int finalSize = 0;
    for (int i = 0; i < size; i++) {
      if (s[i] == 'a') finalSize += 1;
      finalSize += 1;
    }

    int shift = finalSize - size;
    for (int i = size - 1; i >= 0; i--) {
      if (s[i] == 'a') {
        shift--;
        s[i+shift] = s[i];
        s[i+shift] = 'd';
        s[i+shift+1] = 'd';
      } else {
        s[i+shift] = s[i];
      }
    }
    // Replace 'a' with 'd'
    return finalSize; 
  }
  

  
  @EpiTest(testDataFile = "replace_and_remove.tsv")
  public static List<String>
  replaceAndRemoveWrapper(TimedExecutor executor, Integer size, List<String> s)
      throws Exception {
    char[] sCopy = new char[s.size()];
    for (int i = 0; i < size; ++i) {
      if (!s.get(i).isEmpty()) {
        sCopy[i] = s.get(i).charAt(0);
      }
    }

    Integer resSize = executor.run(() -> replaceAndRemove(size, sCopy));

    List<String> result = new ArrayList<>();
    for (int i = 0; i < resSize; ++i) {
      result.add(Character.toString(sCopy[i]));
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReplaceAndRemove.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
