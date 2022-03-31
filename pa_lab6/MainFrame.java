package com.mycompany.pa_lab6;

/**
 *
 * @author Mihai
 */
import javax.swing.*;
import java.awt.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    Canvas canvas;

    public MainFrame() {
        super("Drawing app");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        canvas = new Canvas(this);

        add(configPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);

        pack();
    }

    public void paintComponent() {
        canvas.paintComponent(canvas.graphics);
    }
}