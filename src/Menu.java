import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    JPanel panel;

    public Menu() {
        createPanel();
        Canvas.window.add(panel);
    }

    public void createPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        setUpCheckBoxes();
        setUpSimulateButton();

        panel.setVisible(true);
    }

    public void setUpCheckBoxes() {
        JCheckBox start = new JCheckBox();
        JCheckBox end = new JCheckBox();
        JCheckBox obstacle = new JCheckBox();

        start.setText("Start");
        end.setText("End");
        obstacle.setText("Obstacle");

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!obstacle.isSelected() && !start.isSelected() && !end.isSelected()) {
                    Cell.ParseColor(Cell.CellColor.gray);
                    return;
                }

                if (start.isSelected()) {
                    end.setSelected(false);
                    obstacle.setSelected(false);

                    Cell.ParseColor(Cell.CellColor.green);
                }
            }
        });

        end.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!obstacle.isSelected() && !start.isSelected() && !end.isSelected()) {
                    Cell.ParseColor(Cell.CellColor.gray);
                    return;
                }

                if (end.isSelected()) {
                    start.setSelected(false);
                    obstacle.setSelected(false);

                    Cell.ParseColor(Cell.CellColor.red);
                }
            }
        });

        obstacle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!obstacle.isSelected() && !start.isSelected() && !end.isSelected()) {
                    Cell.ParseColor(Cell.CellColor.gray);
                    return;
                }

                if (obstacle.isSelected()) {
                    start.setSelected(false);
                    end.setSelected(false);

                    Cell.ParseColor(Cell.CellColor.black);
                }
            }
        });

        panel.add(start);
        panel.add(end);
        panel.add(obstacle);
    }

    public void setUpSimulateButton() {
        JButton simulate = new JButton();
        simulate.setText("Simulate");

        simulate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Algorithms.getStart() != null && Algorithms.getEnd() != null) {
                    Algorithms.dijkstra();
                }
                else {
                    JOptionPane.showMessageDialog(Canvas.window, "A start and end node are needed for the simulation.");
                }
            }
        });

        panel.add(simulate);
        simulate.setVisible(true);
    }
}
