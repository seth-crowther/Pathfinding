import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Canvas {

    public static JFrame window;
    private Grid grid;
    private Menu menu;

    public Canvas() {
        window = new JFrame();
        window.setLayout(new FlowLayout());
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        grid = new Grid();
        menu = new Menu();
        window.pack();
        window.setVisible(true);
    }
}
