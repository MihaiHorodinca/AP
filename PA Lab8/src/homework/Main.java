package homework;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {
    static class JImageComponent extends JComponent{
        @Override
        public void paintComponent(Graphics g) {
            BufferedImage map;
            try {
                map = ImageIO.read(new File("C:\\Users\\Mihai\\IdeaProjects\\PA Lab8\\src\\homework\\map.png"));

                g.drawImage(map, 0, 0, getWidth(), getHeight(), this);
                g.setColor(Color.red);

                CitiesDAO citiesDAO = new CitiesDAO();
                ArrayList<City> allCities = citiesDAO.getAllCities();

                if (allCities != null){
                    for (City city : allCities){
                        double map_lat = getHeight() * (0.5 - city.latitude / 180);
                        double map_lon = getWidth() * (0.5 + city.longitude / 360);

                        g.drawRect((int) (map_lon - 3), (int) (map_lat - 3), 6, 6);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        CitiesDAO citiesDAO = new CitiesDAO();

        City london = citiesDAO.findByName("london");
        City bucharest = citiesDAO.findByName("bucharest");

        System.out.println(london.longitude);
        System.out.println(bucharest);

        System.out.println(Ruler.distanceBetween(london, bucharest));

        JFrame frame = new JFrame();
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JImageComponent image = new JImageComponent();
        frame.add(image);
        frame.setVisible(true);
        frame.repaint();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //DBConnectionPool.closeConnection();
    }
}
