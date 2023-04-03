import javax.swing.*;
import java.awt.*;

public class ColorPalette extends JPanel {

    public void paintComponent(Graphics g) {
        Color[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE, new Color(128, 0, 128), Color.BLACK};
        for (int i = 1; i < 8; i++) {
            g.setColor(colors[i-1]);
            int x = i * 100;
            int y = 0;
            int width = 60;
            int height = 50;
            g.fillRect(x, y, width, height);
        }
    }
    public Dimension getPreferredSize() {
        return new Dimension(1000, 50);
    }
}



