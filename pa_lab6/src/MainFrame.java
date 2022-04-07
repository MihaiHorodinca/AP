import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    ConfigPanel configPanel;
    DrawingPanel drawingPanel;
    ControlPanel controlPanel;

    public MainFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("My game");
        setPreferredSize(new Dimension(500, 500));
        setLayout(new BorderLayout(10, 10));

        configPanel = new ConfigPanel(this);
        drawingPanel = new DrawingPanel(this);
        controlPanel = new ControlPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public void createGame(){
        drawingPanel.paintComponents(drawingPanel.graphics);
    }
}
