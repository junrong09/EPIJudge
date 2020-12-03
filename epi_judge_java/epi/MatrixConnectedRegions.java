package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.*;
public class MatrixConnectedRegions {
  public static void flipColor(int x, int y, List<List<Boolean>> image) {
    // TODO - you fill in here.
    // O(V + E); O(V + E)
    flip(x, y, !image.get(x).get(y), image);
    //bfs(x, y, !image.get(x).get(y), image);
  }

  public static void bfs(int x, int y, boolean flipTo, List<List<Boolean>> image) {
    Queue<List<Integer>> queue = new LinkedList<>();
    queue.add(Arrays.asList(x, y));
    int[][] changes = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};
    while (queue.peek() != null) {
      List<Integer> item = queue.poll();
      if (image.get(item.get(0)).get(item.get(1)) == flipTo) {
        continue;
      }
      image.get(item.get(0)).set(item.get(1), flipTo);
      for (int[] c : changes) {
        int newX = item.get(0) + c[0], newY = item.get(1) + c[1];
        if (newX >= 0 && newY >= 0 && newX < image.size() && newY < image.get(0).size()) {
          queue.add(Arrays.asList(newX, newY));
        }
      }
    }
  }

  public static void flip(int x, int y, boolean flipTo, List<List<Boolean>> image) {
    if (x < 0 || y < 0 || x >= image.size() || y >= image.get(0).size() || flipTo == image.get(x).get(y)) {
      return;
    }
    image.get(x).set(y, flipTo);
    int[][] changes = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};
    for (int[] c : changes) {
      flip(x + c[0], y + c[1], flipTo, image);
    }
  }

  @EpiTest(testDataFile = "painting.tsv")
  public static List<List<Integer>> flipColorWrapper(TimedExecutor executor,
                                                     int x, int y,
                                                     List<List<Integer>> image)
      throws Exception {
    List<List<Boolean>> B = new ArrayList<>();
    for (int i = 0; i < image.size(); i++) {
      B.add(new ArrayList<>());
      for (int j = 0; j < image.get(i).size(); j++) {
        B.get(i).add(image.get(i).get(j) == 1);
      }
    }

    executor.run(() -> flipColor(x, y, B));

    image = new ArrayList<>();
    for (int i = 0; i < B.size(); i++) {
      image.add(new ArrayList<>());
      for (int j = 0; j < B.get(i).size(); j++) {
        image.get(i).add(B.get(i).get(j) ? 1 : 0);
      }
    }

    return image;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixConnectedRegions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
