import javax.swing.*;
import java.awt.*;

public class ControlPanel  extends JPanel {
    MainFrame frame;
    JButton saveButton, loadButton;

    public ControlPanel(MainFrame frame){
        this.frame = frame;

        setPreferredSize(new Dimension(100, 40));
        setBackground(Color.LIGHT_GRAY);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        saveButton = new JButton("Save");


        loadButton = new JButton("Load");

        add(saveButton);
        add(loadButton);
    }
}