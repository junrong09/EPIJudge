package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

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
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  public enum Color { WHITE, BLACK }

  public static List<Coordinate> searchMaze(List<List<Color>> maze,
                                            Coordinate s, Coordinate e) {
    // TODO - you fill in here.
    List<Coordinate> paths = new LinkedList<>();
    Set<Coordinate> visited = new HashSet<>();
    isPath(maze, s, e, paths, visited);
    return paths;
  }

  private static boolean isPath(List<List<Color>> maze, Coordinate s, Coordinate e, List<Coordinate> paths, Set<Coordinate> visited) {
    visited.add(new Coordinate(s.x, s.y));
    if (s.equals(e)) {
      paths.add(0, e);
      return true;
    }

    for (Coordinate c : getMovableWhite(maze, s, visited)) {
      if (isPath(maze, c, e, paths, visited)) {
        paths.add(0, s);
        return true;
      }
    }
    return false;
  }

  private static List<Coordinate> getMovableWhite(List<List<Color>> maze, Coordinate s, Set<Coordinate> visited) {
    List<Coordinate> movable = new ArrayList<>();
    int maxX = maze.size();
    int maxY = maze.get(0).size();
    if (s.x + 1 < maxX && !visited.contains(new Coordinate(s.x + 1, s.y)) && maze.get(s.x+1).get(s.y) == Color.WHITE) {
      movable.add(new Coordinate(s.x+1, s.y));
    }

    if (s.x - 1 >= 0 && !visited.contains(new Coordinate(s.x - 1, s.y)) && maze.get(s.x-1).get(s.y) == Color.WHITE) {
      movable.add(new Coordinate(s.x-1, s.y));
    }

    if (s.y + 1 < maxY && !visited.contains(new Coordinate(s.x, s.y+1)) && maze.get(s.x).get(s.y+1) == Color.WHITE) {
      movable.add(new Coordinate(s.x, s.y+1));
    }

    if (s.y - 1 >= 0 && !visited.contains(new Coordinate(s.x, s.y-1)) && maze.get(s.x).get(s.y-1) == Color.WHITE) {
      movable.add(new Coordinate(s.x, s.y-1));
    }
    return movable;
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
