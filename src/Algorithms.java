import java.awt.*;
import java.util.*;
import java.util.List;

public class Algorithms {
    private static Cell start;
    private static Cell end;
    private static Cell[][] grid;
    private static List<Cell> finalPath;

    public static Cell getStart() {
        return start;
    }
    public static void setStart(Cell value) {
        start = value;
        value.setDist(0);
    }
    public static Cell getEnd() {
        return end;
    }
    public static void setEnd(Cell value) {
        end = value;
    }
    public static Cell[][] getGrid() {
        return grid;
    }
    public static void setGrid(Cell[][] value) {
        grid = value;
    }

    public static void main(String[] args) {
        Canvas c = new Canvas();
        finalPath = new ArrayList<>();
    }

    // Basic algorithm done, need to output it somehow
    public static void dijkstra() {
        Set<Cell> unvisited = new HashSet<>();
        Set<Cell> visited = new HashSet<>();

        for (Cell[] row: grid) {
            unvisited.addAll(Arrays.asList(row));
        }

        Cell current = Algorithms.getStart();

        for (Cell c: unvisited) {
            if (visited.contains(c)) {
                continue;
            }

            Set<Cell> adjNodes = current.getAdjacentCells();
            for (Cell adj: adjNodes) {
                float alt = current.getDist() + current.distanceTo(adj);
                if (alt < adj.getDist()) {
                    adj.setDist(alt);
                    adj.setPrev(current);
                }
            }

            if (current == Algorithms.getEnd()) {
                break;
            }

            visited.add(current);
            current = current.getClosestCell();
        }

        getFinalPath(Algorithms.getEnd());
        traceFinalPath();
    }

    public static void getFinalPath(Cell current) {
        if (current.getPrev() == null) {
            finalPath.add(current);
            return;
        }
        else {
            finalPath.add(current);
            getFinalPath(current.getPrev());
        }
    }

    public static void traceFinalPath() {
        for (Cell c: finalPath) {
            c.setBackground(new Color(0, 0, 255));
        }
    }
}
