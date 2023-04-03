import javax.swing.*;
import java.awt.*;

public class ColorPalette extends JPanel {

    private Color color;

    public ColorPalette(Color color) {
        super();
        this.color = color;
        setPreferredSize(new Dimension(50, 70));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(5, 5, getWidth() - 10, getHeight() - 10);
    }
}



