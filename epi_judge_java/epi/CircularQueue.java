package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.List;

public class CircularQueue {

  public static class Queue {
    private int p1 = 0, p2 = 0, size = 0;
    private int[] A;

    public Queue(int capacity) {
      if (capacity < 1) throw new RuntimeException("Invalid capacity");
      this.A = new int[capacity];
    }
    public void enqueue(Integer x) {
      // TODO - you fill in here.
      size++;
      if (this.size > A.length) {
        int[] B = new int[A.length * 2];
        int end = 0;
        for (int i = p1; end < A.length; i = (i+1) % A.length) {
          B[end++] = A[i];
        }
        A = B;
        p2 = end;
        p1 = 0;
      }
      A[p2] = x;
      p2 = (p2 + 1) % A.length;
    }
    public Integer dequeue() {
      // TODO - you fill in here.
      if (this.size < 1) throw new RuntimeException("Item not found");
      this.size--;
      int temp = A[p1];
      p1 = (p1 + 1) % A.length;
      return temp;
    }
    public int size() {
      // TODO - you fill in here.
      return this.size;
    }
    @Override
    public String toString() {
      // TODO - you fill in here.
      StringBuilder sb = new StringBuilder();
      for (int i = this.p1; i != this.p2; i = (i+1)%A.length) {
        sb.append(A[i] + " ");
      }
      return sb.toString();
    }
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }

    @Override
    public String toString() {
      return op;
    }
  }

  @EpiTest(testDataFile = "circular_queue.tsv")
  public static void queueTester(List<QueueOp> ops) throws TestFailure {
    Queue q = new Queue(1);
    int opIdx = 0;
    for (QueueOp op : ops) {
      switch (op.op) {
      case "Queue":
        q = new Queue(op.arg);
        break;
      case "enqueue":
        q.enqueue(op.arg);
        break;
      case "dequeue":
        int result = q.dequeue();
        if (result != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, result);
        }
        break;
      case "size":
        int s = q.size();
        if (s != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, s);
        }
        break;
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CircularQueue.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
