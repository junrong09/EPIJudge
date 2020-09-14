package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Deque;
import java.util.LinkedList;

public class EvaluateRpn {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
    // TODO - you fill in here.
    String[] A = expression.split(",");
    Deque<Integer> dq = new LinkedList<>();
    for (String s : A) {
      switch (s) {
        case "+":
          dq.push(dq.poll() + dq.poll());
          break;
        case "-":
          int i = dq.poll();
          dq.push(dq.poll() - i);
          break;
        case "*":
          dq.push(dq.poll() * dq.poll());
          break;
        case "/":
          int j = dq.poll();
          dq.push(dq.poll() / j);
          break;
        default:
          dq.push(Integer.parseInt(s));
      }
    }
    return dq.poll();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
