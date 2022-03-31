package com.mycompany.pa_lab6;

/**
 *
 * @author Mihai
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner rowsInput, colsInput;
    JButton create;

    int cols, rows;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        label = new JLabel("Grid Size:");
        rowsInput = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        rowsInput.addChangeListener(e -> {
            rows = (int) rowsInput.getValue();
        });

        colsInput = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        colsInput.addChangeListener(e -> {
            cols = (int) colsInput.getValue();
        });

        create = new JButton("Create");
        create.addActionListener(this::createCanvas);


        add(rowsInput);
        add(colsInput);
        add(create);

    }

    private void createCanvas(ActionEvent e) {
        frame.paintComponent();
    }

    public int getRows() {
        return (int) rowsInput.getValue();
    }

    public int getCols() {
        return (int) colsInput.getValue();
    }
}
