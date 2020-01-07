package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Arrays;
public class CalendarRendering {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Event {
    public int start, finish;

    public Event(int start, int finish) {
      this.start = start;
      this.finish = finish;
    }
  }

  private static class Endpoint {
    public int time;
    public boolean isStart;

    Endpoint(int time, boolean isStart) {
      this.time = time;
      this.isStart = isStart;
    }
  }

  @EpiTest(testDataFile = "calendar_rendering.tsv")

  public static int findMaxSimultaneousEvents(List<Event> A) {
    // TODO - you fill in here.
    PriorityQueue<List<Integer>> pq = new PriorityQueue<>((x,y) -> {
      int timeDiff = x.get(1) - y.get(1);
      if (timeDiff == 0) return x.get(0) - y.get(0);
      return timeDiff;
    });
    for (int i = 0; i < A.size(); i++) {
      pq.add(Arrays.asList(0, A.get(i).start));
      pq.add(Arrays.asList(1, A.get(i).finish));
    }
    int max = 0;
    int concurrent = 0;
    while (!pq.isEmpty()) {
      int type = pq.poll().get(0);
      if (type == 0) concurrent++;
      else concurrent--;
      max = Math.max(max, concurrent);
    }
    return max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CalendarRendering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
