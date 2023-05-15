import javax.swing.*;
import java.awt.*;

public class Grid {
    private static int sideLength;
    private static int numSquares;
    private JPanel panel;
    public Grid() {
        sideLength = 400;
        numSquares = 20;

        createPanel();
        createGrid();

        Canvas.window.add(panel);
    }

    public void createPanel() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(numSquares, numSquares));
        panel.setVisible(true);
    }

    public void createGrid() {
        Algorithms.setGrid(new Cell[numSquares][numSquares]);

        for (int x = 0; x < numSquares; x++) {
            for (int y = 0; y < numSquares; y++) {
                Cell temp = new Cell(x, y);
                temp.setPreferredSize(new Dimension(sideLength / numSquares, sideLength / numSquares));
                Algorithms.getGrid()[x][y] = temp;
                panel.add(Algorithms.getGrid()[x][y]);
            }
        }
    }

    public static boolean isInGrid(int x, int y) {
        return (x > 0 && x < numSquares && y > 0 && y < numSquares);
    }
}
