package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;
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
    PriorityQueue<List<Integer>> pq = new PriorityQueue<>((x,y) -> x.get(1) - y.get(1));
    for (int i = 0; i < A.size(); i++) {
      pq.add(Arrays.asList(i, A.get(i).start));
      pq.add(Arrays.asList(i, A.get(i).finish));
    }
    Set<Integer> current = new HashSet<>();
    int max = 0;
    while (!pq.isEmpty()) {
      List<Integer> event = pq.poll();
      Set<Integer> remove = new HashSet<>();
      while (!pq.isEmpty() && pq.peek().get(1) == event.get(1)) {
        int eventId = pq.poll().get(0);
        if (current.contains(eventId)) {
          remove.add(eventId);
        } else {
          current.add(eventId);
        }
      }

      if (current.contains(event.get(0))) {
        remove.add(event.get(0));
      } else {
        current.add(event.get(0));
      }

      max = Math.max(max, current.size());
      current.removeAll(remove);
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
