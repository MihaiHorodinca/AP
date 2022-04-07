import javax.annotation.processing.SupportedSourceVersion;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawingPanel  extends JPanel {
    MainFrame frame;
    Graphics graphics;
    int row, col;
    int width, height;
    int standard;
    int offW, offH;
    int boardW, boardH;
    int cellW, cellH;



    public DrawingPanel(MainFrame frame){
        this.frame = frame;

        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.WHITE);

        row = frame.configPanel.getRow();
        col = frame.configPanel.getCol();
    }

    public void paintComponents(Graphics graphics){
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        row = frame.configPanel.getRow();
        col = frame.configPanel.getCol();

        width = this.getWidth();
        height = this.getHeight();
        if (width < height)
            standard = width;
        else
            standard = height;

        offW = (width - standard) / 2 + 10;
        offH = (height - standard) / 2 + 10;

        boardW = width - 2 * offW;
        boardH = height - 2 * offH;

        cellW = boardW / (col - 1);
        cellH = boardH / (row - 1);

        g.setColor(Color.LIGHT_GRAY);

        for (int i = 0; i < col; i++)
            g.drawLine(offW + i * cellW, offH,
                    offW + i * cellW, offH + boardH);

        for (int i = 0; i < row; i++)
            g.drawLine(offW, offH + i * cellH,
                    offW + boardW, offH + i * cellH);

        for (int i = 0; i < col; i++)
            for (int j = 0; j < row; j++)
                g.drawOval(offW + i * cellW - 10, offH + j * cellH - 10, 20, 20);

        g.setStroke(new BasicStroke(5));
        g.setColor(Color.DARK_GRAY);

        for (int i = 0; i < col-1; i++)
            for (int j = 0; j < row; j++)
                if (Math.random() > 0.5)
                    g.drawLine(offW + i * cellW, offH + j * cellH,
                            offW + (i + 1) * cellW, offH + j * cellH);

        for (int i = 0; i < col; i++)
            for (int j = 0; j < row-1; j++)
                if (Math.random() > 0.5)
                    g.drawLine(offW + i * cellW, offH + j * cellH,
                            offW + i * cellW, offH + (j + 1) * cellH);

        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D imageG = image.createGraphics();
        printAll(imageG);
        imageG.dispose();
        try {
            ImageIO.write(image, "png", new File("poza.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
