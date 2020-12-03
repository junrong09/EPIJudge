package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.*;
public class SearchMaze {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Coordinate {
    public int x, y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }

      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Coordinate that = (Coordinate)o;
      if (x != that.x || y != that.y) {
        return false;
      }
      return true;
    }

    @Override
    public String toString() {
      return String.format("[%d, %d]", x, y);
    }
  }

  public enum Color { WHITE, BLACK }

  public static List<Coordinate> searchMaze(List<List<Color>> maze,
                                            Coordinate s, Coordinate e) {
    // TODO - you fill in here.
    // O(V + E); O(V)
    List<Coordinate> path = new LinkedList<>();
    traverse(maze, s, e, path);
    System.out.println(path.toString());
    return path;
  }

  public static boolean traverse(List<List<Color>> maze, Coordinate s, Coordinate e, List<Coordinate> path) {
    if (s.x < 0 || s.y < 0 || s.y >= maze.get(0).size() || s.x >= maze.size() || maze.get(s.x).get(s.y) == Color.BLACK) {
      return false;
    }
    maze.get(s.x).set(s.y, Color.BLACK);
    if (s.equals(e)) {
      path.add(0, e);
      return true;
    }

    int[][] changes = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    for (int[] c : changes) {
      boolean succeed = traverse(maze, new Coordinate(s.x + c[0], s.y + c[1]), e, path);
      if (succeed) {
        path.add(0, s);
        return true;
      }
    }
   return false;
  }

  public static boolean pathElementIsFeasible(List<List<Integer>> maze,
                                              Coordinate prev, Coordinate cur) {
    if (!(0 <= cur.x && cur.x < maze.size() && 0 <= cur.y &&
          cur.y < maze.get(cur.x).size() && maze.get(cur.x).get(cur.y) == 0)) {
      return false;
    }
    return cur.x == prev.x + 1 && cur.y == prev.y ||
        cur.x == prev.x - 1 && cur.y == prev.y ||
        cur.x == prev.x && cur.y == prev.y + 1 ||
        cur.x == prev.x && cur.y == prev.y - 1;
  }

  @EpiTest(testDataFile = "search_maze.tsv")
  public static boolean searchMazeWrapper(List<List<Integer>> maze,
                                          Coordinate s, Coordinate e)
      throws TestFailure {
    List<List<Color>> colored = new ArrayList<>();
    for (List<Integer> col : maze) {
      List<Color> tmp = new ArrayList<>();
      for (Integer i : col) {
        tmp.add(i == 0 ? Color.WHITE : Color.BLACK);
      }
      colored.add(tmp);
    }
    List<Coordinate> path = searchMaze(colored, s, e);
    if (path.isEmpty()) {
      return s.equals(e);
    }

    if (!path.get(0).equals(s) || !path.get(path.size() - 1).equals(e)) {
      throw new TestFailure("Path doesn't lay between start and end points");
    }

    for (int i = 1; i < path.size(); i++) {
      if (!pathElementIsFeasible(maze, path.get(i - 1), path.get(i))) {
        throw new TestFailure("Path contains invalid segments");
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchMaze.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
