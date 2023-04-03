import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorPalette extends JPanel {

    private Color color;
    public ColorPalette(Color color, Paint paint) {
        super();
        this.color = color;
        setPreferredSize(new Dimension(50, 70));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                paint.getDrawTable().setPen_color(color);
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(5, 5, getWidth() - 10, getHeight() - 10);
    }

}



