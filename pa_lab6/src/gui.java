import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MyComponent extends JPanel {
    private int x, y, radius;
    public MyComponent() {
        init();
    }
    private void init() {
        setPreferredSize(new Dimension(400, 400));

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                x = e.getX(); y = e.getY();
                radius = 50 + (int) (100 * Math.random());
                repaint();
            }


            public void mouseReleased(MouseEvent e) {
                x = e.getX(); y = e.getY();
                radius = 5;
                repaint();
            }
        });}

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawOval(x - radius / 2, y - radius / 2, radius, radius);
        }
    }


public class gui {

    private JFrame frame;
    private JPanel configPanel;
    private JPanel drawingPanel;
    private JPanel controlPanel;

    public gui(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("My game");
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setLayout(new BorderLayout(10, 10));

        configPanel = new JPanel();
        drawingPanel = new JPanel();
        controlPanel = new JPanel();

        configPanel.setBackground(Color.BLACK);
        drawingPanel.setBackground(Color.YELLOW);
        controlPanel.setBackground(Color.GREEN);

        configPanel.setPreferredSize(new Dimension(100, 100));
        drawingPanel.setPreferredSize(new Dimension(100, 100));
        controlPanel.setPreferredSize(new Dimension(100, 100));


        frame.add(configPanel, BorderLayout.NORTH);
        frame.add(drawingPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new MainFrame();
    }
}
