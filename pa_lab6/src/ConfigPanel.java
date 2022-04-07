import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    MainFrame frame;
    JLabel label;
    JSpinner rowSpinner, colSpinner;
    JButton createButton;
    
    int row, col;


    public ConfigPanel (MainFrame frame){
        this.frame = frame;

        setPreferredSize(new Dimension(100, 40));
        setBackground(Color.LIGHT_GRAY);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        label = new JLabel("Grid size: ");
        
        rowSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 10, 1));
        rowSpinner.addChangeListener(e -> {
            row = (int) rowSpinner.getValue();
        });

        colSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 10, 1));
        colSpinner.addChangeListener(e -> {
            col = (int) colSpinner.getValue();
        });

        createButton = new JButton("Create");
        createButton.addActionListener(this::createGame);

        add(label);
        add(rowSpinner);
        add(colSpinner);
        add(createButton);
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    private void createGame(ActionEvent e){
        frame.createGame();
    }
}
