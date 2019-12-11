package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.NoSuchElementException;
public class StackWithMax {

  public static class Stack {
    List<List<Integer>> maxStack = new LinkedList<>();
    List<Integer> stack = new LinkedList<>();
    public boolean empty() {
      return stack.isEmpty();
    }
    public Integer max() {
      if (empty()) throw new IllegalStateException();
      return maxStack.get(maxStack.size() - 1).get(0);
    }
    public Integer pop() {
      if (empty()) throw new IllegalStateException();
      Integer v = stack.remove(stack.size() - 1);
      List<Integer> max = maxStack.get(maxStack.size() - 1);
      if (v == max.get(0)) {
	      if (max.get(1) <= 1) {
		      maxStack.remove(maxStack.size() - 1);
	      } else {
		      max.set(1, max.get(1) - 1);
	      }
      }
      return v;
    }
    public void push(Integer x) {
      // TODO - you fill in here.
      stack.add(x);
      if (maxStack.isEmpty()) {
	      maxStack.add(Arrays.asList(x, 1));
	      return;
      }
      List<Integer> max = maxStack.get(maxStack.size() - 1);
      if (max.get(0) == x) {
	      max.set(1, max.get(1));
      } else if (max.get(0) < x) {
	      maxStack.add(Arrays.asList(x ,1));
      }
    }
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class StackOp {
    public String op;
    public int arg;

    public StackOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testDataFile = "stack_with_max.tsv")
  public static void stackTest(List<StackOp> ops) throws TestFailure {
    try {
      Stack s = new Stack();
      int result;
      for (StackOp op : ops) {
        switch (op.op) {
        case "Stack":
          s = new Stack();
          break;
        case "push":
          s.push(op.arg);
          break;
        case "pop":
          result = s.pop();
          if (result != op.arg) {
            throw new TestFailure("Pop: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "max":
          result = s.max();
          if (result != op.arg) {
            throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "empty":
          result = s.empty() ? 1 : 0;
          if (result != op.arg) {
            throw new TestFailure("Empty: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(s));
          }
          break;
        default:
          throw new RuntimeException("Unsupported stack operation: " + op.op);
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StackWithMax.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
