package com.mycompany.pa_lab6;

/**
 *
 * @author Mihai
 */
import javax.swing.*;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton load, save;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init(){
        load=new JButton("Load");
        save=new JButton("Save");

        add(load);
        add(save);
    }
}
