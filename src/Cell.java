import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class Cell extends JButton {
    public static Color paintColor;

    private Cell cell = this;
    private boolean obstacle = false;

    private int xCoord;
    private int yCoord;
    private float dist;
    private Cell prev;

    private final static Color green = new Color(0, 255, 0);
    private final static Color red = new Color(255, 0, 0);
    private final static Color gray = new Color(91, 101, 101);
    private final static Color black = new Color(0, 0, 0);

    public enum CellColor {
        green,
        red,
        gray,
        black
    }

    public static void ParseColor(CellColor c) {
        switch (c) {
            case green -> paintColor = green;
            case red -> paintColor = red;
            case gray -> paintColor = gray;
            default -> paintColor = black;
        }
    }

    public int getX() {
        return xCoord;
    }

    public int getY() {
        return yCoord;
    }

    public float getDist() {
        return dist;
    }

    public void setDist(float value) {
        dist = value;
    }

    public Cell getPrev() {
        return prev;
    }

    public void setPrev(Cell value) {
        prev = value;
    }

    public boolean getObstacle() {
        return obstacle;
    }

    public Cell(int x, int y) {
        this.xCoord = x;
        this.yCoord = y;

        dist = Float.MAX_VALUE;
        prev = null;

        paintColor = gray;
        setBackground(gray);
        setUpListener();
        setVisible(true);
    }

    public void setUpListener() {
        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent me) {
                if (paintColor.equals(green)) {
                    if (Algorithms.getStart() == null) {
                        Algorithms.setStart(cell);
                    }
                    else {
                        Algorithms.getStart().setBackground(gray);
                        Algorithms.setStart(cell);
                    }

                }
                else if (paintColor.equals(red)) {
                    if (Algorithms.getEnd() == null) {
                        Algorithms.setEnd(cell);
                    }
                    else {
                        Algorithms.getEnd().setBackground(gray);
                        Algorithms.setEnd(cell);
                    }

                }
                else if (paintColor.equals(black)) {
                    obstacle = true;
                }
                setBackground(paintColor);
            }
        });
    }

    public float distanceTo(Cell other) {
        float xDiff = other.xCoord - this.xCoord;
        float yDiff = other.yCoord - this.yCoord;

        return (float)(Math.sqrt((xDiff * xDiff) + (yDiff * yDiff)));
    }

    public Set<Cell> getAdjacentCells() {
        Set<Cell> toReturn = new HashSet<Cell>();
        for (int i = xCoord - 1; i <= xCoord + 1; i++) {
            for (int j = yCoord - 1; j <= yCoord + 1; j++) {
                Cell c = (Grid.isInGrid(i, j)) ? Algorithms.getGrid()[i][j] : null;
                if (c != null && !c.getObstacle()) {
                    toReturn.add(c);
                }
            }
        }
        return toReturn;
    }

    public Cell getClosestCell() {
        Cell toReturn = null;
        float dist = Float.MAX_VALUE;

        Set<Cell> adj = getAdjacentCells();
        for (Cell c: adj) {
            if (c.getDist() < dist) {
                dist = c.getDist();
                toReturn = c;
            }
        }

        return toReturn;
    }
}
